package com.bin.picturns;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private ViewPager viewPager;
	private TextView tv_intro;
	private LinearLayout dot_layout;
	private ArrayList<Ad> list=new ArrayList<Ad>();
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg){
			viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
			handler.sendEmptyMessageDelayed(0, 4000);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		
		initListener();
		initData();
		
		
	}
	private void initView(){
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		tv_intro = (TextView) findViewById(R.id.tv_intro);
		dot_layout = (LinearLayout) findViewById(R.id.dot_layout);
	}
	@SuppressWarnings("deprecation")
	private void initListener(){
		viewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
				updateIntro();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	private void initData(){
		list.add(new Ad(R.drawable.a,"���������ף��ҾͲ��ܵ���"));
		list.add(new Ad(R.drawable.b, "�����ֻ����ˣ��ٳ������ϸ���������ͬ����"));
		list.add(new Ad(R.drawable.c, "���ر�����Ӱ�������"));
		list.add(new Ad(R.drawable.d, "������TV������"));
		list.add(new Ad(R.drawable.e, "��Ѫ��˿�ķ�ɱ"));
		initDots();
		viewPager.setAdapter(new MyPageAdapter());
		handler.sendEmptyMessageDelayed(0, 4000);
		updateIntro();
	}
	
	/**
	 * ������ʾ���ı���С�����ɫ
	 */
	private void updateIntro(){
		int currentPage=viewPager.getCurrentItem()%list.size();
		tv_intro.setText(list.get(currentPage).getIntro());
		
		for(int i=0;i<dot_layout.getChildCount();i++){//�������еĵ�
			dot_layout.getChildAt(i).setEnabled(i==currentPage);//������ǰ��͵�ǰҳ��ȣ�����ǰ����Ϊ��ɫ
		}
		
	}
	/**
	 * ��ʼ��ͼƬ�����С�㣬������list��С���ı�
	 */
	private void initDots(){
		for(int i=0;i<list.size();i++){
			View view=new View(this);//�ڵ�ǰҳ�涨��һ��viwe
			
			//������ʾ���
			LayoutParams params=new LayoutParams(8,8);//android.widget.LinearLayout.LayoutParams
			if(i!=0){
				params.leftMargin=5;
			}
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selector_dot);
			dot_layout.addView(view);
		}
	}
	class MyPageAdapter extends PagerAdapter{
		/**
		 * ����page
		 * position�� ��ǰ��Ҫ���ĵڼ���page
		 * object:��ǰ��Ҫ���ĵ�page
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			//super.destroyItem(container, position, object);
			container.removeView((View) object);
		}
		/**
		 * ������BaseAdapger��getView����
		 * ���˽��������ø�view
		 * ����������3�����棬����ҪviewHolder
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view=View.inflate(MainActivity.this, R.layout.adapter_ad,null);
			ImageView imageView=(ImageView) view.findViewById(R.id.image);
			Ad ad=list.get(position%list.size());//��ΪgetCount(),����100������position���ֵΪ99����ģ������ʵ��ѭ�����أ�αѭ����
			
			imageView.setImageResource(ad.getIconResId());
		    container.addView(view);//һ�������٣���view���뵽viewPager��
			return view;
		}
		/**
		 * ���ض��ٸ�page
		 */
		@Override
		public int getCount() {
			
			//return list.size();
			return 100;//ʵ��ѭ��Ч��
		}
		/**
		 * true: ��ʾ��ȥ������ʹ�û���  false:ȥ���´���
		 * view�� ��ǰ������view
		 * object����Ҫ������´�����view����instantiateItem��������
		 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view==object;
		}
		
	}
	
	
	
}
