package com.jayqqaa12.abase.expand.umeng;

import com.jayqqaa12.abase.core.fragment.AFragmentActivity;
import com.umeng.analytics.MobclickAgent;

public class UmengFragmentActivity extends AFragmentActivity
{
	
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onResume(this);      
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPause(this);
	}

}
