
package com.itheima.sdlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SDStatusReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//�ж��յ��ĵ�����ʲô�㲥
		String action = intent.getAction();
		if("android.intent.action.MEDIA_MOUNTED".equals(action)){
			Toast.makeText(context, "SD������", 0).show();
		}
		else if("android.intent.action.MEDIA_REMOVED".equals(action)){
			Toast.makeText(context, "SD���γ�", 0).show();
		}
		else if("android.intent.action.MEDIA_UNMOUNTED".equals(action)){
			Toast.makeText(context, "SD��������", 0).show();
		}

	}

}
