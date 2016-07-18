package com.jayqqaa12.mobilesafe.ui.process;

import android.os.Bundle;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.mobilesafe.R;

public class SettingActivity extends AbaseActivity
{
	@FindView(id = R.id.title_1_tv,textId=R.string.process_setting_label)
	private TextView tv;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process_settings);
		
		
	}

}
