package com.bin.clock;

import java.util.Calendar;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeView extends LinearLayout {

	private TextView tv;
	private Handler timeHandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(getVisibility()==View.VISIBLE){
				refreshTime();
				timeHandler.sendEmptyMessageDelayed(0,1000);
			}
		};
	};
	public TimeView(Context context) {
		super(context);
	}
	
	public TimeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


	public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	//不可见与可见状态切换时掉用
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if(visibility==View.VISIBLE){
			timeHandler.sendEmptyMessage(0);
		}else{
			timeHandler.removeMessages(0);
		}
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		tv=(TextView) findViewById(R.id.tv);
		tv.setText("时钟页");
	}
	
	public void refreshTime(){
		Calendar c=Calendar.getInstance();
		//设置显示时间
		tv.setText(String.format("%d:%d:%d",c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND)));
	}
}
