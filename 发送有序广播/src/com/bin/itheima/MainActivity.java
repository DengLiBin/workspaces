package com.bin.itheima;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View v){
    	Intent intent=new Intent();
    	//��������㲥
    	//resultReceiver:����Ҫ���嵥�ļ������ã�����㲥������ֻ���ܸ�������㲥�����������һ���յ��ù㲥������һ�������յ��ù㲥
    	sendOrderedBroadcast(intent, null,new MyReceiver(),null,0,"û�˷�һ�ٽ����",null);
    }
    class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String text=getResultData();
			System.out.println("��̰���յ��ļ���"+text);
		}
    	
    }
}
