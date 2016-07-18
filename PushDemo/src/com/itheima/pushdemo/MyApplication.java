package com.itheima.pushdemo;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;

/**
 * �Զ���application
 * 
 * @author Kevin
 * 
 */
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("Ӧ�ô�����....");
		
		JPushInterface.setDebugMode(true);
	    JPushInterface.init(this);
	}
	
	public void doSomething() {
		System.out.println("do something...");
	}

}
