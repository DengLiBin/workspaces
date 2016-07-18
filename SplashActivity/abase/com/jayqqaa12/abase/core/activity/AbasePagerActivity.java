package com.jayqqaa12.abase.core.activity;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.Abase;

/**
 * 
 * 
 * 用 ViewPage 时 应该使用的 类 如果 还有 其他要用 的 可以 用 AbaseAllActivity
 * 
 * 用 pageId 的时候 注意    只找 pageView  layout view 的话  id 要设置为 0  
 * 
 * 然后  属性的 先后顺序 一定要从 前往后排  不然 可能有问题
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
public class AbasePagerActivity extends AbaseActivity implements OnPageChangeListener
{
	
	protected  Map<Integer,View> pageViews =new  TreeMap<Integer,View>();

	
	@Override
	protected void initView(Field field, Resources resources)
	{
		FindView findView = Abase.initView(this, field, resources, parentViews,pageViews);
		if (findView == null) return;
		boolean pagerChange = findView.pagerChange();
		if (pagerChange) setPagerChangeClick(field);
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

	// ////////////////////////////////////Listener//////////////////////////////////////////////////

	@Override
	public void onPageScrollStateChanged(int stats)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int pos)
	{
		// TODO Auto-generated method stub

	}

}
