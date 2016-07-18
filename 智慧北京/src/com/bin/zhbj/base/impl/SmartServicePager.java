package com.bin.zhbj.base.impl;

import com.bin.zhbj.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class SmartServicePager extends BasePager {

	public SmartServicePager(Activity activity) {
		super(activity);
	
	}
//ContentFragment�е���
	@Override
	public void initData() {
		super.setSlidingMenuEnable(true);
		tvTitle.setText("�ǻ۷���");
		TextView text=new TextView(mActivity);
		text.setText("����");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		flContent.addView(text);//��textView��ӵ�frameLayout��
	}

}
