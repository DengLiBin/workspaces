package com.jayqqaa12.abase.util;

import java.io.File;
import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class IntentUtil
{

	public static void startIntent(Context context, Class clazz)
	{
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
	}

	public static Intent startIntent(Context context, Class clazz, String name, Bundle bundle)
	{
		Intent intent = new Intent(context, clazz);
		intent.putExtra(name, bundle);
		context.startActivity(intent);

		return intent;
	}

	public static Intent startService(Context context, Class clazz)
	{
		Intent service = new Intent(context, clazz);
		context.startService(service);
		return service;
	}
	
	
	public static Intent startService(ContextWrapper context, Class clazz)
	{
		Intent service = new Intent(context, clazz);
		context.startService(service);
		return service;
	}
	
	

	public static void startService(Context context, Class clazz, String name, Serializable obj)
	{
		Intent service = new Intent(context, clazz);
		service.putExtra(name, obj);
		context.startService(service);
	}

	public static void stopService(Context context, Class clazz)
	{
		Intent service = new Intent(context, clazz);
		context.stopService(service);
	}

	public static void startIntent(Context context, Class clazz, String name, Object content)
	{
		Intent intent = new Intent(context, clazz);
		putExtra(name, content, intent);

		context.startActivity(intent);
	}

	public static void startIntent(Context context, Class clazz, String name, Serializable obj)
	{
		Intent intent = new Intent(context, clazz);
		intent.putExtra(name, obj);

		context.startActivity(intent);
	}

	public static void startIntent(Context context, Class clazz, String[] name, Object[] content)
	{
		Intent intent = new Intent(context, clazz);
		int i = name.length;
		while (i-- > 0)
		{
			putExtra(name[i], content[i], intent);
		}
		context.startActivity(intent);
	}

	public static void startIntent(Context context, Class clazz, String objName, Serializable obj, String name, Object content)
	{
		Intent intent = new Intent(context, clazz);
		putExtra(name, content, intent);
		intent.putExtra(objName, obj);
		context.startActivity(intent);
	}

	public static void startIntentForReuslt(Activity activity, Class clazz, String objName, Serializable obj, String name, Object content,
			int requestCode)
	{
		Intent intent = new Intent(activity, clazz);
		putExtra(name, content, intent);
		intent.putExtra(objName, obj);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startIntentForReuslt(Activity activity, Class clazz, String objName, Serializable obj, String[] name, Object[] content,
			int requestCode)
	{
		Intent intent = new Intent(activity, clazz);
		int i = content.length;
		while (i-- > 0)
		{
			putExtra(name[i], content[i], intent);
		}
		intent.putExtra(objName, obj);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startIntentForReuslt(Activity activity, Class clazz, String name, Object content, int requestCode)
	{
		Intent intent = new Intent(activity, clazz);

		putExtra(name, content, intent);

		activity.startActivityForResult(intent, requestCode);
	}

	public static void startIntentForReuslt(Activity activity, Class clazz, String[] name, Object[] content, int requestCode)
	{
		Intent intent = new Intent(activity, clazz);

		int i = name.length;
		while (i-- > 0)
		{
			putExtra(name[i], content[i], intent);
		}

		activity.startActivityForResult(intent, requestCode);
	}

	private static void putExtra(String name, Object content, Intent intent)
	{
		if (content instanceof String)
		{
			intent.putExtra(name, (String) content);
		}
		else if (content instanceof Boolean)
		{
			intent.putExtra(name, (Boolean) content);

		}
		else if (content instanceof Integer)
		{

			intent.putExtra(name, (Integer) content);
		}
		else if (content instanceof Float)
		{

			intent.putExtra(name, (Float) content);
		}

		else if (content instanceof Serializable)
		{
			intent.putExtra(name, (Serializable) content);
		}
	}

	public static void startIntentForReuslt(Activity activity, Class clazz, int requestCode)
	{
		Intent intent = new Intent(activity, clazz);
		activity.startActivityForResult(intent, requestCode);
	}


}
