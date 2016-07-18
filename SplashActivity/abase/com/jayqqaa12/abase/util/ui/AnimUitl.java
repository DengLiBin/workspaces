package com.jayqqaa12.abase.util.ui;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

import com.jayqqaa12.abase.core.AbaseUtil;

public class AnimUitl extends AbaseUtil
{
	
	
	public static  void swing(View view,AnimationListener listener)
	{
		TranslateAnimation ta = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		ta.setDuration(200);
		ta.setAnimationListener(listener);		
		view.startAnimation(ta);
		
	}

	public static  void swing(View view)
	{
		TranslateAnimation ta = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		ta.setDuration(200);
		view.startAnimation(ta);
		
	}
	
	

}
