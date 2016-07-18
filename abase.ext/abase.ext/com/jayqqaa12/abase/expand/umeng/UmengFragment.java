package com.jayqqaa12.abase.expand.umeng;

import com.jayqqaa12.abase.core.fragment.AFragment;
import com.umeng.analytics.MobclickAgent;

public class UmengFragment extends AFragment
{
	
	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(getClass().getSimpleName());
	}
	
	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(getClass().getSimpleName());
	}
}
