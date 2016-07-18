package com.jayqqaa12.abase.core.activity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.core.Abase;
import com.jayqqaa12.abase.core.AbaseApp;

/**
 * 注意 用 parID 如果 当前是 parent 元素 id 设置为0 可以提高性能
 * 
 * @findEngine
 * @findDao 没有太大 意义 建议 在 application 里面 关闭 比较浪费性能
 * 
 * @author jayqqaa12. This is Android for Ioc framework. Use it must extends
 *         BaseActivity for your Activity and View Field add @findView and
 *         override attribute id (equel findViewById(R.id...)) or add onclik
 *         listener (equal setListener(this)) and override onListener in your
 *         Activity or you can open service and dao ioc but it's possible so
 *         show you can set ( IOC.activity or service or dao = false); it's so
 *         easy! Let's go
 * 
 */
public class AbaseActivity extends Activity implements Listener
{
	// 缓存 找到的 parentView 数据
	protected Map<Integer, View> parentViews = new HashMap<Integer, View>();

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Abase.init(this);

	}

	@Override
	public void setContentView(int layoutResID)
	{
		super.setContentView(layoutResID);

		initViewForReflect();
	}

	@Override
	public void setContentView(View view, LayoutParams params)
	{
		super.setContentView(view, params);

		initViewForReflect();
	}

	@Override
	public void setContentView(View view)
	{
		super.setContentView(view);

		initViewForReflect();
	}

	protected void initViewForReflect()
	{
		Resources resources = this.getResources();
		if (!Abase.isOpenActivtiy()) return;
		Field[] fields = getClass().getDeclaredFields();
		if (fields == null || fields.length < 1) return;

		for (Field field : fields)
		{
			initView(field, resources);
		}
	}

	protected void initView(Field field, Resources resources)
	{
		  Abase.initView(this, field, resources, parentViews);
	}
	
	
	
	

	// ////////////////////////////////////Listener//////////////////////////////////////////////////

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
	}

	@Override
	public void onClick(View v)
	{
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	{
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
	{
		return false;
	}

	@Override
	public boolean onLongClick(View v)
	{
		return false;
	}

}
