package com.jayqqaa12.mobilesafe.ui.space.lost;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.Direction;
import com.jayqqaa12.abase.core.activity.AbaseGestureActivity;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;
import com.jayqqaa12.mobilesafe.receiver.MyAdmin;

public class SetupGuide4Activity extends AbaseGestureActivity
{
	private static final String TAG = "SetupGudie4Activity";
	
	@FindView(id = R.id.lost_guide4_ll,gesture=true)
	private LinearLayout ll;
	@FindView(id = R.id.lost_guide4_cb_open_protecting ,checkedChange=true)
	private CheckBox cb_open_protected;
	@FindEngine
	private LostEngine engine;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide4);
		engine.setupOver();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		initView();
	}
	
	
	private void initView()
	{
		Log.i(TAG, "Is protected " + engine.isOpenService());
		if (engine.isOpenService())
		{
			cb_open_protected.setChecked(true);
			cb_open_protected.setText(getString(R.string.lost_lable_guide2_already_bind));
		}
		else
		{
			cb_open_protected.setChecked(false);
			cb_open_protected.setText(getString(R.string.lost_lable_on_protected));
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton view, boolean isChecked)
	{

		switch (view.getId())
		{
		case R.id.lost_guide4_cb_open_protecting:

			Log.i(TAG, "on checked changed is" + isChecked);
			if (isChecked)
			{
				engine.setProtected(true);
			}
			else
			{
				engine.setProtected(false);
				showHintDialog();
			}

			initView();
			break;

		default:
			break;
		}

	}

	private void showHintDialog()
	{
		Log.i(TAG, "show dialog");
		AlertDialog.Builder builder = new Builder(SetupGuide4Activity.this);
		builder.setTitle("提醒");
		builder.setMessage("强烈建议开启手机防盗,是否完成设置?");

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				engine.setProtected(true);
				registAdminDevice();
				return;
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
		{

			public void onClick(DialogInterface dialog, int which)
			{
				return;
			}
		});

		builder.create().show();
		return;

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		Log.i(SetupGuide1Activity.TAG, "parent is on fling");
		Direction dir = getGestureDirection(e1, e2);

		if (dir == Direction.LEFT)
		{
			showHintDialog();
		}
		else if (dir == Direction.RIGHT)
		{
			changeActivity(this, SetupGuide3Activity.class, null, dir);
		}

		return false;
	}

	private void registAdminDevice()
	{
		if (engine.isOpenService())
		{
			finish();
			ManageUtil.registAdminDevice(this,MyAdmin.class);
		}
		else
		{
			finish();
		}
	
		
	}
	
	
	
	

}
