package com.jayqqaa12.abase.core.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.Abase;
import com.jayqqaa12.abase.core.AbaseApp;

/**
 * 
 * 包括 所以的 方法 比较 耗 资源
 * 
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
public class AbaseAllActivity extends AbaseActivity implements OnPageChangeListener, OnChildClickListener, OnTouchListener, OnFocusChangeListener,
		TextWatcher
{
	protected  Map<Integer,View> pageViews =new  TreeMap<Integer,View>();

	@Override
	protected  void initView(Field field, Resources resources)
	{
		FindView findView = Abase.initView(this, field, resources, parentViews,pageViews);

		if (findView == null) return;

		boolean touchBind = findView.touch();
		if ((touchBind)) setTouchListener(field);

		boolean textWatcherBind = findView.textChanged();
		if ((textWatcherBind)) setTextWatcherListener(field);

		boolean focusChangeBind = findView.focusChange();
		if ((focusChangeBind)) setFocusChangeListener(field);

		boolean gestureBind = findView.gesture();
		if ((gestureBind)) setGestureListener(field);

		boolean childClickBind = findView.childClick();
		if (childClickBind) setChildClick(field);
		boolean pagerChangeBind = findView.pagerChange();
		if (pagerChangeBind) setPagerChangeClick(field);
	}
	
	
	
	
	
	

	private void setPagerChangeClick(Field field)
	{
		try
		{
			Object obj = field.get(this);
			if (obj instanceof ViewPager)
			{
				((ViewPager) obj).setOnPageChangeListener(this);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void setChildClick(Field field)
	{

		try
		{
			Object obj = field.get(this);
			if (obj instanceof ExpandableListView)
			{
				((ExpandableListView) obj).setOnChildClickListener(this);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

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

	private void setFocusChangeListener(Field field)
	{
		try
		{
			Object obj = field.get(this);
			if (obj instanceof View)
			{
				((View) obj).setOnFocusChangeListener(this);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setTextWatcherListener(Field field)
	{
		try
		{
			Object obj = field.get(this);
			if (obj instanceof TextView)
			{
				((TextView) obj).addTextChangedListener(this);
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
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable s)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0)
	{
		// TODO Auto-generated method stub

	}

}
