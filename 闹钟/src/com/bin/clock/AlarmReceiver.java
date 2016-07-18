package com.bin.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
			System.out.println("闹钟执行了");
			
			AlarmManager am=(AlarmManager)context.getSystemService(context.ALARM_SERVICE);//拿到闹钟服务
			//取消闹钟
			am.cancel(PendingIntent.getBroadcast(context,getResultCode(),new Intent(context,AlarmReceiver.class),0));
			
			Intent in=new Intent(context,PlayAlarmAty.class);
			in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(in);//启动Activity并播放音乐
			
			
	}

}
