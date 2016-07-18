package com.jayqqaa12.mobilesafe.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.jayqqaa12.abase.core.AbaseApp;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.ReceiverUtil;
import com.jayqqaa12.abase.util.comm.NetWorkUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.receiver.MonitorServiceReceiver;
import com.jayqqaa12.mobilesafe.receiver.SmsReceiver;
import com.jayqqaa12.mobilesafe.service.MonitorService;
import com.jayqqaa12.mobilesafe.ui.MainActivity;

public class App extends AbaseApp
{
	public static boolean WIFI = false;
	public static boolean NETWORK = false;

	@Override
	public void onCreate()
	{
		Log.i(TAG.APP, "application is oncreate");
		super.onCreate();
		// 注册 广播 监听 service 状态 被杀死后 再启动
	//	registRedeiver();
		showNotice();
		// 开启 service
	//	IntentUtil.startService(this, MonitorService.class);

		initNetwork();

	}

	private void initNetwork()
	{
		NETWORK = NetWorkUtil.isConnectingToInternet();
		WIFI = NetWorkUtil.isWifiConnecting();
		
		Log.i(TAG.APP,"wifi is open =" +WIFI );
	}

	private void showNotice()
	{
		NotificationManager manager = ManageUtil.getNotificationManager();
		Notification notification = new Notification(R.drawable.icon, "12叔正在保护您的手机", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;
		Intent intent = new Intent(this, MainActivity.class);

		RemoteViews remoteView = new RemoteViews(this.getPackageName(), R.layout.notification_main);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

		notification.contentIntent = contentIntent;
		notification.contentView = remoteView;
		manager.notify(R.string.app_name, notification);

	}

	private void registRedeiver()
	{
		Log.i(TAG.APP, "regist receiver");
		// sms redeiver
		ReceiverUtil.smsReceived(this, new SmsReceiver());
		// 归属地 显示 redeiver
		ReceiverUtil.timeTick(getContext(), new MonitorServiceReceiver());

	}

}
