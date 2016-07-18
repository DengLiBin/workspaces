package com.bin.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
			System.out.println("����ִ����");
			
			AlarmManager am=(AlarmManager)context.getSystemService(context.ALARM_SERVICE);//�õ����ӷ���
			//ȡ������
			am.cancel(PendingIntent.getBroadcast(context,getResultCode(),new Intent(context,AlarmReceiver.class),0));
			
			Intent in=new Intent(context,PlayAlarmAty.class);
			in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(in);//����Activity����������
			
			
	}

}
