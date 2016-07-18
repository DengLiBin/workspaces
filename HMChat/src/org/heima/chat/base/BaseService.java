package org.heima.chat.base;

import org.heima.chat.ChatApplication;

import android.app.Service;

public abstract class BaseService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		
		((ChatApplication)getApplication()).addService(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		((ChatApplication)getApplication()).removeService(this);
	}
}
