package com.jayqqaa12.mobilesafe.service;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseService;
import com.jayqqaa12.abase.core.Provider;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.media.KeyguardUtil;
import com.jayqqaa12.abase.util.sys.TaskUtil;
import com.jayqqaa12.mobilesafe.engine.space.AppLockEngine;
import com.jayqqaa12.mobilesafe.provider.AppLockProvider;
import com.jayqqaa12.mobilesafe.ui.space.lock.LockScreenActivity;

public class AppLockDogService extends AbaseService
{

	@FindEngine
	private AppLockEngine engine;

	private Intent lockIntent;
	private ActivityManager am;
	private List<String> locks;
	private List<String> passApps = new ArrayList<String>();
	private MyBinder myBinder = new MyBinder();

	@Override
	public IBinder onBind(Intent intent)
	{
		return myBinder;
	}

	public class MyBinder extends Binder implements IAppLockDogService
	{

		public void callLock(String packname)
		{
			lock(packname);
		}

		public void callUnlock(String packname)
		{
			unlock(packname);

		}

	}

	/**
	 * 重新开启对应用的保护
	 * 
	 * @param packname
	 */
	private void lock(String packname)
	{
		if (passApps.contains(packname))
		{
			passApps.remove(packname);
		}

	}

	@Override
	public void onCreate()
	{
		super.onCreate();

		getContentResolver().registerContentObserver(Uri.parse(Provider.CONTENT + AppLockProvider.AUTHORITIES), true, new MyObserver(new Handler()));

	}

	/**
	 * 临时停止对某个app的保护
	 * 
	 * @param packname
	 */
	private void unlock(String packname)
	{
		passApps.add(packname);
	}

	@Override
	protected void doTask()
	{

		locks = engine.getAllLocks();
		lockIntent = new Intent(this, LockScreenActivity.class);

		am = ManageUtil.getActivityManager();

		while (engine.isOpenService())
		{
			try
			{
				String packname = TaskUtil.getTaskTopActivityPackName(am);
				
				if (KeyguardUtil.isKeyguardRestricted())
				{
					if (passApps.size() > 0)
					{
						passApps.clear();
						Log.i(TAG.SERVICE, "锁定的 程序 清空");
					}
				}
				
				if (passApps.contains(packname)) continue;

				if (locks.contains(packname))
				{
					lockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Log.i(TAG.SERVICE, packname + "这个被锁住了 哦");
					lockIntent.putExtra("packname", packname);
					startActivity(lockIntent);
				}

				

				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

	}

	private class MyObserver extends ContentObserver
	{
		public MyObserver(Handler handler)
		{
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange)
		{
			super.onChange(selfChange);

			Log.i(TAG.SERVICE, "----------------------------------数据库内容变化了");
			locks = engine.getAllLocks();
			passApps.clear();
		}

	}

	@Override
	public void onDestroy()
	{
		IntentUtil.startService(this, MonitorService.class);
		super.onDestroy();
	}

}
