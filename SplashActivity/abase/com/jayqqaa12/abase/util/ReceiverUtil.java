package com.jayqqaa12.abase.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jayqqaa12.abase.core.AbaseUtil;

/**
 * 提供各种 系统 广播的 动态 注册
 * 
 * @author jayqqaa12
 * @date 2013-5-15
 */
public class ReceiverUtil extends AbaseUtil
{

	public static BroadcastReceiver smsReceived(Context context, BroadcastReceiver receiver)
	{
		 return registReceiver(context, receiver, "android.provider.Telephony.SMS_RECEIVED");
	}

	public static BroadcastReceiver timeTick(Context context, BroadcastReceiver receiver)
	{
		return registReceiver(context, receiver, Intent.ACTION_TIME_TICK);
	}

	public static BroadcastReceiver packageRemoved(Context context, BroadcastReceiver receiver)
	{
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
		intentFilter.addDataScheme("package");
		return registReceiver(context, receiver, intentFilter);

	}
	
	
	public static BroadcastReceiver packageAdded(Context context, BroadcastReceiver receiver)
	{
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.ACTION_PACKAGE_ADDED");
		intentFilter.addDataScheme("package");
		return registReceiver(context, receiver, intentFilter);
	}
	
	
	public static BroadcastReceiver packageReplaced(Context context, BroadcastReceiver receiver)
	{
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.ACTION_PACKAGE_REPLACED");
		intentFilter.addDataScheme("package");
		return registReceiver(context, receiver, intentFilter);
	}
	
	

	public static BroadcastReceiver registReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter)
	{
		filter.setPriority(Integer.MAX_VALUE);
		context.registerReceiver(receiver, filter);
		return receiver;
	}

	public static BroadcastReceiver registReceiver(Context context, BroadcastReceiver receiver, String action)
	{
		IntentFilter filter = new IntentFilter();
		filter.addAction(action);
		filter.setPriority(Integer.MAX_VALUE);
		context.registerReceiver(receiver, filter);

		return receiver;
	}

}
