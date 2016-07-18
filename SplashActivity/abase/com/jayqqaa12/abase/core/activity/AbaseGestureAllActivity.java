package com.jayqqaa12.abase.core.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.jayqqaa12.abase.core.Direction;

public class AbaseGestureAllActivity extends AbaseAllActivity implements OnGestureListener
{
	public static final int MIN_GESTURE_DISTANCE = 50;
	private GestureDetector detector = new GestureDetector(this);
	private Context context;
	private Class<? extends Activity> pre;
	private Class<? extends Activity> next;

	protected void setChangeActivity(Context context, Class<? extends Activity> pre, Class<? extends Activity> next)
	{
		this.context = context;
		this.pre = pre;
		this.next = next;
	}
	
	
	

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{

		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		Direction dir = getGestureDirection(e1, e2);
		
		changeActivity(context, pre, next, dir);
		
		return false;
	}

	protected Direction getGestureDirection(MotionEvent e1, MotionEvent e2)
	{
		if (e2.getX() - e1.getX() > MIN_GESTURE_DISTANCE) return Direction.RIGHT;
		if (Math.abs(e2.getX() - e1.getX()) > MIN_GESTURE_DISTANCE) return Direction.LEFT;
		if (Math.abs(e2.getY() - e1.getY()) > MIN_GESTURE_DISTANCE) return Direction.UP;
		if (e2.getY() - e1.getY() > MIN_GESTURE_DISTANCE) return Direction.DOWN;

		return null;

	}
	
	
	
	protected void changeView(ViewFlipper vf ,Direction dir)
	{
		if (dir == Direction.RIGHT)
		{
			vf.showPrevious();
		}
		else if (dir == Direction.LEFT)
		{
			vf.showNext();
		}
	}
	
	
	
	
	
	

	protected  void changeActivity(Context context, Class<? extends Activity> pre, Class<? extends Activity> next,
			Direction dir)
	{
		// right is go back
		if (dir == Direction.RIGHT)
		{
			if (pre == null) return;
			Intent intent = new Intent(context, pre);
			finish();
			startActivity(intent);
		}
		// will LEFT  is   go forward 
		else if (dir == Direction.LEFT)
		{
			if (next == null) return;
			Intent intent = new Intent(context, next);

			if (next != null) intent.putExtra("activity",next.getSimpleName());
			finish();
			startActivity(intent);
		}
	}

	private void setOverridePendingTransition(int in,int out)
	{
		overridePendingTransition(in ,out);
		
	}
	
	
	

}
