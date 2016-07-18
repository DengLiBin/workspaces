package com.jayqqaa12.abase.util.comm;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;

import com.jayqqaa12.abase.core.AbaseUtil;
import com.jayqqaa12.abase.util.ManageUtil;

public class GpsUtil extends AbaseUtil
{
	
	public static void openGPS(LocationManager manager)
	{
		if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			openGPS();
		}
	}

	public static boolean isOpenGPS(LocationManager manager)
	{

		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

	}

	public static void openGPS()
	{
		Context context = getContext();
		if (!ManageUtil.getLocationManager().isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			Intent GPSIntent = new Intent();
			GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
			GPSIntent.setData(Uri.parse("custom:3"));
			try
			{
				PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
			}
			catch (CanceledException e)
			{
				e.printStackTrace();
			}

		}
	}
}
