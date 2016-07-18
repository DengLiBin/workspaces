package com.bin.zhbj;

import java.util.ArrayList;

import com.bin.zhbj.utils.DensityUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {
	private ViewPager vpGuide;
	private static final int[] mImageIds=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	private ArrayList<ImageView> mImageViewList;
	private LinearLayout llPointGroup;//����Բ��ĸ��ؼ�
	private int mPointWidth;//����Բ��֮��ľ���
	private View viewRedPoint;//С���
	private Button btnStart;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر���������setContentView()֮ǰ����
		setContentView(R.layout.activity_guide);
		sp = getSharedPreferences("config",MODE_PRIVATE);
		vpGuide=(ViewPager) findViewById(R.id.vp_guide);
		btnStart=(Button) findViewById(R.id.button1);
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GuideActivity.this,MainActivity.class));
				sp.edit().putBoolean("is_user_guide_showed", true).commit();
				finish();
			}
		});
		llPointGroup=(LinearLayout) findViewById(R.id.ll_point_group);
		viewRedPoint=findViewById(R.id.view_red_point);
		initView();
		vpGuide.setAdapter(new GuideAdapter());
		
		vpGuide.setOnPageChangeListener(new GuideListener());
	}
	/**
	 * ��ʼ������
	 */
	private void initView(){
		mImageViewList = new ArrayList<ImageView>();
		//��ʼ������ҳ������ҳ��
		for(int i=0;i<mImageIds.length;i++){
			ImageView image=new ImageView(this);
			image.setBackgroundResource(mImageIds[i]);//���ñ�������ҳ
			mImageViewList.add(image);//�����е�ImageView��ӵ�������
		}
		
		//��ʼ������ҳ��СԲ��
		for(int i=0;i<mImageIds.length;i++){
			View point=new View(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);//��������ҳĬ��Բ��
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
				DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10));//��10dpת��px
			if(i>0){
				params.leftMargin=10;//����Բ����
			}
			point.setLayoutParams(params);//����Բ���С�����ؼ���LinearLayout��������LinearLayout��LayoutParams
			
			llPointGroup.addView(point);//��Բ����Ӹ����Բ���
		}
		
		
		//��ȡ��ͼ��,��layout�����¼����м���
		llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			

			//��layoutִ�н�����ص��˷���
			@Override
			public void onGlobalLayout() {
				System.out.println("layout����");
				llPointGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mPointWidth = llPointGroup.getChildAt(1).getLeft()-llPointGroup.getChildAt(0).getLeft();
				System.out.println("Բ����룺"+mPointWidth);
			}
		});
	}
	
	/**
	 * ViewPager������������
	 * @author Administrator
	 *
	 */
	class GuideAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			
			//return mImageIds.length;
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
		
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);		
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);;
			
		}
	}
	
	/**
	 * ViewPager�Ļ�������
	 * @author Administrator
	 *
	 */
	class GuideListener implements OnPageChangeListener{
		//��������
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			
			System.out.println("��ǰλ�ã�"+position+";�ٷֱȣ�"+positionOffset+";�ƶ����룺"+positionOffsetPixels);
			int len=(int) (mPointWidth * positionOffset)+position*mPointWidth;//�����ڶ�ҳʱpositionOffset��ֵ�����
			//��ȡ��ǰ���Ĳ��ֲ���
			RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
			System.out.println("leftMagin:"+params.leftMargin);
			params.leftMargin=len;
			viewRedPoint.setLayoutParams(params);//���¸�С������ò��ֲ���
		}
		//ĳ��ҳ�汻ѡ��
		@Override
		public void onPageSelected(int position) {
			if(position==mImageIds.length-1){//���һ��ҳ��
				btnStart.setVisibility(View.VISIBLE);//����Ϊ�ɼ�
			}else{
				btnStart.setVisibility(View.INVISIBLE);
			}
			
		}
		//����״̬�����仯
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}

	}

}
