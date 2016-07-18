package com.jayqqaa12.mobilesafe.ui.space;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.security.DisgestUtil;
import com.jayqqaa12.abase.util.ui.DialogUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.ListViewAdapter;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;
import com.jayqqaa12.mobilesafe.ui.space.lock.AppLockActivity;
import com.jayqqaa12.mobilesafe.ui.space.lost.LostMainActivity;

public class SpaceMainActivity extends AbaseActivity
{
	private static final String TAG = "SpaceMainActivity";
	@FindView(id = R.id.title_1_tv, textId = R.string.space_label)
	private TextView tv_title;

	@FindView(id = R.id.lv, itemClick = true)
	private ListView lv;
	private ListViewAdapter adpater;

	private static int[] describes = { R.string.space_friend_describe, R.string.space_lock_describe, R.string.lost_describe };
	private static int[] titles = { R.string.space_friend_label, R.string.space_lock_label, R.string.lost_lable };
	private static final int[] icons = { R.drawable.space_friend, R.drawable.space_lock, R.drawable.lost };

	private Dialog dialog;
	private EditText et_pwd, et_repwd;
	private Button bt_ok, bt_cancel;

	@FindEngine
	private LostEngine engine;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// password 
		
		
//		intoMain();
		
		if (!engine.havePassword())
		{
			Log.i(TAG, "first enter dialog");
			showSetPassword();
		}
		else
		{
			Log.i(TAG, "nomrl enter dialog");
			showPassword();
		}
		
		
	}

	
	private void vaildatePassword()
	{
		String pwd = et_pwd.getText().toString();

		if ("".equals(pwd))
		{
			ToastUtil.ShortToast( "密码不能为空");
			return;
		}
		else
		{
			if (engine.vaildataPwd(pwd))
			{
				Log.i(TAG, "enter main");
				intoMain();
			}
			else
			{
				ToastUtil.ShortToast( "密码错误");
				return;
			}
		}
		dialog.dismiss();
	}
	
	

	private void setPassword()
	{
		String pwd = et_pwd.getText().toString();
		String repwd = et_repwd.getText().toString();

		if ("".equals(pwd) || "".equals(repwd))
		{
			Toast.makeText(getApplicationContext(), "密码不能为空", 0).show();
			return;
		}
		else
		{
			if (pwd.equals(repwd))
			{
				// encode must > 30 counts
				engine.setPassword(pwd);
				// into main
				dialog.dismiss();
				intoMain();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "两次密码不同", 0).show();
				return;
			}
		}

	}
	
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.bt_group_bt_2:
			dialog.dismiss();
			finish();

			break;
		case R.id.bt_group_bt_1:

			if (v.getTag() != null) setPassword();
			else vaildatePassword();

			break;
		default:
			break;
		}
	}

	
	private void showSetPassword()
	{
		View view = View.inflate(this, R.layout.dialog_set_pwd, null);
		et_pwd = (EditText) view.findViewById(R.id.dialog_set_pwd_et_pwd);
		et_repwd = (EditText) view.findViewById(R.id.dialog_set_pwd_et_repwd);
		// !! Must user view.findViewById()
		bt_ok = (Button) view.findViewById(R.id.bt_group_bt_1);
		bt_cancel = (Button) view.findViewById(R.id.bt_group_bt_2);
		bt_ok.setTag("set");
		bt_ok.setOnClickListener(this);
		bt_cancel.setOnClickListener(this);

		dialog = DialogUtil.showDialog(this, R.style.MyDialog, false, view);
	}

	private void showPassword()
	{
		View view = View.inflate(this, R.layout.dialog_pwd, null);
		bt_cancel = (Button) view.findViewById(R.id.bt_group_bt_2);
		bt_ok = (Button) view.findViewById(R.id.bt_group_bt_1);
		et_pwd = (EditText) view.findViewById(R.id.dialog_pwd_et_pwd);
		bt_ok.setOnClickListener(this);
		bt_cancel.setOnClickListener(this);
		dialog = DialogUtil.showDialog(this, R.style.MyDialog, false, view);
		
	}


	private void intoMain()
	{
		setContentView(R.layout.style_activity_manager);
		adpater = new ListViewAdapter(icons, titles, describes, this);
		lv.setAdapter(adpater);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		switch (position)
		{
		case 0:
			//TODO friend 
			break;
		case 1:
			//TODO lock 
			IntentUtil.startIntent(this,AppLockActivity.class);

			break;
		case 2:
			// lost
			
			IntentUtil.startIntent(this,LostMainActivity.class);

			break;

		default:
			break;
		}

	}

}
