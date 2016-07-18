package com.jayqqaa12.abase.core.activity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
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
public class AbaseExpandableActivity extends AbaseActivity implements OnChildClickListener
{

	@Override
	protected   void initView(Field field, Resources resources)
	{
		FindView findView = Abase.initView(this, field, resources, parentViews);

		if (findView == null) return;

		boolean childClick = findView.childClick();
		if (childClick) setChildClick(field);

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

	// ////////////////////////////////////Listener//////////////////////////////////////////////////

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
