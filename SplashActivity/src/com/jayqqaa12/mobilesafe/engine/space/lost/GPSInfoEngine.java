package com.jayqqaa12.mobilesafe.engine.space.lost;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.comm.GpsUtil;
import com.jayqqaa12.abase.util.comm.PhoneUtil;
import com.jayqqaa12.abase.util.comm.SmsUtil;
import com.jayqqaa12.abase.util.security.ValidateUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;

public class GPSInfoEngine extends AbaseEngine
{
	private static final String TAG = "GPSInfoService";
	private LocationManager manager;
	private static MyLoactionListener listener;

	// 获取gps 信息
	public synchronized String getLocation() throws Exception
	{
		GpsUtil.openGPS();
		manager = ManageUtil.getLocationManager();
		String provider = getBestProvider(manager);

		provider = getBestProvider(manager);

		Log.i(TAG, " provider is = " + provider);

		if (provider == null)
		{
			// 如果 provider gps 找不到 只好随便给 它一个
			List<String> providers = manager.getProviders(true);
			provider = providers.get(0);
		}
		Log.i(TAG, " provider is = " + provider);
		manager.requestLocationUpdates(provider, 60000, 50, getListener());
		
		if (provider == null) return null;
		return ConfigSpUtil.getString(Config.PROTECTED_LOCATION, "");
	}

	
	public  void sendSmsGPS(String sender)
	{
		
		String location ="";
		try
		{
			String google= getContext().getString(R.string.instruction_google_gps_http);
			location = getLocation();
			if(!ValidateUtil.isValid(location)) location="正在 获取 gps ,请 稍后 重试"; 
			location= google+location;
			
		}
		catch (Exception e)
		{
			location ="正在 获取 gps ,请 稍后 重试";
			e.printStackTrace();
		}
		if (ValidateUtil.isValid(location) && PhoneUtil.havaSimCard())
		{
			Log.i(TAG, "location is =" + location);
			SmsUtil.sendSms(sender, location);
			
		}
	}
	
	
	
	/**
	 * \
	 * 
	 * @param manager
	 *            位置管理服务
	 * @return 最好的位置提供者
	 */
	private String getBestProvider(LocationManager manager)
	{
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
		criteria.setSpeedRequired(true);
		criteria.setCostAllowed(true);
		return manager.getBestProvider(criteria, true);
	}

	public void stopGPSListener()
	{
		if (listener != null)
			manager.removeUpdates(listener);
	}

	private synchronized MyLoactionListener getListener()
	{
		if (listener == null)
		{
			listener = new MyLoactionListener();
		}
		return listener;
	}

	private class MyLoactionListener implements LocationListener
	{
		/**
		 * 当手机位置发生改变的时候 调用的方法
		 */
		public void onLocationChanged(Location location)
		{
			String latitude = location.getLatitude() + ""; // weidu
			String longitude = location.getLongitude() + ""; // jingdu

			ConfigSpUtil.setValue("location", latitude + "," + longitude);

		}

		/**
		 * 某一个设备的状态发生改变的时候 调用 可用->不可用 不可用->可用
		 */
		public void onStatusChanged(String provider, int status, Bundle extras)
		{

		}

		/**
		 * 某个设备被打开
		 */
		public void onProviderEnabled(String provider)
		{

		}

		/**
		 * 某个设备被禁用
		 * 
		 */
		public void onProviderDisabled(String provider)
		{

		}

	}

}
