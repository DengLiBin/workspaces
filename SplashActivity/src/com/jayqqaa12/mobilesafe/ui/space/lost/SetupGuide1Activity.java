package com.jayqqaa12.mobilesafe.ui.space.lost;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseGestureActivity;
import com.jayqqaa12.mobilesafe.R;

public class SetupGuide1Activity extends AbaseGestureActivity
{
	public static final String  TAG= "SetupGudie1Activity";
	
	@FindView(id = R.id.lost_guide1_ll,gesture=true)
	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide1);
		
		setResult(0,getIntent());
		setChangeActivity(this,null,SetupGuide2Activity.class);
		
	
	}
	
	

}
