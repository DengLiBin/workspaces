package com.jayqqaa12.mobilesafe.receiver;

import android.content.Context;
import android.content.Intent;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.comm.SmsUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationDisplayEngine;
import com.jayqqaa12.mobilesafe.engine.space.AppLockEngine;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;
import com.jayqqaa12.mobilesafe.engine.traffic.TrafficEngine;
import com.jayqqaa12.mobilesafe.service.AppLockDogService;
import com.jayqqaa12.mobilesafe.service.PhoneNumberService;
import com.jayqqaa12.mobilesafe.service.MonitorService;
import com.jayqqaa12.mobilesafe.service.TrafficService;

/*
 * 开机 的Receiver 
 * 
 * 
 */
public class BootCompletedReceiver extends AbaseReceiver
{
	
	
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		super.onReceive( context, intent);

		IntentUtil.startService(context, MonitorService.class);
	}
	
	
	
}
