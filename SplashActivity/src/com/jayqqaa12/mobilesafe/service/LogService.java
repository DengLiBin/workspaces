package com.jayqqaa12.mobilesafe.service;

import java.io.BufferedReader;

import android.content.Intent;
import android.util.Log;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseService;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.common.LogcatUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.sys.TaskUtil;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;
import com.jayqqaa12.mobilesafe.ui.space.lock.LockScreenActivity;

public class LogService extends AbaseService
{
	@FindEngine
	private LostEngine lostEngine;


	@Override
	protected void doTask()
	{
		try
		{
			Intent settingsIntent = new Intent(LogService.this, LockScreenActivity.class);
			settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			settingsIntent.putExtra("packname", "com.android.settings");
			settingsIntent.putExtra("redirection", true);

			Log.i(TAG.SERVICE, "start get log");
			BufferedReader br = LogcatUtil.getLog(TAG.ActivityManager);
			String log = null;

			while ((log = br.readLine()) != null)
			{
				// TODO 如何 点 取消后 再次 进入的 是setting 主界面
				if (!lostEngine.isOpenUninstallProtectedService()) stopSelf();

				if (log.contains("dat=package:" + getPackageName() + " cmp=com.android.settings/.applications.InstalledAppDetails"))
				{
					startActivity(settingsIntent);
				}

				Thread.sleep(200);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void onDestroy()
	{
		IntentUtil.startService(this, MonitorService.class);
		super.onDestroy();
	}

}
