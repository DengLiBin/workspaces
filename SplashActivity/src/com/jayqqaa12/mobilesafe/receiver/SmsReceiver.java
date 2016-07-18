package com.jayqqaa12.mobilesafe.receiver;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.SysUtil;
import com.jayqqaa12.abase.util.comm.SmsUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.media.KeyguardUtil;
import com.jayqqaa12.abase.util.media.SoundUtil;
import com.jayqqaa12.abase.util.security.ValidateUtil;
import com.jayqqaa12.abase.util.ui.ScreenUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.comm.intercept.InterceptEngine;
import com.jayqqaa12.mobilesafe.engine.space.lost.GPSInfoEngine;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;
import com.jayqqaa12.mobilesafe.service.RebootService;

public class SmsReceiver extends AbaseReceiver
{
	@FindEngine
	private LostEngine lostEngine;
	@FindEngine
	private InterceptEngine interceptEngine;
	@FindEngine
	private GPSInfoEngine gpsEngine;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		
		super.onReceive(context, intent);
		Log.i(TAG.RECEIVER,"sms receiver catch");

		Object[] pdus = SmsUtil.getPdus(intent);
		for (Object pdu : pdus)
		{
			SmsMessage sms = SmsUtil.getSmsMessage(pdu);
			String contents = sms.getMessageBody();
			String sender = sms.getOriginatingAddress();

			// 是否 开 保护了 不然 不运行
			if (lostEngine.isOpenService())
			{
				if (!ValidateUtil.isValid(contents)) return;

				String[] args = contents.split("#");
				String pwd = args[1];
				String content = args[0];
				Log.i(TAG.RECEIVER, "pwd =" + pwd);
				Log.i(TAG.RECEIVER, "content =" + content);

				// 判断 密码 是否 正确
				if (!lostEngine.parsePassword(pwd)) return;

				if (context.getString(R.string.instruction_location).equals(content))
				{
					// 终止广播
					abortBroadcast();
					if (!KeyguardUtil.isKeyguardRestricted())
					{
						SmsUtil.sendSms(sender, context.getString(R.string.lost_sms_gps_use));
						// TODO 开启 关闭屏幕 receiver 获取 gps
						Log.i(TAG.RECEIVER, context.getString(R.string.lost_sms_gps_use));
						return;
					}
					gpsEngine.sendSmsGPS(sender);
					return;

				}
				else if (context.getString(R.string.instruction_at_once_location).equals(content))
				{
					// 终止广播
					abortBroadcast();
					gpsEngine.sendSmsGPS(sender);
					return;
				}

				else if (context.getString(R.string.instruction_lockscreen).equals(content))
				{
					abortBroadcast();
					ScreenUtil.setLockScreen(context.getString(R.string.instruction_lock_screen_pwd));
					return;
				}
				else if (context.getString(R.string.instruction_wipeData).equals(content))
				{
					abortBroadcast();
					SysUtil.wipeData();
					return;
				}
				else if (context.getString(R.string.instruction_alram).equals(content))
				{
					abortBroadcast();
					SoundUtil.playSound(R.raw.ylzs, 1.0f);
					return;
				}
				else if (context.getString(R.string.instruction_reboot).equals(content))
				{
					abortBroadcast();
					IntentUtil.startService(context,RebootService.class);
					
				}
				
				

			}
			// intercept
			if (interceptEngine.isOpenService())
			{

				interceptEngine.interceptSms(sms, this);

			}

		}

	}

}
