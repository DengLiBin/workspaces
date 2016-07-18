package com.jayqqaa12.abase.expand.umeng;

import com.jayqqaa12.abase.core.AApp;
import com.umeng.analytics.MobclickAgent;

public class UmengApp extends AApp implements Thread.UncaughtExceptionHandler
{

	public void onCreate()
	{
		super.onCreate();
		
		Thread.setDefaultUncaughtExceptionHandler(this);
		
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.setAutoLocation(true);
		
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex)
	{
		MobclickAgent.reportError(this, ex);
	};
}
