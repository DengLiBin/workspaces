package com.jayqqaa12.abase.core.activity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.Abase;
import com.jayqqaa12.abase.core.AbaseApp;

/**
 * 
 * 
 * @author jayqqaa12
 * @date 2013-4-15
 * @update 用 内部类 改进 失败  会使 使用者 调用 太麻烦 还是算了
 */
public class AbaseTabActivity extends TabActivity implements Listener, OnTabChangeListener
{
	// 缓存 找到的 parentView 数据
	protected Map<Integer, View> parentViews = new HashMap<Integer, View>();

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Abase.init(this);
	}
	
	protected void initViewForReflect()
	{
		Resources resources = this.getResources();
		if (!Abase.isOpenActivtiy()) return;
		Field[] fields = getClass().getDeclaredFields();
		if (fields == null || fields.length < 1) return;

		for (Field field : fields)
		{
			initView(field,resources);
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

	@Override
	public void onTabChanged(String tabId)
	{

	}

	/**
	 * 使用 getTabHost() 方式时使用 使用 系统 默认的 风格 同时 实现 IOC 为系统风格
	 * 
	 * @param contentLayoutId
	 *            总的activity 的布局 文件 也就是TabContent
	 * @param lables
	 *            每个 tab的 标签
	 * @param ContentId
	 *            每个content 的id
	 * 
	 */
	protected void initTab(int contentLayoutId, String[] lables, int[] ContentId)
	{
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(contentLayoutId, tabHost.getTabContentView(), true);

		int i = lables.length;
		while (i-- > 0)
		{
			tabHost.addTab(tabHost.newTabSpec(lables[i]).setIndicator(lables[i]).setContent(ContentId[i]));

		}
		init(tabHost);

	}

	/**
	 * 使用 getTabHost() 方式时使用 ， 自定义 风格时候用的 快捷方式 同时 实现 IOC 可实现 文字的 自动 装配
	 * 
	 * @param contentLayoutId
	 *            总的activity 的布局 文件 也就是TabContent
	 * @param tabWidgetLayoutId
	 *            自定义的 TabWight layout的 id
	 * @param tabTextViewLabel
	 *            自定义 Tabwight 的TextView 文字
	 * @param tabTextViewId
	 *            自定义 Tabwight 的TextView id
	 * @param lables
	 *            每个 tab的 标签
	 * @param contentIds
	 *            每个content 的id
	 */
	protected void initMyTab(int contentLayoutId, int tabWidgetLayoutId, String[] tabTextViewLabel, int tabTextViewId, String[] lables,
			int[] contentIds)
	{
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(contentLayoutId, tabHost.getTabContentView(), true);

		int length = lables.length;
		int i = -1;

		while (++i < length)
		{
			View tab = (View) LayoutInflater.from(this).inflate(tabWidgetLayoutId, null);
			View view = tab.findViewById(tabTextViewId);
			if (view instanceof TextView) ((TextView) view).setText(tabTextViewLabel[i]);

			tabHost.addTab(tabHost.newTabSpec(lables[i]).setIndicator(tab).setContent(contentIds[i]));

		}
		init(tabHost);

	}

	/**
	 * 使用 getTabHost() 方式时使用 ， 自定义 风格时候用的 快捷方式 同时 实现 IOC 可实现 文字的 自动 装配
	 * 
	 * @param contentLayoutId
	 *            总的activity 的布局 文件 也就是TabContent
	 * @param tabWidgetLayoutId
	 *            自定义的 TabWight layout的 id
	 * @param tabTextViewLabel
	 *            自定义 Tabwight 的TextView 文字
	 * @param tabTextViewId
	 *            自定义 Tabwight 的TextView id
	 * @param lables
	 *            每个 tab的 标签
	 * @param contentIds
	 *            每个content 的id
	 */
	protected void initMyTab(int contentLayoutId, int tabWidgetLayoutId, String[] tabTextViewLabel, int tabTextViewId, String[] lables,
			Intent[] contentIds)
	{
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(contentLayoutId, tabHost.getTabContentView(), true);

		int length = lables.length;
		int i = -1;

		while (++i < length)
		{
			View tab = (View) LayoutInflater.from(this).inflate(tabWidgetLayoutId, null);
			View view = tab.findViewById(tabTextViewId);
			if (view instanceof TextView) ((TextView) view).setText(tabTextViewLabel[i]);

			tabHost.addTab(tabHost.newTabSpec(lables[i]).setIndicator(tab).setContent(contentIds[i]));

		}
		init(tabHost);

	}

	/**
	 * 使用 getTabHost() 方式时使用 ， 自定义 风格时候用的 快捷方式 同时 实现 IOC 可实现 文字的 自动 装配
	 * 
	 * @param tabWidgetLayoutId
	 *            自定义的 TabWight layout的 id
	 * @param tabTextViewLabel
	 *            自定义 Tabwight 的TextView 文字
	 * @param tabTextViewId
	 *            自定义 Tabwight 的TextView id
	 * @param lables
	 *            每个 tab的 标签
	 * @param contentIds
	 *            每个content 的id
	 */
	protected void initMyTab(int tabWidgetLayoutId, String[] tabTextViewLabel, int tabTextViewId, String[] lables, Intent[] contentIds)
	{
		TabHost tabHost = getTabHost();

		int length = lables.length;
		int i = -1;

		while (++i < length)
		{
			View tab = (View) LayoutInflater.from(this).inflate(tabWidgetLayoutId, null);
			View view = tab.findViewById(tabTextViewId);
			if (view instanceof TextView) ((TextView) view).setText(tabTextViewLabel[i]);

			tabHost.addTab(tabHost.newTabSpec(lables[i]).setIndicator(tab).setContent(contentIds[i]));

		}
		init(tabHost);

	}

	/**
	 * 使用 getTabHost() 方式时使用 ， 自定义 风格时候用的 快捷方式 同时 实现 IOC 可实现 所有类型的自动 装配
	 * 
	 * @param contentLayoutId
	 * @param tabWidgetLayoutId
	 * @param tabWidgetResIds
	 * @param tabWidgetIds
	 * @param lables
	 * @param contentIds
	 */
	protected void initMyTab(int contentLayoutId, int tabWidgetLayoutId, int[][] tabWidgetResIds, int[] tabWidgetIds, String[] lables,
			int[] contentIds)
	{
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(contentLayoutId, tabHost.getTabContentView(), true);

		int length = lables.length;
		int i = -1;

		while (++i < length)
		{
			View tab = (View) LayoutInflater.from(this).inflate(tabWidgetLayoutId, null);

			int j = -1;

			while (++j < tabWidgetIds.length)
			{
				View view = tab.findViewById(tabWidgetIds[j]);
				if (view instanceof TextView) ((TextView) view).setText(tabWidgetResIds[i][j]);
				if (view instanceof ImageView) view.setBackgroundResource(tabWidgetResIds[i][j]);

			}

			tabHost.addTab(tabHost.newTabSpec(lables[i]).setIndicator(tab).setContent(contentIds[i]));

		}
		init(tabHost);

	}

	/**
	 * 没有使用 getTabHost() 方式时使用 ， 自定义 风格Tab时候用的 快捷方式 同时 实现 IOC 可实现 所有类型的自动 装配
	 * 
	 * @param tabHost
	 * @param tabWidgetLayoutId
	 * @param tabWidgetResIds
	 *            子控件Id的 资源 Id
	 * @param tabWidgetIds
	 *            子控件Id
	 * @param lables
	 *            标签
	 * @param contentIds
	 *            内容
	 */
	protected void initMyTab(TabHost tabHost, int tabWidgetLayoutId, int[][] tabWidgetResIds, int[] tabWidgetIds, String[] lables, int[] contentIds)
	{

		int length = lables.length;
		int i = -1;

		while (++i < length)
		{
			View tab = (View) LayoutInflater.from(this).inflate(tabWidgetLayoutId, null);

			int j = -1;

			while (++j < tabWidgetIds.length)
			{
				View view = tab.findViewById(tabWidgetIds[j]);
				if (view instanceof TextView) ((TextView) view).setText(tabWidgetResIds[i][j]);
				if (view instanceof ImageView) ((ImageView) view).setImageResource(tabWidgetResIds[i][j]);
			}

			tabHost.addTab(tabHost.newTabSpec(lables[i]).setIndicator(tab).setContent(contentIds[i]));

		}

		init(tabHost);
	}

	/**
	 * 没有使用 getTabHost() 方式时使用 ， 自定义 风格Tab时候用的 快捷方式 同时 实现 IOC 可实现 所有类型的自动 装配
	 * 
	 * @param tabHost
	 * @param tabWidgetLayoutId
	 * @param tabWidgetResIds
	 *            子控件Id的 资源 Id
	 * @param tabWidgetIds
	 *            子控件Id
	 * @param lables
	 *            标签
	 * @param Intents
	 *            内容
	 */
	protected void initMyTab(TabHost tabHost, int tabWidgetLayoutId, int[][] tabWidgetResIds, int[] tabWidgetIds, String[] lables, Intent[] intents)
	{

		int length = lables.length;
		int i = -1;

		while (++i < length)
		{
			View tab = (View) LayoutInflater.from(this).inflate(tabWidgetLayoutId, null);

			int j = -1;

			while (++j < tabWidgetIds.length)
			{
				View view = tab.findViewById(tabWidgetIds[j]);
				if (view instanceof TextView) ((TextView) view).setText(tabWidgetResIds[i][j]);
				if (view instanceof ImageView) ((ImageView) view).setImageResource(tabWidgetResIds[i][j]);
			}

			tabHost.addTab(tabHost.newTabSpec(lables[i]).setIndicator(tab).setContent(intents[i]));

		}

		init(tabHost);
	}

	private void init(TabHost tabHost)
	{
		initViewForReflect();
		tabHost.setOnTabChangedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	{

	}

}
