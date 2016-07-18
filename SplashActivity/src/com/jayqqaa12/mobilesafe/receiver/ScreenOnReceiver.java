package com.jayqqaa12.mobilesafe.receiver;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.media.KeyguardUtil;
import com.jayqqaa12.mobilesafe.engine.space.lost.GPSInfoEngine;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*
 * 当 用户 解锁的时候  remove  gps  listener
 * 
 */
public class ScreenOnReceiver extends AbaseReceiver
{
	private static final String TAG="ScreenOnReceuver";
	@FindEngine
	private LostEngine lostEngine;
	@FindEngine
	private GPSInfoEngine gpsEngine;
	
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		super.onReceive( context, intent);
		
		if(!lostEngine.isOpenService()) return;
		
		Log.i(TAG, "screen  unlock ");
		
		if(!KeyguardUtil.isKeyguardRestricted())
		{
			gpsEngine.stopGPSListener();
		}
		
		
	}
	
	

}
