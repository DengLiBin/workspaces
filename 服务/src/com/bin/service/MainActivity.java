package com.bin.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	private Intent intent,intent2;
	private MyServiceConn conn;
	PMin pm;//�ӿڵ��������
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=new Intent(this,MyService.class);
        intent2=new Intent(this,LeadService.class);
        
        conn = new MyServiceConn();
      //���쵼����LeadService��
        this.bindService(intent2, conn, this.BIND_AUTO_CREATE);
    }
    public void banZheng(View v){
    	
        pm.QianXian();
    }
    public void clickStart(View v){
    	//��ʽ��ָ���˷������������������,���÷����onCreate��onStartCommand������
    	this.startService(intent);
    }
    public void clickStop(View v){
    	//���÷����onDestroy����
    	this.stopService(intent);
    }
    
    public void bind(View v){
    	//�󶨷��񣬵��÷����onCreate��onBind����
    	this.bindService(intent, conn, BIND_AUTO_CREATE);
    }
    public void unbind(View v){
    	//��󣬵��÷����onUnBind��onDestroy����
    	this.unbindService(conn);
    }
    
    class MyServiceConn implements ServiceConnection{
    	
    	//�������ӳɹ�ʱ���˷�������
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			pm=(PMin) service;
		}
		
		//����ʧȥ����ʱ���˷�������
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
