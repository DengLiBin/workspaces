package com.bin.zhbj.base;

import com.bin.zhbj.MainActivity;
import com.bin.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * ��ҳ��5����ҳ��Ļ���
 * @author Administrator
 *
 */
public class BasePager {
	public Activity mActivity;
	public View mRootView;//���ֶ���
	public TextView tvTitle;//�������
	public ImageButton imgBtn;
	public FrameLayout flContent;//����
	public ImageButton btnPhoto;//��ͼ�л���ť
	public BasePager(Activity activity){
		this.mActivity=activity;
		initView();
	}
	/**
	 * ��ʼ������
	 */
	public void initView(){
		mRootView = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle=(TextView) mRootView.findViewById(R.id.tv_title);
		flContent=(FrameLayout) mRootView.findViewById(R.id.fl_content);
		imgBtn=(ImageButton) mRootView.findViewById(R.id.btn_menu);
		btnPhoto = (ImageButton) mRootView.findViewById(R.id.btn_photo);
		imgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleSlidingMenu();
			}
		});
	}
	/**
	 * ��ʼ������
	 */
	public void initData(){
		
	}
	
	/**
	 * ����slidingMenu�Ƿ����
	 * @param enable
	 */
	public void setSlidingMenuEnable(boolean enable){
		MainActivity mainUi=(MainActivity)mActivity;//BasePager�ڴ�������ʱ�����캯�������������ʵ����MainActivity
		SlidingMenu slidingMenu=mainUi.getSlidingMenu();
		if(enable){
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		}else{
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
		
	}
	/**
	 * ����slidingMenu����ʾ������
	 * @param show
	 */
	protected void toggleSlidingMenu(){
		MainActivity mainUi=(MainActivity) mActivity;
		SlidingMenu slidingMenu=mainUi.getSlidingMenu();
		slidingMenu.toggle();//״̬�л�����ʾʱ�����أ�����ʱ����ʾ
	}
}
