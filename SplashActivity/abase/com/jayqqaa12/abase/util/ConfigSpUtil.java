package com.jayqqaa12.abase.util;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jayqqaa12.abase.core.Abase;

/**
 * SharedPredferences  util
 *  当要 使用事物时 不必 用 这里的方法
 */
public class ConfigSpUtil
{
	private static SharedPreferences sharedPreferences;
	
	

	public static SharedPreferences getConfigSp()
	{
		if (sharedPreferences == null)
		{
			sharedPreferences = Abase.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sharedPreferences;
	}

	
	public static SharedPreferences getConfigSp(String name)
	{
		if (sharedPreferences == null)
		{
			sharedPreferences = Abase.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
		}
		return sharedPreferences;
	}
	
	public static String getString(String key, String defultValue)
	{
		
		return  getConfigSp().getString(key, defultValue);
	}


	public static Boolean getBoolean( String key, Boolean defultValue)
	{
		return  getConfigSp().getBoolean(key, defultValue);
	}

	
	public static int getInteger(String key,int defultValue)
	{
		return  getConfigSp().getInt(key,defultValue);
	}
	
	
	public static void setValue(String key ,Object value)
	{
		
		
		Editor edit =  getConfigSp().edit();
		if (value instanceof Boolean) {
			edit.putBoolean(key, (Boolean) value);
		} else if (value instanceof Integer || value instanceof Byte) {
			edit.putInt(key, (Integer) value);
		} else if (value instanceof Long) {
			edit.putLong(key, (Long) value);
		} else if (value instanceof Float) {
			edit.putFloat(key, (Float) value);
		} else if (value instanceof String) {
			edit.putString(key, (String) value);
		} else {
			edit.putString(key, value.toString());
		}
		edit.commit();
	}

	

}
