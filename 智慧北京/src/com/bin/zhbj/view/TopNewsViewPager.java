package com.bin.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TopNewsViewPager extends ViewPager {

	private int startX;
	private int startY;
	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TopNewsViewPager(Context context) {
		super(context);
	}
	/**
	 * �¼��ַ������󸸿ؼ��������ڿؼ��Ƿ������¼�
	 * 1���һ�������������һ��ҳ��ʱ����Ҫ���ؼ����أ������¼��������ؼ�����
	 * 2���󻬣������������һ��ҳ��ʱ��Ҳ��Ҫ���ؼ�����
	 * 3�����»���Ҳ��Ҫ���ؼ�����
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		//getParent().requestDisallowInterceptTouchEvent(true);//��getParentȡ����
		switch(ev.getAction()){
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);//��ȡ�¼������ؼ���Ҫ���أ�����ȻMotionEvent.ACTION_MOVE����ִ��
			startX = (int)ev.getRawX();
			startY = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			int endX=(int) ev.getRawX();
			int endY=(int) ev.getRawY();
			if(Math.abs(endX-startX)>Math.abs(endY-startY)){//ˮƽ����
				if(endX>startX){//�һ�
					if(getCurrentItem()==0){
						getParent().requestDisallowInterceptTouchEvent(false);//��Ҫ���ؼ�����
					}
				}else{//��
					if(getCurrentItem()==getAdapter().getCount()-1){//���һ��ҳ��
						getParent().requestDisallowInterceptTouchEvent(false);//��Ҫ���ؼ�����
					}
				}
			}else{//��ֱ����
				getParent().requestDisallowInterceptTouchEvent(false);//��Ҫ���ؼ�����
			}
			break;
		default:
			break;
		}
		
		return super.dispatchTouchEvent(ev);
	}

}
