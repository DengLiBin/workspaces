package com.jayqqaa12.mobilesafe.ui.space.lock;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.SysIntentUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.sys.RootUtil;
import com.jayqqaa12.abase.util.ui.DialogUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;
import com.jayqqaa12.mobilesafe.service.AppLockDogService;
import com.jayqqaa12.mobilesafe.service.IAppLockDogService;

public class LockScreenActivity extends AbaseActivity
{
	private Dialog dialog;
	private EditText et_pwd, et_repwd;
	private Button bt_ok, bt_cancel;

	@FindEngine
	private LostEngine lostEngine;
	private MyConn conn = new MyConn();

	private IAppLockDogService iAppLockDogService;
	private String packname;
	private boolean redirection;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO 以后 改成 锁屏 手势
		super.onCreate(savedInstanceState);

		packname = getIntent().getStringExtra("packname");
		redirection = getIntent().getBooleanExtra("redirection", false);

		Log.i(TAG.ACTIVITY, "packname is =" + packname);

		Intent intent = new Intent(this, AppLockDogService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);

		showPassword();

	}

	private class MyConn implements ServiceConnection
	{

		public void onServiceConnected(ComponentName name, IBinder service)
		{
			iAppLockDogService = (IAppLockDogService) service;
		}

		public void onServiceDisconnected(ComponentName name)
		{
		}

	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		unbindService(conn);
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

	private void vaildatePassword()
	{
		String pwd = et_pwd.getText().toString();

		if ("".equals(pwd))
		{
			ToastUtil.ShortToast("密码不能为空");
			return;
		}
		else
		{
			if (lostEngine.vaildataPwd(pwd))
			{
				iAppLockDogService.callUnlock(packname);
				finish();
			}
			else
			{
				ToastUtil.ShortToast("密码错误");
				return;
			}
		}
		dialog.dismiss();
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.bt_group_bt_2:

			// TODO 强制 重定向 不过 有点 不太好。
			if (redirection) SysIntentUtil.goSettingsManageApp(this);
			else SysIntentUtil.goHome(this);

			dialog.dismiss();
			finish();

			break;
		case R.id.bt_group_bt_1:
			vaildatePassword();

			break;
		default:
			break;
		}
	}
	
	


}
