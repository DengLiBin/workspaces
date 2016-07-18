package com.bin.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 11����ҳ��ҳǩ��viewPager 
 * @author Administrator
 *
 */
public class HorizonalViewPager extends ViewPager {

	public HorizonalViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HorizonalViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(this.getCurrentItem()!=0){//������ǰҳ���ǵ�0ҳ�����򸸿ؼ��������ص��¼������������øÿؼ��Լ�����������ǰҳ�ǵ�0ҳ�������¼����ø��ؼ����ش���
			getParent().requestDisallowInterceptTouchEvent(true);//��getParentȡ����
		}
		
		return super.dispatchTouchEvent(ev);
	}
}
