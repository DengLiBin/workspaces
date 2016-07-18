package com.jayqqaa12.mobilesafe.receiver;

import android.content.Context;
import android.content.Intent;

import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.ui.space.lost.LostMainActivity;

public class CallPhoneReceiver extends AbaseReceiver
{
	/*
	 *  输入  #3838438  运行 手机防盗主界面
	 *  input number  #3838438 running this program
	 */
	@Override
	public void onReceive(Context context, Intent intent)
	{
		super.onReceive( context, intent);
		
		String number = getResultData();
		if(context.getString(R.string.instruction_open_program).equals(number)){
			
			Intent lostintent = new Intent(context,LostMainActivity.class);
			lostintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//指定要激活的activity在自己的任务栈里面运行 
			context.startActivity(lostintent);
			// 终止掉这个电话 
			// 不能通过  abortBroadcast();
			setResultData(null);
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	

}
