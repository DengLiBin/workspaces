package com.jayqqaa12.abase.core.activity;

import java.lang.reflect.Field;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
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
 */
public class AbaseTextActivity extends AbaseActivity implements TextWatcher
{

	@Override
	protected  void initView(Field field, Resources resources)
	{
		FindView findView = Abase.initView(this, field, resources, parentViews);

		if (findView == null) return;

		boolean textWatcherBind = findView.textChanged();
		if ((textWatcherBind)) setTextWatcherListener(field);

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

}
