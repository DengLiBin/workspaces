package com.itheima.ipdialer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class CallReceiver extends BroadcastReceiver {

	//���յ��㲥ʱ�ͻ����
	@Override
	public void onReceive(Context context, Intent intent) {
		//���IP��·
		//�ڴ�绰�㲥�У���Я������ĵ绰�ĺ��룬ͨ�����´����ȡ��
		String number = getResultData();
		
		if(number.startsWith("0")){
			SharedPreferences sp = context.getSharedPreferences("ip", Context.MODE_PRIVATE);
			String ipNumber = sp.getString("ipNumber", "");
			
			//��IP��·����������û���������ǰ��
			number = ipNumber + number;
			
			//���µĺ������·���㲥��
			setResultData(number);
			
			abortBroadcast();//��ֹ�㲥
		}
		
	}

}
