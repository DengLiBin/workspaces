package com.bin.callrecord;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RecoderService extends Service {
	private MediaRecorder recorder;//¼��API������¼����������Ƶ
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//�õ��绰������
		TelephonyManager tm=(TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
		//�����绰״̬
		//events������PhoneStateListener����ʲô����
		tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	class MyListener extends PhoneStateListener{
		//һ���绰״̬�ı䣬�˷�������
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			switch(state){
			case TelephonyManager.CALL_STATE_IDLE:
				System.out.println("����");
				if(recorder!=null){
					recorder.stop();
					recorder.release();
					recorder=null;
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				System.out.println("����");
				if(recorder==null){
					recorder=new MediaRecorder();
					recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					recorder.setOutputFile("sdcard/luyin.3gp");
					recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					try{
						recorder.prepare();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				System.out.println("ժ��");
				//��ʼ¼��
				if(recorder!=null){
					recorder.start();
				}
				break;
			}
		}
		
	}
	
}
