package com.bin.otification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void click(View v){
		Intent intent=new Intent(MainActivity.this,OtherActivity.class);
		
		PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
		
		//创建一个Notification
		Notification notify=new Notification();
		//设置图标
		notify.icon=R.drawable.icon_150;
		//设置文本内容
		notify.tickerText="启动OtherActivity的通知";
		//设置发送的时间
		notify.when=System.currentTimeMillis();
		//设置声音
		notify.defaults=Notification.DEFAULT_SOUND;
		//为Notification设置默认声音，默认震动，默认闪光灯
		notify.defaults=Notification.DEFAULT_ALL;
		//设置事件信息
		notify.setLatestEventInfo(MainActivity.this,"普通通知","点击查看",pi); 
	
		
		//获取系统的NotificationManager服务
		NotificationManager nm=(NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		
		nm.notify(2, notify);
	}
	
}
