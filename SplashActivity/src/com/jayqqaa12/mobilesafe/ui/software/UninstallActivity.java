package com.jayqqaa12.mobilesafe.ui.software;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindRes;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.annotation.view.FindRes.ResType;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.core.adapter.AbaseSimpleAdapter;
import com.jayqqaa12.abase.util.AdapterUtil;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.sys.MemoryUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.software.AppEngine;

public class UninstallActivity extends AbaseActivity
{
	private static final String TAG = "UninstallActivity";

	@FindView(id = R.id.tv_group_1_tv_1)
	private TextView tv_memory;
	@FindView(id = R.id.tv_group_1_tv_2)
	private TextView tv_sdcard;
	@FindView(id = R.id.lv, itemClick = true)
	private ListView lv;

	@FindView(id = R.id.title_1_tv, textId = R.string.software_uninstall_label)
	private TextView tv_title;
	@FindView(id = R.id.pb)
	private View pb;

	@FindRes(id = R.array.software_uninstall_labels, type = ResType.STRING_ARRAY)
	private static String[] titles;
	@FindRes(id = R.array.software_uninstall_describes, type = ResType.STRING_ARRAY)
	private static String[] describes;

	private static final String[] from = new String[] { "title", "describe" };
	private SimpleAdapter adapter;
	private List<? extends Map<String, ?>> data;

	@FindEngine
	private AppEngine engine;

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			pb.setVisibility(View.GONE);
			lv.setVisibility(View.VISIBLE);

			initListView();
			initAdapterData();

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_software_uninstall);
		getData();

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		initMemory();
		initAdapterData();

	}

	private void getData()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				engine.startGetAppsInfo();
				handler.sendEmptyMessage(0);

			}
		}).start();

	}

	private void initAdapterData()
	{

		if (adapter != null)
		{

			titles[0] = getString(R.string.software_uninstall_label_my_software) + "(" + engine.getMyApps().size() + ")";
			titles[1] = getString(R.string.software_uninstall_label_pre_software) + "(" + engine.getPreloadApps().size() + ")";
			titles[2] = getString(R.string.software_uninstall_label_system_software) + "(" + engine.getSysApps().size() + ")";

			AdapterUtil.changeAdpaterData(data, new String[] { "title" }, titles);

			adapter.notifyDataSetChanged();
		}
	}

	private void initMemory()
	{
		tv_memory.setText(getString(R.string.software_uninstall_label_memory) + " : " + MemoryUtil.getAvailablePhoneRom());
		tv_sdcard.setText(getString(R.string.software_uninstall_label_sdcard) + " : " + MemoryUtil.getAvailableSDRom());

	}

	private void initListView()
	{

		data = AdapterUtil.getAdpaterData(from, titles, describes);
		adapter = new AbaseSimpleAdapter(this, data, R.layout.lv_item_2, from, new int[] { R.id.lv_tv_1, R.id.lv_tv_2 });
		lv.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:
			// my apps
			IntentUtil.startIntent(this, MyAppsActivity.class);

			break;
		case 1:

			// prelaod apps
			IntentUtil.startIntent(this, PreloadAppsActivity.class);

			break;
		case 2:
			// sys apps
			IntentUtil.startIntent(this, SysAppsActivity.class);

			break;

		default:
			break;
		}

	}

	@Override
	protected void onDestroy()
	{
		Log.i(TAG, "appinfo数据销毁了");
		engine.destoryAppsInfo();
		super.onDestroy();
	}

}
