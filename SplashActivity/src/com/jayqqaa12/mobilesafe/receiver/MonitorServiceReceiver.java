package com.jayqqaa12.mobilesafe.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.sys.TaskUtil;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationDisplayEngine;
import com.jayqqaa12.mobilesafe.engine.space.AppLockEngine;
import com.jayqqaa12.mobilesafe.engine.traffic.TrafficEngine;
import com.jayqqaa12.mobilesafe.service.AppLockDogService;
import com.jayqqaa12.mobilesafe.service.PhoneNumberService;
import com.jayqqaa12.mobilesafe.service.MonitorService;
import com.jayqqaa12.mobilesafe.service.TrafficService;

public class MonitorServiceReceiver extends AbaseReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		super.onReceive(context, intent);

		Log.i(TAG.RECEIVER, " monitor receiver catch");

		IntentUtil.startService(context, MonitorService.class);
		
		
		
		
		
	}

}
