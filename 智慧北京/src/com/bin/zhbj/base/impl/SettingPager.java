package com.bin.zhbj.base.impl;

import com.bin.zhbj.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class SettingPager extends BasePager {

	public SettingPager(Activity activity) {
		super(activity);
		
	}
//ContentFragment�е���
	@Override
	public void initData() {
		super.setSlidingMenuEnable(false);
		imgBtn.setVisibility(View.INVISIBLE);//���ذ�ť
		tvTitle.setText("��������");
		TextView text=new TextView(mActivity);
		text.setText("����");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		flContent.addView(text);//��textView��ӵ�frameLayout��
	}
	
}
