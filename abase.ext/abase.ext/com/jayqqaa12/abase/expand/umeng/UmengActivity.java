package com.jayqqaa12.abase.expand.umeng;

import com.jayqqaa12.abase.core.activity.AActivity;
import com.umeng.analytics.MobclickAgent;

public class UmengActivity extends AActivity
{

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(getClass().getSimpleName());
	    MobclickAgent.onResume(this);   
	}
	
	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(getClass().getSimpleName());
		MobclickAgent.onPause(this);
	}
	
}
