package com.bin.zhbj.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bin.zhbj.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * ����ˢ�µ�ListView
 * @author Administrator
 *
 */
public class RefreshListView extends ListView implements OnScrollListener, android.widget.AdapterView.OnItemClickListener{
	//ˢ�µ�����״̬
	private static final int STATE_PULL_REFRESH=0;//����ˢ��
	private static final int STATE_RELEASE_REFRESH=1;//�ɿ�ˢ��
	private static final int STATE_REFRESHING=2;//����ˢ��
	
	private int mCurrentState=STATE_PULL_REFRESH;//��¼��ǰ״̬
	
	private View mHeaderView;
	private int startY=-1;//�������Y����
	private int mHeaderViewHeight;
	OnRefreshListener mListener;
	private View mFooterView;
	private int mFooterViewHeight;
	private TextView tvTitle;
	private TextView tvTime;
	private ImageView ivArrow;
	private ProgressBar pb_progress;
	private RotateAnimation animUp;//��ͷ��������ת����
	private RotateAnimation animDown;//��ͷ��������ת����
	//���췽��
	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHeaderView();
		initFooterView();
	}
	//���췽��
	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
		initFooterView();
	}
	//���췽��
	public RefreshListView(Context context) {
		super(context);
		initHeaderView();
		initFooterView();
	}
	//��ʼ��ͷ����
	private void initHeaderView(){
		initArrowAnim();//��ʼ������
		
		mHeaderView = View.inflate(getContext(),R.layout.refresh_header,null);
		this.addHeaderView(mHeaderView);//�ڸ�ListView��ͷ�����һ��ͷView
		
		tvTitle = (TextView) mHeaderView.findViewById(R.id.tv_title);
		tvTime = (TextView) mHeaderView.findViewById(R.id.tv_time);
		tvTime.setText("���ˢ��ʱ�䣺"+getCurrentTime());
		ivArrow = (ImageView) mHeaderView.findViewById(R.id.iv_arrow);
		pb_progress = (ProgressBar) mHeaderView.findViewById(R.id.pb_progress);
		  
		mHeaderView.measure(0,0);//�����ؼ��Ŀ��
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();//��ȡ�ؼ��ĸ�
		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);//���Խ�view����
	}
	//��ʼ��β����
	private void initFooterView(){
		mFooterView = View.inflate(getContext(), R.layout.refresh_footer, null);
		this.addFooterView(mFooterView);
		mFooterView.measure(0, 0);//�����ؼ��Ŀ��
		mFooterViewHeight = mFooterView.getMeasuredHeight();//��ȡ�ؼ��ĸ�
		mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);//���ؿؼ�
		this.setOnScrollListener(this);//���һ����������
		
	}
	
	//��дonTouchEvent����
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch(ev.getAction()){
		case MotionEvent.ACTION_DOWN://��ָ����
			startY = (int) ev.getRawY();
			//int startX=(int) ev.getRawX();��ֱ���򣬲��ÿ���x����
			break;
		case MotionEvent.ACTION_MOVE://����
			if(mCurrentState==STATE_REFRESHING){//�����������ˢ�µ�״̬��ֱ���������Ի��������κδ���
				break;
			}
			if(startY==-1){//ȷ���õ�startY��ֵ
				startY=(int) ev.getRawY();
			}
			int endY=(int) ev.getRawY();//����ʱ��Y����
			
			int dy=endY-startY;//�����Ĵ�ֱ����
			
			if(dy>0&&getFirstVisiblePosition()==0){//��֤���������������ľ���Ҫ�㹻�󣬲��Ҵ��ڵ�1��itemʱ����ʾmHeaderView
				int padding=dy-mHeaderViewHeight;//����padding��ֵ
				mHeaderView.setPadding(0, padding, 0, 0);//���õ�ǰ��padding
				
				if(padding>0&&mCurrentState!=STATE_RELEASE_REFRESH){//����������������ˢ�¸�Ϊ�ɿ�ˢ��
					mCurrentState=STATE_RELEASE_REFRESH;
					refreshState();//ˢ�¿ؼ�����
				}else if(padding<=0&&mCurrentState!=STATE_PULL_REFRESH){//�������벻��ʱ����Ϊ����ˢ��
					mCurrentState=STATE_PULL_REFRESH;
					refreshState();//ˢ�¿ؼ�����
				}
				
				return true;//����TouchMove������listview����ô�move�¼�,�����listview�޷�����
			}
			break;
		case MotionEvent.ACTION_UP://��ָ�뿪��Ļ
			startY=-1;//����
			if(mCurrentState==STATE_RELEASE_REFRESH){//���ɿ�ˢ�¸�Ϊ����ˢ��
				mCurrentState=STATE_REFRESHING;
				mHeaderView.setPadding(0,0,0,0);//��ʾ
				refreshState();
			}else if(mCurrentState==STATE_PULL_REFRESH){//�������ľ��벻��ʱ��
				mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);//��view����
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	/**
	 * ���ݵ�ǰ״̬���¿ؼ�����
	 */
	private void refreshState() {
		switch(mCurrentState){
		case STATE_PULL_REFRESH:
			tvTitle.setText("����ˢ��");
			ivArrow.setVisibility(View.VISIBLE);//��ʾ��ͷ 
			pb_progress.setVisibility(View.INVISIBLE);//����ProgressBar
			ivArrow.startAnimation(animDown);
		break;
		
		case STATE_RELEASE_REFRESH:
			tvTitle.setText("�ɿ�ˢ��");
			ivArrow.setVisibility(View.VISIBLE);//��ʾ��ͷ 
			pb_progress.setVisibility(View.INVISIBLE);//����ProgressBar
			ivArrow.startAnimation(animUp);
			break;
			
		case STATE_REFRESHING:
			tvTitle.setText("����ˢ��");
			ivArrow.clearAnimation();//������������ܽ���ͷ����
			ivArrow.setVisibility(View.INVISIBLE);//���ؼ�ͷ 
			pb_progress.setVisibility(View.VISIBLE);//��ʾProgressBar
			mListener.onRefresh();
			break;
		default:
			break;
		}
	}
	/**
	 * ��ͷ����
	 */
	private void initArrowAnim(){
		//��ͷ����
		animUp = new RotateAnimation(0,-180,
				Animation.RELATIVE_TO_SELF,0.5f,
				Animation.RELATIVE_TO_SELF,0.5f);
		animUp.setDuration(200);
		animUp.setFillAfter(true);//����״̬
		
		//��ͷ����
		animDown = new RotateAnimation(-180,0,
				Animation.RELATIVE_TO_SELF,0.5f,
				Animation.RELATIVE_TO_SELF,0.5f);
		animUp.setDuration(200);
		animUp.setFillAfter(true);//����״̬
	}
	
	//���ü������ķ���
	public void setOnRefreshListener(OnRefreshListener listener){
		mListener=listener;
	}
	//ˢ�µļ�����
	public interface OnRefreshListener{
		public void onRefresh();//ͷ��ˢ������
		
		public void onLoadingMore();//β�����ظ�������
	}
	
	/**
	 * ˢ����ɺ󣬽��ؼ����أ���TabDetailPager�У��ӷ�������ȡ�������ʱ������
	 */
	public  void onRefreshComplete(){
		if(isLoadingMore){
			mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);//���ؽŲ���
		}else{
			mCurrentState=STATE_PULL_REFRESH;
			tvTitle.setText("����ˢ��");
			ivArrow.setVisibility(View.VISIBLE);//��ʾ��ͷ 
			pb_progress.setVisibility(View.INVISIBLE);//����ProgressBar
			mHeaderView.setPadding(0, -mHeaderViewHeight, 0,0);//���ؼ�����
			
			tvTime.setText("���ˢ��ʱ�䣺"+getCurrentTime());
		}
		
	}
	
	/**
	 * ��ȡ��ǰʱ��,���෽��onRefreshComplete����
	 */
	public String getCurrentTime(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());//��׿ϵͳ�õ�ʲôʱ��������ʾ�ĸ�ʱ����ʱ��
	}
	
	
	private boolean isLoadingMore;//һ����ǣ���ʾ�Ƿ���Ҫ���ظ��࣬Ĭ����false
	//��дOnScrollListener�ķ���
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(scrollState==SCROLL_STATE_IDLE||scrollState==SCROLL_STATE_FLING){
			if(getLastVisiblePosition()==getCount()-1 && !isLoadingMore){//���������һ��item
				System.out.println("������");
				mFooterView.setPadding(0, 0, 0, 0);//��ʾ�ؼ�
				setSelection(getCount()-1);//����ѡ�е�Item��Ϊ���һ����
				
				isLoadingMore=true;
				if(mListener!=null){
					mListener.onLoadingMore(); 
				}
			}
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		
	}
	
	OnItemClickListener mItemClickListener;
	@Override
	public void setOnItemClickListener(android.widget.AdapterView.OnItemClickListener listener) {
		super.setOnItemClickListener(this);//this��ʾ����(ʵ����OnItemClickListener,��дOnItemClick����)
		mItemClickListener=listener;
	}
	
	//OnItemClickListener�ķ���
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(mItemClickListener!=null){
			mItemClickListener.onItemClick(parent, view, position-2, id);
		}
	}
	
}







