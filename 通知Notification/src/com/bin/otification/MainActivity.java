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
		
		//����һ��Notification
		Notification notify=new Notification();
		//����ͼ��
		notify.icon=R.drawable.icon_150;
		//�����ı�����
		notify.tickerText="����OtherActivity��֪ͨ";
		//���÷��͵�ʱ��
		notify.when=System.currentTimeMillis();
		//��������
		notify.defaults=Notification.DEFAULT_SOUND;
		//ΪNotification����Ĭ��������Ĭ���𶯣�Ĭ�������
		notify.defaults=Notification.DEFAULT_ALL;
		//�����¼���Ϣ
		notify.setLatestEventInfo(MainActivity.this,"��֪ͨͨ","����鿴",pi); 
	
		
		//��ȡϵͳ��NotificationManager����
		NotificationManager nm=(NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		
		nm.notify(2, notify);
	}
	
}
