package com.jayqqaa12.abase.util;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.jayqqaa12.abase.core.AbaseUtil;

/**
 * 
 ******* System Action_View************
 * 
 * @author jayqqaa12
 * @date 2013-5-15
 */
public class SysIntentUtil extends AbaseUtil
{

	public static void install(Context context, File file)
	{
		Intent intent = new Intent();
		intent.setAction(intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	public static void install(Context activity, String path)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
		activity.startActivity(intent);
	}

	// uninstall a apk
	public static void uninstall(Context context, String pkg)
	{
		if (pkg == null) { return; }
		Uri uri = Uri.fromParts("package", pkg, null);
		Intent intent = new Intent(Intent.ACTION_DELETE, uri);
		context.startActivity(intent);
	}

	public static void callPhone(Context context, String number)
	{
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
		context.startActivity(intent);
	}

	public static void goPhone(Context context, String number)
	{
		Uri uri = Uri.parse("tel:" + number);
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		context.startActivity(intent);
	}

	public static void sendSms(Context context, String number, String smsBody)
	{

		Uri smsToUri = Uri.parse("smsto:" + number);

		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

		intent.putExtra("sms_body", smsBody);
		context.startActivity(intent);

	}

	public static void sendSms(Context context, String number)
	{
		Uri smsToUri = Uri.parse("smsto:" + number);
		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
		intent.putExtra("sms_body", "");
		context.startActivity(intent);

	}

	public static void goHome(Context context)
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		context.startActivity(intent);

	}
	
	
	/**
	 *  重启 手机 需要 系统级 签名
	 * @param context
	 */
	public static void reboot(Context context)
	{
		
		Intent intent = new Intent(Intent.ACTION_REBOOT);
    	intent.putExtra("nowait", 1);
    	intent.putExtra("interval", 1);
    	intent.putExtra("window", 0);
    	context.sendBroadcast(intent);
		
	}

	/**
	 * 进入 设置的 管理 app 界面
	 */
	public static void goSettingsManageApp(Context context)
	{
		Intent intent = new Intent();
		intent.setClassName("com.android.settings", "com.android.settings.applications.ManageApplications");
		context.startActivity(intent);
		
	}
	

}
