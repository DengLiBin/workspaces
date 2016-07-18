package com.jayqqaa12.mobilesafe.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.abase.core.AbaseService;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ReceiverUtil;
import com.jayqqaa12.abase.util.comm.NetWorkUtil;
import com.jayqqaa12.abase.util.sys.ApkInfo;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;
import com.jayqqaa12.mobilesafe.app.App;
import com.jayqqaa12.mobilesafe.domain.Traffic;
import com.jayqqaa12.mobilesafe.engine.traffic.TrafficEngine;

public class TrafficService extends AbaseService
{
	@FindEngine
	private TrafficEngine engine;
	// 缓存 对象 保存数据
	private Map<Integer, Traffic> temp = new HashMap<Integer, Traffic>();
	private List<ApkInfo> apks;
	
	@Override
	protected void doTask()
	{
		initApp();
		ReceiverUtil.packageAdded(this, new InstallReceiver());
		statisticsTraffic();
	}

	private void initApp()
	{
		apks = ApkInfoUtil.getAllApps();

	}

	private class InstallReceiver extends AbaseReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			initApp();
		}

	}

	private void initTemp()
	{
		for (ApkInfo<Traffic> info : apks)
		{
			Traffic t = new Traffic();
			t.setUid(info.uid);
			t.setCache(NetWorkUtil.getUidTotalBytes(info.uid));
			t.setDate(new Date());
			temp.put(info.uid, t);
		}
	}

	private void updateTemp()
	{
		for (Traffic t : temp.values())
		{
			long data = NetWorkUtil.getUidTotalBytes(t.getUid()) - t.getCache();

			t.setCache(data + t.getCache());

			if (App.WIFI)
			{
				t.setWifiDay(data + t.getWifiDay());
			}
			else
			{
				t.setMobileDay(data + t.getMobileDay());
			}

		}

	};

	private void statisticsTraffic()
	{
		initTemp();

		while (true)
		{
			try
			{

				updateTemp();

				if (App.NETWORK) engine.updateTrafficData(temp, apks);

				Thread.sleep(1000 * 60);

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}

	}

	@Override
	public void onDestroy()
	{
		IntentUtil.startService(this, MonitorService.class);
		super.onDestroy();
	}

}
