package com.jayqqaa12.mobilesafe.ui.space.lost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseGestureActivity;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;

public class SetupGuide2Activity extends AbaseGestureActivity
{
	private static final String TAG ="SetupGudie2Activity"; 
	
	@FindView(id = R.id.lost_guide2_ll,gesture=true)
	private LinearLayout ll;
	@FindView(id = R.id.lost_guide2_bt_bind,click=true)
	private Button bt_bind;
	@FindView(id = R.id.lost_guide2_cb_bind)
	private CheckBox cb_bind;
	
	@FindEngine
	private LostEngine engine;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide2);
		
		// change activity
		setChangeActivity(this, SetupGuide1Activity.class, SetupGuide3Activity.class);
	}
	
	
	@Override
	protected void onResume()
	{
		super.onResume();
		// init view context
		initViewContext();
	}
	
	

	private void initViewContext()
	{
		Log.i(TAG,"bind states is " +engine.isSimBind());
		
		if (engine.isSimBind())
		{
			bt_bind.setText(getString(R.string.lost_lable_guide2_off_bind));
			cb_bind.setText(getString(R.string.lost_lable_guide2_already_bind));
			cb_bind.setChecked(true);
		}
		else
		{
			bt_bind.setText(getString(R.string.lost_lable_guide2_on_bind));
			cb_bind.setText(getString(R.string.lost_lable_guide2_unbind));
			cb_bind.setChecked(false);
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lost_guide2_bt_bind:
			
			if (!engine.isSimBind())
			{
				Log.i(TAG,"bind");
				engine.setSimBind(true);
			}
			else
			{
				Log.i(TAG,"relieve bind");
				engine.setSimBind(false);
			}
			initViewContext();
			break;
		default:
			break;
		}

	}

}
