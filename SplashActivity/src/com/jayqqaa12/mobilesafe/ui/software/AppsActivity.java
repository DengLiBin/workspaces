package com.jayqqaa12.mobilesafe.ui.software;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jayqqaa12.abase.core.AbaseReceiver;
import com.jayqqaa12.abase.core.Engine;
import com.jayqqaa12.abase.util.MsgUtil;
import com.jayqqaa12.abase.util.ReceiverUtil;
import com.jayqqaa12.abase.util.sys.ApkInfo;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;
import com.jayqqaa12.abase.util.sys.RootUtil;
import com.jayqqaa12.abase.util.ui.DialogUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.AppListViewAdapter;
import com.jayqqaa12.mobilesafe.engine.software.AppEngine;

public class AppsActivity extends Activity implements OnClickListener, OnItemClickListener
{

	protected static final String TAG = "AppsActivity";

	protected boolean myApps, sysApps, preLoadApp;

	protected TextView tv_count;
	protected ImageView iv_handler;
	protected TextView tv_describe;
	protected TextView tv_title;
	protected Button bt_delete;
	protected Button bt_select;
	protected ListView lv;

	protected List<ApkInfo> data = new ArrayList<ApkInfo>();

	protected AppEngine engine;
	// apps

	protected AppListViewAdapter adapter;
	protected BroadcastReceiver receiver;
	protected Dialog pd;
	protected List<String> uninstallInfo = new ArrayList<String>();

	protected TextView tv_progress;
	protected ProgressBar pb_progress;

	private int title_id;
	private int describer_id;

	public void setMyApps(boolean myApps)
	{
		this.myApps = myApps;
	}

	public void setSysApps(boolean sysApps)
	{
		this.sysApps = sysApps;
	}

	public void setPreLoadApp(boolean preLoadApp)
	{
		this.preLoadApp = preLoadApp;
	}

	protected Handler uninstallHandler = new Handler()
	{
		private int pos = 0;
		private String currentTitle;

		public void handleMessage(android.os.Message msg)
		{
			if (pos < uninstallInfo.size()) currentTitle = uninstallInfo.get(pos);

			switch (msg.what)
			{

			case MsgUtil.MSG_START:
				tv_progress.setText("正在卸载:" + currentTitle);
				pb_progress.setProgress(pos++);
				break;

			case MsgUtil.MSG_FINISH:
				pb_progress.setProgress(pos);
				Log.i(TAG, "卸载 完成");
				pos = 0;
				uninstallInfo.clear();
				pd.dismiss();

				break;
			default:
				break;
			}
		}

	};

	private void init()
	{
		engine = (AppEngine) Engine.getInstance(AppEngine.class);

		if (myApps)
		{
			title_id = R.string.software_uninstall_label_my_software;
			describer_id = R.string.software_uninstall_describe_my_software;
			data = engine.getMyApps();
		}
		else if (sysApps)
		{
			title_id = R.string.software_uninstall_label_system_software;
			describer_id = R.string.software_uninstall_describe_system_software;
			data = engine.getSysApps();
		}

		else if (preLoadApp)
		{

			title_id = R.string.software_uninstall_label_pre_software;
			describer_id = R.string.software_uninstall_describe_pre_software;
			data = engine.getPreloadApps();
		}

	}

	private void initView()
	{
		tv_title = (TextView) findViewById(R.id.title_2_tv);
		lv = (ListView) findViewById(R.id.lv);
		tv_count = (TextView) findViewById(R.id.tv_count);
		tv_describe = (TextView) findViewById(R.id.tv_describe);
		iv_handler = (ImageView) findViewById(R.id.iv_handler);

		bt_select = (Button) findViewById(R.id.title_2_bt_1);
		bt_delete = (Button) findViewById(R.id.title_2_bt_2);

		bt_delete.setOnClickListener(this);
		bt_select.setOnClickListener(this);
		iv_handler.setOnClickListener(this);
		lv.setOnItemClickListener(this);
	}

	protected Handler updateHandler = new Handler()
	{

		public void handleMessage(android.os.Message msg)
		{

			switch (msg.what)
			{

			// 观察 卸载 是否 成功
			case MsgUtil.MSG_REMOVE:

				removeData((String) msg.obj);
				tv_count.setText(getString(title_id) + "(" + data.size() + ")");
				adapter.notifyDataSetChanged();
				ToastUtil.ShortToast((String) msg.obj + "卸载 成功 ");
				break;

			default:
				break;
			}

		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.style_activity_app);
		initView();
		initAdapter();
		initViewData();
		registerApkReceiver();

	}

	protected void registerApkReceiver()
	{
		receiver= ReceiverUtil.packageRemoved(this,new RemoveApkReceiver());
	}

	protected void initViewData()
	{
		tv_title.setText(getString(R.string.software_uninstall_label));
		tv_describe.setText(getString(describer_id));
		tv_count.setText(getString(title_id) + "(" + data.size() + ")");

	}

	protected void initAdapter()
	{
		adapter = new AppListViewAdapter(this, data);
		lv.setAdapter(adapter);
	}

	protected void changeAppData(boolean checked)
	{
		for (ApkInfo info : data)
		{
			info.check=checked;
		}
	}

	protected void removeData(String pkn)
	{
		Iterator<ApkInfo> it = data.iterator();

		while (it.hasNext())
		{
			if (it.next().packageName.equals(pkn)) it.remove();

		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.title_2_bt_1:
			//全选
			initButton(bt_select, getString(R.string.check_all), getString(R.string.cancel_check_all));
			break;

		case R.id.title_2_bt_2:
			//删除！！
			
			List<String> uninstallPkn = new ArrayList<String>();

			for (ApkInfo info : data)
			{
				if (info.check)
				{
					uninstallPkn.add(info.packageName);
					uninstallInfo.add(info.appName);
				}
			}

			if (uninstallPkn.size() > 0)
			{
				RootUtil.uninstall(this, uninstallPkn, null, uninstallHandler);
				showProgressDialog(uninstallPkn.size());

			}
			else
			{
				ToastUtil.ShortToast("选中列表为空");
			}

			break;

		case R.id.iv_handler:

			break;

		}
	}

	private void showProgressDialog(int max)
	{
		View view = View.inflate(this, R.layout.pd_1, null);
		tv_progress = (TextView) view.findViewById(R.id.pd_1_tv);
		pb_progress = (ProgressBar) view.findViewById(R.id.pd_1_pb);
		pb_progress.setMax(max);
		pd = DialogUtil.showDialog(this, R.style.MyDialog, false, view);

	}

	private void initButton(Button b, String check, String uncheck)
	{
		if (b.getText().equals(check))
		{
			b.setText(uncheck);
			changeAppData(true);

		}
		else
		{
			b.setText(check);
			changeAppData(false);
		}

		adapter.notifyDataSetChanged();
	}

	protected class RemoveApkReceiver extends AbaseReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent)
		{
			super.onReceive(context, intent);
			MsgUtil.sendMessage(updateHandler, MsgUtil.MSG_REMOVE, intent.getDataString().split("package:")[1]);
		}
	}

	@Override
	protected void onDestroy()
	{

		unregisterReceiver(receiver);
		super.onDestroy();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:

			break;

		default:
			break;
		}

	}
}
