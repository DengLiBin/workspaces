package com.jayqqaa12.abase.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View.OnCreateContextMenuListener;

public class AbaseReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Abase.init(this);
		
	}

	
	
	
}
