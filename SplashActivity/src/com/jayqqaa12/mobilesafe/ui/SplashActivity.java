package com.jayqqaa12.mobilesafe.ui;

import java.io.File;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.SysIntentUtil;
import com.jayqqaa12.abase.util.comm.DownLoadUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.ui.DialogUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.domain.VersionInfo;
import com.jayqqaa12.mobilesafe.engine.InitEngine;
import com.jayqqaa12.mobilesafe.engine.VersionInfoEngine;

public class SplashActivity extends AbaseActivity
{

	@FindView(id = R.id.ll)
	private LinearLayout ll;

	@FindView(id = R.id.tv_version)
	private TextView tv_version;
	@FindView(id = R.id.iv_anim)
	private ImageView iv_anim;
	@FindEngine
	private VersionInfoEngine versionEngine;
	@FindEngine
	private InitEngine initEngine;

	private String current_version;
	private VersionInfo versioninfo;
	private ProgressDialog pd_update;
	private AnimationDrawable splash_anim;

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);

		splash_anim.start();
	}

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			if (versionEngine.isCheckUpdate())
			{

				if (versionEngine.haveNewVersion(current_version))
				{
					Log.i(TAG.ACTIVITY, "show update dialog");
					showUpdateDialog();
				}
				{
					intoMain();
					Log.i(TAG.ACTIVITY, "not hava new update");
				}

			}
			else
			{
				intoMain();
				Log.i(TAG.ACTIVITY, "not check version ");
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		// set animation
		splash_anim = (AnimationDrawable) iv_anim.getBackground();
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash);
		ll.startAnimation(anim);

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		initView();
	}

	private void initView()
	{
		current_version = versionEngine.getVersionNumber();
		tv_version.setText(current_version);
		pd_update = DialogUtil.createProgressDialog(this, getString(R.string.download), false, ProgressDialog.STYLE_HORIZONTAL);

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				
				Looper.prepare();
				initEngine.initDir();
								
				if (initEngine.isFirstRunning()) initEngine.init();

				// init work
				// up zip db
				if (initEngine.isInitDB())
				{
					Log.i(TAG.ACTIVITY, " 不需要初始化");
					try
					{
						Thread.sleep(2000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					Log.i(TAG.ACTIVITY, " 正在初始化");
					ToastUtil.ShortToast("正在初始化 数据");
					Log.i(TAG.ACTIVITY, "start zip");
					initEngine.upZipDB();
					Log.i(TAG.ACTIVITY, "zip ending");
				}
				handler.sendEmptyMessage(0);

				Looper.loop();
			}

		}).start();
	}

	private void intoMain()
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private void showUpdateDialog()
	{
		AlertDialog.Builder update_dialog = new Builder(this);
		update_dialog.setTitle(getString(R.string.version_find_new));
		update_dialog.setMessage(versioninfo.getDesciber());

		Log.i(TAG.ACTIVITY, versioninfo.getDesciber());

		update_dialog.setCancelable(false);
		update_dialog.setPositiveButton("确定", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// start download
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
					pd_update.show();

					// TODO 应该开个 服务去 下载 更新 notification
					new Thread(new DownloadTaskThread(versioninfo.getApkurl(), Environment.getExternalStorageDirectory() + "/"
							+ getString(R.string.app_name) + ".apk")).start();
				}
				else
				{
					ToastUtil.ShortToast("SD卡不可用");
					intoMain();
				}

			}
		});

		update_dialog.setNegativeButton("取消", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				intoMain();
				return;
			}
		});

		update_dialog.create().show();

	}

	private class DownloadTaskThread implements Runnable
	{
		private String servicePath;
		private String localPath;

		public DownloadTaskThread(String servicePath, String localPath)
		{
			this.servicePath = servicePath;
			this.localPath = localPath;
		}

		@Override
		public void run()
		{

			try
			{
				File file = DownLoadUtil.getFile(servicePath, localPath, pd_update);
				Log.i(TAG.ACTIVITY, " download success");
				install(file);
			}
			catch (Exception e)
			{
				Log.i(TAG.ACTIVITY, " download fail");
				intoMain();
				e.printStackTrace();
			}
			finally
			{
				pd_update.dismiss();
			}

		}

	}

	private void install(File file)
	{
		finish();
		SysIntentUtil.install(this, file);
	}

}
