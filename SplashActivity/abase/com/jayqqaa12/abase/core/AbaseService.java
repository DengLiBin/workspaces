package com.jayqqaa12.abase.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;

/**
 * 
 * 不重复的单任务 使用 
 * 多任务 建议使用 AbaseIntentService
 * 
 * 默认 被杀死会 自动 重启
 * 可 重写 onStartCommand 修改
 * 
 * @author jayqqaa12
 * @date 2013-5-14
 */
public abstract class AbaseService extends Service implements Runnable
{
	protected abstract void doTask();

	@Override
	public void run()
	{
		Looper.prepare();
		doTask();
	}


	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		Abase.init(this);
		
		new Thread(this).start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{

		return START_REDELIVER_INTENT;
	}

}
