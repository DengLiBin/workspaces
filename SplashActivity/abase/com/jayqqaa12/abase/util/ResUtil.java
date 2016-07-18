package com.jayqqaa12.abase.util;

import android.content.Context;
import android.content.res.Resources;

import com.jayqqaa12.abase.annotation.view.FindRes;
import com.jayqqaa12.abase.annotation.view.FindRes.ResType;
import com.jayqqaa12.abase.core.Abase;

/**
 * 
 * 资源管理器
 * 
 * 
 */
public class ResUtil
{

	/**
	 * 根据资源的名字获取它的ID
	 * 
	 * @param name
	 *            要获取的资源的名字
	 * @param defType
	 *            资源的类型，如drawable, string 。。。
	 * @return 资源的id
	 */
	public int getResId(String name, String defType)
	{
		String packageName = getContext().getApplicationInfo().packageName;
		return getContext().getResources().getIdentifier(name, defType, packageName);

	}

	public static int[] getIntegerArray(int id)
	{

		return getContext().getResources().getIntArray(id);

	}

	public static String[] getStringArray(int id)
	{
		return getContext().getResources().getStringArray(id);
	}

	public static String[] getLocates()
	{
		return getContext().getAssets().getLocales();

	}

	public static Object getResValue(FindRes res, Resources resources)
	{

		int id = res.id();
		ResType type = res.type();
		Object value = null;

		if (type == ResType.BOOLEAN)
		{
			resources.getBoolean(id);
		}
		else if (type == ResType.COLOR)
		{
			value = resources.getColor(id);
		}
		else if (type == ResType.DRAWABLE)
		{
			value = resources.getDrawable(id);
		}
		else if (type == ResType.INT)
		{
			value = resources.getInteger(id);
		}
		else if (type == ResType.INT_ARRAY)
		{
			value = resources.getIntArray(id);
		}
		else if (type == ResType.STRING)
		{
			value = resources.getString(id);
		}
		else if (type == ResType.STRING_ARRAY)
		{
			value = resources.getStringArray(id);
		}
		else if (type == ResType.TEXT)
		{
			value = resources.getText(id);
		}

		return value;

	}

	public static Context getContext()
	{

		return Abase.getContext();

	}

	public static String[] getStringArray(int[] ids)
	{
		String [] s = new String[ids.length];
		
		int i =0;
		for(int id:ids )
		{
			s[i++] = getContext().getString(id);
		}
		
		return s;
		
	}

}
