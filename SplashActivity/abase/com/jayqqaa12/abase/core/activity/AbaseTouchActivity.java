package com.jayqqaa12.abase.core.activity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.Abase;
import com.jayqqaa12.abase.core.AbaseApp;

/**
 * @author jayqqaa12. This is Android for Ioc framework. Use it must extends
 *         BaseActivity for your Activity and View Field add @findView and
 *         override attribute id (equel findViewById(R.id...)) or add onclik
 *         listener (equal setListener(this)) and override onListener in your
 *         Activity or you can open service and dao ioc but it's possible so
 *         show you can set ( IOC.activity or service or dao = false); it's so
 *         easy! Let's go
 * 
 * 
 * 
 */
public class AbaseTouchActivity extends AbaseActivity implements OnTouchListener
{

	@Override
	protected  void initView(Field field, Resources resources)
	{

		FindView findView = Abase.initView(this, field, resources, parentViews);

		if (findView == null) return;

		boolean touchBind = findView.touch();
		if ((touchBind)) setTouchListener(field);

		boolean gestureBind = findView.gesture();
		if ((gestureBind)) setGestureListener(field);

	}

	private void setGestureListener(Field field)
	{
		try
		{
			Object obj = field.get(this);
			if (obj instanceof View)
			{
				((View) obj).setOnTouchListener(this);
				((View) obj).setOnLongClickListener(this);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void setTouchListener(Field field)
	{
		try
		{
			Object obj = field.get(this);
			if (obj instanceof View)
			{
				((View) obj).setOnTouchListener(this);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	// ////////////////////////////////////Listener//////////////////////////////////////////////////

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
