package com.jayqqaa12.mobilesafe.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.jayqqaa12.abase.core.AbaseReceiver;

public class ApkReceiver extends AbaseReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		

		if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction()))
		{
			Toast.makeText(context, "有应用被安装"+intent.getData(), Toast.LENGTH_LONG).show();

		}
		else if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction()))
		{
			Toast.makeText(context, "有应用被删除"+intent.getData(), Toast.LENGTH_LONG).show();
			
		}
		else if (Intent.ACTION_PACKAGE_CHANGED.equals(intent.getAction()))
		{
			Toast.makeText(context, "有应用被改变"+intent.getData(), Toast.LENGTH_LONG).show();
		}
		else if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction()))
		{
			Toast.makeText(context, "有应用被替换"+intent.getData(), Toast.LENGTH_LONG).show();
		}
		else if (Intent.ACTION_PACKAGE_RESTARTED.equals(intent.getAction()))
		{
			Toast.makeText(context, "有应用被重启"+intent.getData(), Toast.LENGTH_LONG).show();
		}

	}
	
	
	

}