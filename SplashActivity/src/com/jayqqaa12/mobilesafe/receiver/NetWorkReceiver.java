package com.jayqqaa12.mobilesafe.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.comm.NetWorkUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.mobilesafe.app.App;

public class NetWorkReceiver extends AbaseReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.i(TAG.NETWORK, "网络状态发送 改变了");
		checkNetWork();

	}

	private void checkNetWork()
	{
		// 获得网络连接服务
		ConnectivityManager cm = ManageUtil.getConnectivtyManager();

		if (NetWorkUtil.isWifiConnecting(cm))
		{
			App.WIFI = true;
			App.NETWORK = true;
		}

		else if (NetWorkUtil.isMobileConnecting(cm))
		{
			App.WIFI = false;
			App.NETWORK = true;
		}
		else App.NETWORK = false; // 断线了

	}

}
