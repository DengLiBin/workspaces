package com.bin.clipdrawable;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView imageView=(ImageView) findViewById(R.id.image);
		//获取图片显示的ClipDrawable对象
		final ClipDrawable drawable=(ClipDrawable) imageView.getDrawable();
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				if(0==msg.what){
					//修改ClipDrawable的level值
					drawable.setLevel(drawable.getLevel()+200);
				}
			}
		};
		
		final Timer timer=new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
					Message msg=new Message();
					msg.what=0;
					handler.sendMessage(msg);//发送消息
					//取消定时器
					if(drawable.getLevel()>=10000){
						timer.cancel();
					}
			}
			
		},0,300);
	}

}
