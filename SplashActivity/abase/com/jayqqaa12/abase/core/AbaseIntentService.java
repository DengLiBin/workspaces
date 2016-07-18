package com.jayqqaa12.abase.core;

import com.jayqqaa12.abase.util.common.TAG;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * 
 *  This's IntentService  ioc
 *  
 *  多个任务 都可以放在这里
 *  
 * 
 */
public abstract class AbaseIntentService extends IntentService
{

	public AbaseIntentService()
	{
		super(TAG.SERVICE);
	}
	
	public AbaseIntentService(String threadName)
	{
		super(threadName);
	}


	@Override
	public void onCreate()
	{
		super.onCreate();
		Abase.init(this);
		
	}

	protected Context getContext()
	{
		return getApplicationContext();
	}

}
