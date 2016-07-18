package com.jayqqaa12.abase.util.comm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.CallLog;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;
import com.jayqqaa12.abase.core.AbaseUtil;
import com.jayqqaa12.abase.util.security.ValidateUtil;

public class PhoneUtil extends AbaseUtil
{

	public static TelephonyManager getTelephonyManager()
	{
		return (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
	}

	
	public static String getSimSerialNumber()
	{
		TelephonyManager manager = getTelephonyManager();

		String simserial = manager.getSimSerialNumber();

		return simserial;
	}

	
	public static boolean havaSimCard()
	{
		return ValidateUtil.isValid(getSimSerialNumber());
	}

	
	/**
	 * sim 
	 * @return
	 */
	public static boolean isCanUseSim()
	{
		try
		{
			TelephonyManager mgr = getTelephonyManager();
			return TelephonyManager.SIM_STATE_READY == mgr.getSimState();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	
	public static ITelephony getITelephony()
	{

		ITelephony telephony = null;
		try
		{
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
			IBinder binder;
			binder = (IBinder) method.invoke(null, new Object[] { Context.TELEPHONY_SERVICE });

			telephony = ITelephony.Stub.asInterface(binder);

		}
		catch (NoSuchMethodException e1)
		{
			e1.printStackTrace();
		}
		catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}

		return telephony;
	}

	
	/**
	 * 挂 电话   
	 * 
	 */
	public static void endPhone()
	{

		try
		{
			ITelephony telephony = getITelephony();
			telephony.endCall();

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

	public static void deleteCallLog(String incomingNumber)
	{
		ContentResolver resolver = getContext().getContentResolver();
		Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, "number=?", new String[] { incomingNumber }, null);
		if (cursor.moveToFirst())
		{// 查询到了呼叫记录
			String id = cursor.getString(cursor.getColumnIndex("_id"));
			resolver.delete(CallLog.Calls.CONTENT_URI, "_id=?", new String[] { id });
		}

	}

}
