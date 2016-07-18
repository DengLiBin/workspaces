package com.jayqqaa12.mobilesafe.ui.comm.sms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ui.DialogUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.comm.sms.SmsEngine;
import com.jayqqaa12.mobilesafe.service.SmsBackupService;

public class BackupActivity extends AbaseActivity implements OnKeyListener
{
	private static final String TAG = "BackupActivity";

	@FindView(id = R.id.tv_date)
	private TextView tv_date;
	// init res

	@FindView(parId = R.id.bt_restore, click = true, id = 0)
	private LinearLayout bt_restore;
	@FindView(parId = R.id.bt_backup, click = true, id = 0)
	private LinearLayout bt_backup;
	
	@FindView(parId = R.id.bt_restore, id = R.id.bt_1_iv, imageId = R.drawable.sms_backup_restore)
	private ImageView bt_iv_image2;
	@FindView(parId = R.id.bt_restore, id = R.id.bt_1_tv_1, textId = R.string.sms_backup_lable_restore)
	private TextView bt_tv_label2;
	@FindView(parId = R.id.bt_restore, id = R.id.bt_1_tv_2, textId = R.string.sms_backup_describe_restore)
	private TextView bt_tv_describe2;
	@FindView(parId = R.id.bt_backup, id = R.id.bt_1_iv, imageId = R.drawable.sms_backup_backup)
	private ImageView bt_iv_image;
	@FindView(parId = R.id.bt_backup, id = R.id.bt_1_tv_1, textId = R.string.sms_backup_lable_backup)
	private TextView bt_tv_label;
	@FindView(parId = R.id.bt_backup, id = R.id.bt_1_tv_2, textId = R.string.sms_backup_describe_backup)
	private TextView bt_tv_describe;

	@FindView(id = R.id.title_1_tv, textId = R.string.sms_backup_lable)
	private TextView tv_title;

	private ProgressDialog pd_backup;
	private ProgressDialog pd_restore;
	private Intent backupIntent;

	@FindEngine
	private SmsEngine smsEngine;
	
	private SmsBackupService backupEngine;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_backup);

		initDate();
	}

	private void initDate()
	{
		String date = smsEngine.getBackupDate();
		if (TextUtils.isEmpty(date))
		{

			tv_date.setText(getString(R.string.sms_backup_describe_not_backup));
		}
		else
		{
			tv_date.setText(getString(R.string.sms_backup_describe_last_time) + date);
		}

	}

	@Override
	public void onClick(View v)
	{
		Log.i(TAG, "view is click tag is" + v.getTag());

		switch (v.getId())
		{
		case R.id.bt_backup:
			Log.i(TAG, "backup start");
			
			// TODO handler 更新 Progress
			// TODO 自定义 Progress UI
			// pd_backup = DialogUtil.showProgressDialog(this, "正在备份短信", false,
			// ProgressDialog.STYLE_HORIZONTAL);
			// pd_backup.setOnKeyListener(this);
			
			// TODO 用数据库代替XML   然后 加密 放到 sd卡中
			
			backupIntent = IntentUtil.startService(this, SmsBackupService.class);
			initDate();

			break;
		case R.id.bt_restore:
			Log.i(TAG, "restore start");

			if (!smsEngine.isFindBackupFile())
			{
				ToastUtil.ShortToast("没有找到备份文件");
			}
			else
			{
				//TODO  先查找 有没有 相同的短信存在 存在 就不还原
				pd_restore = DialogUtil.showProgressDialog(this, "正在还原短信哦！", false, ProgressDialog.STYLE_HORIZONTAL);
				
				pd_restore.setOnKeyListener(this);
				
				new RestoreThread().start();
				
			}
			break;
		default:
			break;
		}
	}

	private class RestoreThread extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				Looper.prepare();
				smsEngine.restoreSms(pd_restore);
				ToastUtil.ShortToast(getString(R.string.sms_backup_describe_restore_success) + smsEngine.getBackupCount());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ToastUtil.ShortToast(getString(R.string.sms_bakcup_describer_restore_fail));
			}
			finally
			{
				pd_restore.dismiss();
				Looper.loop();
			}
		}

	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
	{
		
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK:
			return true;
			
		case KeyEvent.KEYCODE_SEARCH:
			return true;
		default:
			break;
		}

			

		return false;
	}

}
