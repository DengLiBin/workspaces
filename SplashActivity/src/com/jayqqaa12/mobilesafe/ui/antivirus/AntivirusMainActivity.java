package com.jayqqaa12.mobilesafe.ui.antivirus;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.antivirus.AntivirusEngine;

public class AntivirusMainActivity extends AbaseActivity
{

	@FindView(id = R.id.title_3_tv, textId = R.string.antivirus_label)
	private TextView tv_title;
	@FindView(id = R.id.title_3_bt, click = true)
	private Button bt_setting;
	@FindView(id = R.id.iv)
	private ImageView iv;
	@FindView(id = R.id.sv)
	private ScrollView sv;
	@FindView(id = R.id.ll)
	private LinearLayout ll_info;
	@FindView(id = R.id.bt_group_bt_1, click = true,textId=R.string.antivirus_label_kill)
	private Button bt_start;
	@FindView(id = R.id.bt_group_bt_2, click = true,textId=R.string.stop)
	private Button bt_stop;
	
	@FindEngine
	private AntivirusEngine engine;

	private AnimationDrawable anim;

	private Handler handler = new Handler()
	{

		public void handleMessage(android.os.Message msg)
		{

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_antivirus);

		anim = (AnimationDrawable) iv.getDrawable();

	}

	@Override
	public void onClick(View v)
	{
		
		switch (v.getId())
		{
		case R.id.title_3_bt:
			// into settings
			IntentUtil.startIntent(this, SettingsActivity.class);
			
			break;
		case  R.id.bt_group_bt_1:
			// start kill
			anim.start();
			
			
			
			
			
			
			break;
			
		case  R.id.bt_group_bt_2:
			// stop kill

			anim.stop();
			
			
			
			break;


		default:
			break;
		}
	
	}

}
