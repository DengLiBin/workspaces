package com.bin.pulldownrefresh.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView.OnScrollListener;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bin.pulldownrefresh.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Attributes;

/**
 * Created by Administrator on 2016/1/31.
 */
public class RefreshListView extends ListView implements OnScrollListener {
    View headerView;
    ImageView iv_arrow;
    private ProgressBar pb_rotate;
    private TextView tv_state,tv_time;
    private View footerView;
    private int footerViewHeight;

    private int headerViewHeight;//headerView��
    private int downY;//����ʱy����
    private final int PULL_REFRESH = 0;//����ˢ�µ�״̬
    private final int RELEASE_REFRESH = 1;//�ɿ�ˢ�µ�״̬
    private final int REFRESHING = 2;//����ˢ�µ�״̬
    private int currentState = PULL_REFRESH;//��¼��ǰ״̬
    private RotateAnimation upAnimation,downAnimation;
    private boolean isLoadingMore = false;//��ǰ�Ƿ����ڴ��ڼ��ظ���
    public RefreshListView(Context context){
        super(context);
        init();
    }
    public RefreshListView(Context context,AttributeSet attrs){
        super(context,attrs);
        init();
    }
    private  void init(){
        setOnScrollListener(this);
        initHeaderView();
        initRotateAnimation();
        initFooterView();
    }
    private void initHeaderView(){
        headerView= View.inflate(getContext(), R.layout.layout_header,null);
        iv_arrow=(ImageView)headerView.findViewById(R.id.iv_arrow);
        pb_rotate=(ProgressBar)headerView.findViewById(R.id.pb_rotate);
        tv_time=(TextView)headerView.findViewById(R.id.tv_time);
        tv_state=(TextView)headerView.findViewById(R.id.tv_state);

        headerView.measure(0, 0);//����֪ͨϵͳ��ȥ������view
        headerViewHeight=headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerViewHeight, 0, 0);//��Ϊ-headerViewHeight�ɽ���view����

        addHeaderView(headerView);
    }
    /**
     * ��ʼ����ת����
     */
    private void initRotateAnimation(){
        upAnimation=new RotateAnimation(0,-180,RotateAnimation.RELATIVE_TO_SELF,0.5f,
                     RotateAnimation.RELATIVE_TO_SELF,0.5f);
        upAnimation.setDuration(300);
        upAnimation.setFillAfter(true);
        downAnimation = new RotateAnimation(-180, -360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(300);
        downAnimation.setFillAfter(true);
    }
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.layout_footer, null);
        footerView.measure(0, 0);//����֪ͨϵͳȥ������view;
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        addFooterView(footerView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY=(int)ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(currentState==REFRESHING){
                    break;
                }
                int deltaY=(int)(ev.getY()-downY);
                int paddingTop=-headerViewHeight+deltaY;
                if(deltaY>0&&getFirstVisiblePosition()==0){//getFirstVisiblePosition():������������һ��item��λ��
                    headerView.setPadding(0,paddingTop,0,0);
                    if(paddingTop>=0 && currentState==PULL_REFRESH){
                        //������ˢ�½����ɿ�ˢ��״̬
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView();
                    }else if (paddingTop<0 && currentState==RELEASE_REFRESH) {
                        //��������ˢ��״̬
                        currentState = PULL_REFRESH;
                        refreshHeaderView();
                    }
                    return true;//����TouchMove������listview����ô�move�¼�,�����listview�޷�����
                }
                break;
            case MotionEvent.ACTION_UP:
                if(currentState==PULL_REFRESH){
                    //����headerView
                     headerView.setPadding(0,-headerViewHeight,0,0);
                }else if(currentState==RELEASE_REFRESH){
                    headerView.setPadding(0,0,0,0);
                    currentState=REFRESHING;
                    refreshHeaderView();
                    if (listener != null) {
                        listener.onPullRefresh();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    /**
     * ����currentState������headerView
     */
   private  void refreshHeaderView(){
       switch (currentState){
           case PULL_REFRESH:
               tv_state.setText("����ˢ��");
               iv_arrow.setAnimation(downAnimation);
               break;
           case RELEASE_REFRESH:
               tv_state.setText("�ɿ�ˢ��");
               iv_arrow.setAnimation(upAnimation);
               break;
           case REFRESHING:
               iv_arrow.clearAnimation();//��Ϊ���ϵ���ת�����п���û��ִ����
               iv_arrow.setVisibility(View.INVISIBLE);
               pb_rotate.setVisibility(View.VISIBLE);
               tv_state.setText("����ˢ��...");
               break;
       }
   }
    /**
     * ���ˢ�²���������״̬,�����ȡ�����ݲ�������adater֮��ȥ��UI�߳��е��ø÷���
     */
    public void completeRefresh(){
        if(isLoadingMore){
            //����footerView״̬
            footerView.setPadding(0, -footerViewHeight, 0, 0);
            isLoadingMore = false;
        }else {
            //����headerView״̬
            headerView.setPadding(0, -headerViewHeight, 0, 0);
            currentState = PULL_REFRESH;
            pb_rotate.setVisibility(View.INVISIBLE);
            iv_arrow.setVisibility(View.VISIBLE);
            tv_state.setText("����ˢ��");
            tv_time.setText("���ˢ�£�"+getCurrentTime());
        }
    }
    /**
     * ��ȡ��ǰϵͳʱ�䣬����ʽ��
     * @return
     */
    private String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    private OnRefreshListener listener;
    public void setOnRefreshListener(OnRefreshListener listener){
        this.listener = listener;
    }
    public interface OnRefreshListener{
        void onPullRefresh();
        void onLoadingMore();
    }
    /**
     * SCROLL_STATE_IDLE:����״̬��������ָ�ɿ�
     * SCROLL_STATE_TOUCH_SCROLL����ָ�������������ǰ���������
     * SCROLL_STATE_FLING�����ٻ������ɿ�
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState==OnScrollListener.SCROLL_STATE_IDLE
                && getLastVisiblePosition()==(getCount()-1) &&!isLoadingMore){
            isLoadingMore = true;

            footerView.setPadding(0, 0, 0, 0);//��ʾ��footerView
            setSelection(getCount());//��listview���һ����ʾ����

            if(listener!=null){
                listener.onLoadingMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
