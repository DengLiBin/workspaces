package com.jayqqaa12.mobilesafe.ui.comm.phone;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseTouchActivity;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationDisplayEngine;

public class LocationDisplayDragActivity extends AbaseTouchActivity
{
	private static final String TAG = "PhoneLocationDisplayDragActivity";
	
	@FindView(id = R.id.fl,touch=true)
	private FrameLayout fl;
	@FindView(id = R.id.title_1_tv,textId=R.string.phone_location_display_position_lable)
	private TextView tv_title;
	
	@FindEngine
	private LocationDisplayEngine engine;
	
	
	private int startx; // 记录下来第一次手指触摸屏幕的位置
	private int starty;
	private int screen_width;
	private int screen_height;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_location_display_drap);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		DisplayMetrics dm = getResources().getDisplayMetrics();
		screen_height = dm.heightPixels;
		screen_width = dm.widthPixels;

		int x = engine.getDragX();
		int y = engine.getDragY();

		Log.i(TAG, "x =" + x);
		Log.i(TAG, "y =" + y);
		fl.setBackgroundResource(engine.getDisplayStyleDrwable());
		// became view ont resume  so  layout params is null

//		fl.layout(fl.getLeft() + x, 0 + y, fl.getRight() + x, fl.getBottom() + y);
//		fl.invalidate();
		
		LayoutParams params = (LayoutParams) fl.getLayoutParams();
		params.leftMargin = x;
		params.topMargin = y;
		fl.setLayoutParams(params);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		switch (v.getId())
		{
		case R.id.fl:
			switch (event.getAction())
			{

			case MotionEvent.ACTION_DOWN:

				Log.i(TAG, "start down");

				startx = (int) event.getRawX(); // 获取手指第一次接触屏幕在x方向的坐标
				starty = (int) event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE: // 手指没有离开屏幕 在屏幕移动
				int x = (int) event.getRawX();
				int y = (int) event.getRawY();
				// 获取手指移动的距离
				int dx = x - startx;
				int dy = y - starty;
				int left = fl.getLeft() + dx;
				int top = fl.getTop() + dy;
				int right = fl.getRight() + dx;
				int bottom = fl.getBottom() + dy;

				int width = fl.getWidth();
				int height = fl.getHeight();

				if (left < 0)
				{
					left = 0;
					right = left + width;
				}
				if (right > screen_width)
				{
					right = screen_width;
					left = right - width;
				}
				if (top < 0)
				{
					top = 0;
					bottom = top + height;
				}
				if (bottom > screen_height)
				{
					bottom = screen_height;
					top = bottom - height;
				}

				fl.layout(left, top, right, bottom);
				startx = (int) event.getRawX(); // 获取到移动后的位置
				starty = (int) event.getRawY();

				break;
			case MotionEvent.ACTION_UP: // 手指离开屏幕对应的事件
				// 记录下来最后 图片在窗体中的位置
				Log.i(TAG, "up");
				int lasty = fl.getTop();
				int lastx = fl.getLeft();

				engine.setDragX(lastx);
				engine.setDragY(lasty);
				break;
			}
			break;
		}
		return true; // 不会中断触摸事件的返回
	}

}
