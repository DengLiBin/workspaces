package com.bin.servicebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver  {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action=intent.getAction();
		if(Intent.ACTION_SCREEN_OFF.equals(action)){
			System.out.println("��Ļ��");
			Log.i("tag", "��Ļ��");
		}
		else if(Intent.ACTION_SCREEN_ON.equals(action)){
			System.out.println("��Ļ�ر�");
			Log.i("tag", "��Ļ�ر�");
		}
		
	}

}
