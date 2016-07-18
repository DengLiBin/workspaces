package com.bin.mp3player;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends Activity {
	
	private static SeekBar sb;
	MusicInterface mi;
	MyServiceConn conn;
	Intent intent;
	static Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			int duration = bundle.getInt("duration");
			int currentPostition = bundle.getInt("currentPosition");
			//ˢ�½������Ľ���
			sb.setMax(duration);
			sb.setProgress(currentPostition);
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb = (SeekBar) findViewById(R.id.sb);
        
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//�����϶��Ľ��ȸı����ֲ��Ž���
				int progress = seekBar.getProgress();
				//�ı䲥�Ž���
				mi.seekTo(progress);
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
		});
        
        intent=new Intent(this,MusicService.class);
        this.startService(intent);
        conn=new MyServiceConn();
        this.bindService(intent, conn, this.BIND_AUTO_CREATE);
    }
    class MyServiceConn implements ServiceConnection{
    	
    	//�������ӽ���ʱ���ô˷���
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mi=(MusicInterface) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    public void play(View v){
    	mi.play();
    }
    public void continuePlay(View v){
		mi.continuePlay();
	}
	public void pause(View v){
		mi.pause();
	}
	public void exit(View v){
		unbindService(conn);
		stopService(intent);
	}
}
