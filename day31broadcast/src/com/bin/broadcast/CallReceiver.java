package com.bin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * ip����
 * @author Administrator
 *
 */
public class CallReceiver extends BroadcastReceiver {
	//���յ��㲥ʱ�����
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("adv","abc��绰�㲥����");
		//���IP��·
		//�ڴ�绰�㲥�У���Я������ĵ绰�ĺ��룬ͨ�����´����ȡ��
		String number=this.getResultData();
		SharedPreferences sp=context.getSharedPreferences("ip",Context.MODE_PRIVATE);
		String ipNumber=sp.getString("ipNumber","");
		//��IP��·����������û���������ǰ��
		number=ipNumber+number;
		//���µĺ������·���㲥��
		setResultData(number);
	}

}
