package com.bin.zhbj.base.impl.menudetail;

import java.util.ArrayList;

import com.bin.zhbj.R;
import com.bin.zhbj.base.BaseMenuDetailPager;
import com.bin.zhbj.base.TabDetailPager;
import com.bin.zhbj.domain.NewsData.NewsTabData;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class NewsMenuDetailPager extends BaseMenuDetailPager {
	
	private ViewPager mViewPager;
	private ArrayList<TabDetailPager> mPagerList;
	private ArrayList<NewsTabData> mNewsTabData;//��������ҳ��ҳǩ����
	private TabPageIndicator mIndicator;
	private ImageButton mImgBtn;
	public NewsMenuDetailPager(Activity activity,ArrayList<NewsTabData> children) {
		super(activity);
		mNewsTabData=children;
	}

	@Override
	public View initView() {
		View view=View.inflate(mActivity, R.layout.news_menu_detail, null);
		mViewPager=(ViewPager) view.findViewById(R.id.vp_menu_detail);
		//��ʼ���Զ���ؼ�
		mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		mImgBtn = (ImageButton) view.findViewById(R.id.btn_next);
		mImgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int currentItem=mViewPager.getCurrentItem();
				mViewPager.setCurrentItem(++currentItem);
			}
		});
		return view;
	}
	
//NewsCenterPager�е���
	@Override
	public void initData() {
		mPagerList=new ArrayList<TabDetailPager>();
		
		for(int i=0;i<mNewsTabData.size();i++){
			TabDetailPager pager=new TabDetailPager(mActivity,mNewsTabData.get(i));
			mPagerList.add(pager);//����������ҳҳ�е�ҳǩ��ӵ�������
		}
		mViewPager.setAdapter(new MenuDetailAdapter());
		
		mIndicator.setViewPager(mViewPager);//������setAdapter֮�����setViewPager
	}
	class MenuDetailAdapter extends PagerAdapter{
		//��indicator���ñ��⣬Ϊ��ʹindicator���������Ч�������嵥�ļ���ΪActivity����һ��������ʽ��android:theme="@style/Theme.PageIndicatorDefaults"
		@Override
		public CharSequence getPageTitle(int position) {
			return mNewsTabData.get(position).title;
		}
		@Override
		public int getCount() {
			return mPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int psition) {
			TabDetailPager pager=mPagerList.get(psition);
			container.addView(pager.mRootView);
			
			pager.initData();//��ʼ�����ݣ�Ҳ������ѡ��ʱ�ٳ�ʼ�����ݣ�
			return pager.mRootView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		
	}
	
}
