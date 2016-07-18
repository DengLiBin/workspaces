package com.bin.mp3player;

import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer.OnPreparedListener;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

public class MusicService extends Service {
	MediaPlayer player;
	private Timer timer;//��ʱ��
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		player=new MediaPlayer();
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//ֹͣ����
		player.stop();
		//�ͷ�ռ�õ���Դ����ʱplayer�����Ѿ��ϵ���
		player.release();
		player = null;
		if(timer != null){
			timer.cancel();
			timer = null;
		}
				
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MusicController();
	}
	class MusicController extends Binder implements MusicInterface{

		@Override
		public void play() {
			// TODO Auto-generated method stub
			MusicService.this.play();
		}

		@Override
		public void pause() {
			// TODO Auto-generated method stub
			MusicService.this.pause();
		}

		@Override
		public void continuePlay() {
			// TODO Auto-generated method stub
			MusicService.this.continuePlay();
		}

		@Override
		public void seekTo(int progress) {
			// TODO Auto-generated method stub
			
		}
		
	}
	//��������
	public void play(){
		player.reset();
		try{
			//���ض�ý���ļ�
			player.setDataSource("sdcard/abc.mp3");
//			player.setDataSource("http://192.168.13.119:8080/bzj.mp3");
			player.prepare();
			//player.prepareAsync();
			player.start();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
	}
	//��������
	public void continuePlay(){
		player.start();
	}
	//��ͣ����
	public void pause(){
		player.pause();
	}
	
	public void seekTo(int progress){
		player.seekTo(progress);
	}
	public void addTimer(){
		if(timer == null){
			timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					//��ȡ������ʱ��
					int duration = player.getDuration();
					//��ȡ������ǰ���Ž���
					int currentPosition= player.getCurrentPosition();
					
					Message msg = MainActivity.handler.obtainMessage();
					//�ѽ��ȷ�װ����Ϣ������
					Bundle bundle = new Bundle();
					bundle.putInt("duration", duration);
					bundle.putInt("currentPosition", currentPosition);
					msg.setData(bundle);
					MainActivity.handler.sendMessage(msg);
					
				}
				//��ʼ��ʱ������5���룬��һ��ִ��run�������Ժ�ÿ500����ִ��һ��
			}, 5, 500);
		}
	}
}



