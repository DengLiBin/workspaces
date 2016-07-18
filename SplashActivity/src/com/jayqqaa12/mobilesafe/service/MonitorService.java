package com.jayqqaa12.mobilesafe.service;

import java.util.List;

import android.app.ActivityManager.RunningServiceInfo;
import android.util.Log;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseService;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.comm.SmsUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.sys.TaskUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.comm.intercept.InterceptEngine;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationDisplayEngine;
import com.jayqqaa12.mobilesafe.engine.space.AppLockEngine;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;
import com.jayqqaa12.mobilesafe.engine.traffic.TrafficEngine;

public class MonitorService extends AbaseService
{

	@FindEngine
	private LostEngine lostEngine;
	@FindEngine
	private LocationDisplayEngine displayEngine;
	@FindEngine
	private AppLockEngine lockEngine;
	@FindEngine
	private TrafficEngine trafficEngine;
	@FindEngine
	private InterceptEngine interceptEngine;

	List<RunningServiceInfo> runnings = TaskUtil.getRunningServices();

	
	@Override
	protected void doTask()
	{

		Log.i(TAG.SERVICE, "monitor  service  is start");
		// SIM 变更 事件
		if (lostEngine.isOpenService())
		{
			// sim change proceted
			if (lostEngine.isSimChange())
			{
				String destinationAddress = lostEngine.getProtectedNumber();
				SmsUtil.sendSms(destinationAddress, MonitorService.this.getString(R.string.lost_sms_sim_change));
			}
			// uninstall proceted
			if (lostEngine.isOpenUninstallProtectedService())
			{
				if (!isRunningService(LogService.class.getName()))
				{
					Log.i(TAG.SERVICE, " log service is restart");
					IntentUtil.startService(MonitorService.this, LogService.class);
				}
			}

		}
		if (displayEngine.isOpenService() || interceptEngine.isOpenService())
		{
			if (!isRunningService(PhoneNumberService.class.getName()))
			{
				Log.i(TAG.SERVICE, " phonenumber service is restart");
				IntentUtil.startService(MonitorService.this, PhoneNumberService.class);
			}
		}
		if (lockEngine.isOpenService())
		{
			if (!isRunningService(AppLockDogService.class.getName()))
			{
				Log.i(TAG.SERVICE, " app lock service is restart");
				IntentUtil.startService(MonitorService.this, AppLockDogService.class);
			}
		}
		if (trafficEngine.isOpenService())
		{
			if (!isRunningService(TrafficService.class.getName()))
			{
				Log.i(TAG.SERVICE, " app traffic service is restart");
				IntentUtil.startService(MonitorService.this, TrafficService.class);

			}
		}
		MonitorService.this.stopSelf();

	}

	private boolean isRunningService(String serviceName)
	{
		for (RunningServiceInfo service : runnings)
		{
			if (service.service.getClassName().equals(serviceName)) { return true; }
		}

		return false;
	}

}
