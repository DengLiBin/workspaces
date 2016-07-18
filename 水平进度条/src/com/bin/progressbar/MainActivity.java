package com.bin.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	int status=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ProgressBar bar=(ProgressBar) findViewById(R.id.bar);
		//����һ��������½��ȵ�Handler
		final Handler handler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch(msg.what){
				case 1:
					bar.setProgress(status);
					status+=10;
					break;
				default:
					break;
				}
			}
		};
		
		//�����߳���ִ������
		new Thread(){
			public void run() {
				while(status<=100){
					try {
						Thread.sleep(1000L);
						Message message=new Message();
						message.what=1;
						handler.sendMessage(message);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
