package com.bin.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LeadService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		// ����һ��Binder���������������м��˶���
		return new ZhouMi();
	}
	
	public static void banzheng(){
		System.out.println("�쵼��������֤");
	}
	class ZhouMi extends Binder implements PMin{
		@Override
		public void QianXian(){
			LeadService.banzheng(); 
		}
		public  void daMaJiang(){
			System.out.println("���쵼���齫");
		}
	}

}
