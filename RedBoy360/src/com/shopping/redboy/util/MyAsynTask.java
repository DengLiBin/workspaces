package com.shopping.redboy.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;

public abstract class MyAsynTask {
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			onPostExecute();
		};
	};
	
	public void execute(){
		onPreExecute();
		new Thread(){
			public void run() {
				doInBackground();
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
	/**
	 * 耗时操作执行前处理
	 */
	public abstract void onPreExecute();
	/**
	 * 执行耗时操作
	 */
	public abstract void doInBackground();
	/**
	 * 耗时操作执行后处理
	 */
	public abstract void onPostExecute();
	
}
