package com.bin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

//�������ʱϵͳ�����ģ������Լ�newһ�����������Activity�����ò����������ġ�
public class MyService extends Service {
	//��ʱ����,���ص���һ���м�����ڷ������ͷ��ʸ÷����Activity֮��Ķ��󣨲���Ҫ�Ķ����ֱ�ӷ���null��
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onBind����");
		return null;
	}
	//���ʱ����
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("unbind����");
		return super.onUnbind(intent);
	}
	//��������,�ڰ󶨣�onBind������Ϳ�ʼ����(onStartCommand)֮ǰ����
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("create����");
	}

	//��������
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("startCommand����");
		return super.onStartCommand(intent, flags, startId);
	}
	
	//ֹͣ������ô˷���
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("destroy����");
	}


}
/*#���������������ʹ��
* �÷���ʵ�����ֲ���ʱ����Ϊ���ֲ��ű��������ڷ�������У���Activity�޹أ����������ַ����еķ�������Ҫ��ǰ̨Activity����Activity�йأ������ã�������Ҫ����������ַ���
* ��start����bind������ʱ��unbind����stop
* */
