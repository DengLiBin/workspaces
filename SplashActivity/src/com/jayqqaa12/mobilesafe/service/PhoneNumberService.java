package com.jayqqaa12.mobilesafe.service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.graphics.PixelFormat;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseService;
import com.jayqqaa12.abase.observer.PhoneNumberObserver;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.comm.PhoneUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.ui.NotificationUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.comm.intercept.InterceptEngine;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationDisplayEngine;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationNumberEngine;

public class PhoneNumberService extends AbaseService
{

	@FindEngine
	private LocationDisplayEngine displayEngine;
	@FindEngine
	private LocationNumberEngine numberEngine;
	@FindEngine
	private InterceptEngine interceptEngine;

	private WindowManager windowmanager;
	private TelephonyManager manager;
	private PhoneStateListener listener;

	
	@Override
	protected void doTask()
	{
		windowmanager = ManageUtil.getWindowManager();
		listener = new MyPhoneListener();
		// 注册系统电话管理服务的监听器
		manager = PhoneUtil.getTelephonyManager();
		manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);		
	}

	

	@Override
	public void onDestroy()
	{
		IntentUtil.startService(this, MonitorService.class);

		super.onDestroy();
		manager.listen(listener, PhoneStateListener.LISTEN_NONE);
		listener = null;
	}

	private class MyPhoneListener extends PhoneStateListener
	{
		private View view;
		private long endTime;
		private long startTime;
		private boolean isIntercept;

		// 电话状态发生改变的时候 调用的方法
		@Override
		public void onCallStateChanged(int state, String incomingNumber)
		{

			super.onCallStateChanged(state, incomingNumber);
			switch (state)
			{
			case TelephonyManager.CALL_STATE_IDLE: // 处于静止状态: 没有呼叫
				stopDisplay();
				// 再获取一次系统的时间
				endTime = System.currentTimeMillis();

				long callTime = endTime - startTime;

				// firewall
				if (interceptEngine.isOpenService())
				{
					// 拦截 响一声 电话
					if (startTime < endTime && callTime > 0 && callTime < 2000 && interceptEngine.isNotice() && !isIntercept
							&& interceptEngine.isWhite(incomingNumber))
					{
						endTime = 0;
						startTime = 0;
						isIntercept = false;
						interceptEngine.showInterceptOnePhoneNotification(PhoneNumberService.this, incomingNumber, "拦截到响一声电话:" + incomingNumber);
						NotificationUtil.deleteMissedCallsNotification();
						getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true,
								new PhoneNumberObserver(new Handler(), incomingNumber, PhoneNumberService.this));
					}
				}

				break;

			case TelephonyManager.CALL_STATE_RINGING: // 零响状态
				Log.i(TAG.SERVICE, "来电号码为" + incomingNumber);

				startTime = System.currentTimeMillis();

				if (displayEngine.isOpenService())
				{
					String address = numberEngine.getAddress(incomingNumber);
					Log.i(TAG.SERVICE, "归属地为" + address);
					showLoactionToast(address);
				}
				// intercept service
				isIntercept = interceptEngine.interceptNumber(incomingNumber, null, null);
			
				// delete number log
				if (isIntercept)
				{
					getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true,
							new PhoneNumberObserver(new Handler(), incomingNumber, PhoneNumberService.this));
				}

				stopToast();
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 接通电话状态

				break;
			}

		}

		/**
		 * 根据电话号码删除呼叫记录
		 * 
		 * @param incomingNumber
		 *            要删除呼叫记录的号码
		 */

		private void stopToast()
		{

			int timeout = displayEngine.getDisplayTimeout();

			Log.i(TAG.SERVICE, "timeout is =" + timeout);

			if (timeout > 0)
			{
				Executors.newSingleThreadScheduledExecutor().schedule(new Runnable()
				{
					@Override
					public void run()
					{
						stopDisplay();
					}
				}, timeout, TimeUnit.SECONDS);

			}

		}

		private void stopDisplay()
		{
			if (view != null)
			{
				windowmanager.removeView(view);
				view = null;
			}
		}

		/**
		 * 在窗体上显示出来位置信息
		 * 
		 * @param address
		 */
		private void showLoactionToast(String address)
		{
			Log.i(TAG.SERVICE, "show toast");
			// 自定义 Toast window 级别
			WindowManager.LayoutParams params = new LayoutParams();
			params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			params.width = WindowManager.LayoutParams.WRAP_CONTENT;
			params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
			params.format = PixelFormat.TRANSLUCENT;
			params.type = WindowManager.LayoutParams.TYPE_TOAST;
			params.setTitle("Toast");
			params.gravity = Gravity.LEFT | Gravity.TOP;
			params.x = displayEngine.getDragX();
			params.y = displayEngine.getDragY();

			view = View.inflate(getApplicationContext(), R.layout.toast_location, null);

			LinearLayout ll = (LinearLayout) view.findViewById(R.id.toast_location_ll);
			ll.setBackgroundResource(displayEngine.getDisplayStyleDrwable());

			TextView tv = (TextView) view.findViewById(R.id.toast_location_lv);
			tv.setText(address);
			windowmanager.addView(view, params);
		}

	}


}
