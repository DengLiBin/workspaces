package com.libin.googleplay;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class BaseActivity extends  ActionBarActivity {
	// �������е����е�activity
	public final static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		synchronized (mActivities) {
			mActivities.add(this);
		}
		init();
		initView();
		initActionBar();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		synchronized (mActivities) {
			mActivities.remove(this);
		}	
	}
	
	public void killAll() {
		// ������һ��mActivities ����
		List<BaseActivity> copy;
		synchronized (mActivities) {
			copy = new LinkedList<BaseActivity>(mActivities);
		}
		for (BaseActivity activity : copy) {
			activity.finish();
		}
		// ɱ����ǰ�Ľ���
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	protected void initActionBar() {
	}
	protected void initView() {
	}
	protected void init() {
	}
	
}
