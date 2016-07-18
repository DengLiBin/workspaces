package com.bin.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	//����false,��ʾ�������¼�����Ȼ�����ӿؼ��ò����¼���
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	};
	@Override
	//���ػ��������������κδ���
	public boolean onTouchEvent(MotionEvent arg0) {
		
		return false;
	}

	
}
