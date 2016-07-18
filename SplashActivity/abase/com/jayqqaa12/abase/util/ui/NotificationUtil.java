package com.jayqqaa12.abase.util.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.os.RemoteException;
import android.provider.CallLog.Calls;

import com.android.internal.telephony.ITelephony;
import com.jayqqaa12.abase.core.AbaseUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.comm.PhoneUtil;

/**
 * 
 * 信息管理器
 *
 */
public class NotificationUtil  extends AbaseUtil
{
	


	

	public static void showNotification(int iconId, String notifiTitile, long when, String title, String desc, PendingIntent contentIntent,
			Context context)
	{
		NotificationManager manager = ManageUtil.getNotificationManager();
		Notification notification = new Notification(iconId, notifiTitile, when);
		// sound and vibrate and led light
		notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(context, title, desc, contentIntent);
		manager.notify(0, notification);
	}

	public static void deleteMissedCallsNotification()
	{

		try
		{

			ITelephony telephony = PhoneUtil.getITelephony();
			if (telephony != null)
			{
				telephony.cancelMissedCallsNotification();
				//修改 数据库记录  
 				StringBuilder where = new StringBuilder("type=");
				where.append(Calls.MISSED_TYPE);
				where.append(" AND new=1");
				ContentValues values = new ContentValues(1);
				values.put(Calls.NEW, "0");
				getContext().getContentResolver().update(Calls.CONTENT_URI, values, where.toString(), null);
				
			}
			
			

		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}

	}

}
