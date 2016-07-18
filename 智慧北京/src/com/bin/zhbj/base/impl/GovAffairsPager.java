package com.bin.zhbj.base.impl;

import com.bin.zhbj.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class GovAffairsPager extends BasePager {

	public GovAffairsPager(Activity activity) {
		super(activity);
	}

	@Override
	public void setSlidingMenuEnable(boolean enable) {
		super.setSlidingMenuEnable(enable);
	}

//ContentFragment�е���
	@Override
	public void initData() {
		super.setSlidingMenuEnable(true);
		tvTitle.setText("�˿ڹ���");
		TextView text=new TextView(mActivity);
		text.setText("����");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		flContent.addView(text);//��textView��ӵ�frameLayout��
		setSlidingMenuEnable(true);
	}

}
