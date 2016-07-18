package com.jayqqaa12.mobilesafe.ui.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbasePagerActivity;
import com.jayqqaa12.abase.core.adapter.AbasePagerAdapter;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;
import com.jayqqaa12.abase.util.sys.MemoryUtil;
import com.jayqqaa12.abase.util.sys.RootUtil;
import com.jayqqaa12.abase.util.sys.TaskInfo;
import com.jayqqaa12.abase.util.sys.TaskUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.ProcessListViewAdapter;

@SuppressWarnings("rawtypes")
public class ProcessMainActivity extends AbasePagerActivity
{

	@FindView(id = R.id.tv_title)
	private TextView tv_title;
	@FindView(id = R.id.title_3_bt, textId = R.string.setting, click = true)
	private Button bt_setting;
	@FindView(id = R.id.title_3_tv, textId = R.string.process_label)
	private TextView tv;
	@FindView(id = R.id.bt_group_bt_1, textId = R.string.check_all, click = true)
	private Button bt_select;
	@FindView(id = R.id.bt_group_bt_2, textId = R.string.all_clean, click = true)
	private Button bt_clean;
	@FindView(id = R.id.tv_group_1_tv_1, textId = R.string.process_label_running_process)
	private TextView tv_running;
	@FindView(id = R.id.tv_group_1_tv_2, textId = R.string.process_label_memory)
	private TextView tv_memory;
	@FindView(id = R.id.pb_1)
	private View pb;
	@FindView(id = R.id.vp, pagerChange = true)
	private ViewPager vp;
	@FindView(pageId = R.layout.lv, id = R.id.lv, pageNum = 0, tag = "user", itemClick = true)
	private ListView lv_user;
	@FindView(pageId = R.layout.lv, id = R.id.lv, pageNum = 1, tag = "sys", itemClick = true)
	private ListView lv_sys;

	private List<TaskInfo> sysTasks = new ArrayList<TaskInfo>();
	
	private List<TaskInfo> userTasks = new ArrayList<TaskInfo>();
	private ProcessListViewAdapter sysAdapter;
	private ProcessListViewAdapter userAdapter;

	private AbasePagerAdapter pageAdapter;

	private boolean root = RootUtil.getRootAhth();

	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			pb.setVisibility(View.INVISIBLE);

			initAdapte();
			tv_title.setText(getString(R.string.process_label_user) + "(" + userTasks.size() + ")");
			initMemory();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process);
		getProcessData();
	}

	private void initMemory()
	{
		tv_running.setText(getString(R.string.process_label_running_process) + " " + (sysTasks.size() + userTasks.size()) + "个");
		tv_memory.setText(getString(R.string.process_label_memory) + " " + MemoryUtil.getAvailableRam() + "/" + MemoryUtil.getTotalRam());

	}

	protected void initAdapte()
	{
		sysAdapter = new ProcessListViewAdapter(sysTasks, this);
		userAdapter = new ProcessListViewAdapter(userTasks, this);
		pageAdapter = new AbasePagerAdapter(this, pageViews);

		lv_sys.setAdapter(sysAdapter);
		lv_user.setAdapter(userAdapter);

		vp.setAdapter(pageAdapter);

	}

	private void getProcessData()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				for (TaskInfo info : TaskUtil.getRunningAppProcesses())
				{
					if (info.system) sysTasks.add(info);
					else userTasks.add(info);
				}
				handler.sendEmptyMessage(0);
			}
		}.start();

	}

	private void changeTitle()
	{
		if (!isUserTask()) tv_title.setText(getString(R.string.process_label_system) + "(" + sysTasks.size() + ")");
		else tv_title.setText(getString(R.string.process_label_user) + "(" + userTasks.size() + ")");
	}

	private void initTitle()
	{
		if (isUserTask()) tv_title.setText(getString(R.string.process_label_user) + "(" + userTasks.size() + ")");
		else tv_title.setText(getString(R.string.process_label_system) + "(" + sysTasks.size() + ")");
	}

	private boolean isUserTask()
	{
		return vp.getCurrentItem() == 0;
	}

	@Override
	public void onPageSelected(int pos)
	{
		changeTitle();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		String tag = (String) parent.getTag();
		if (tag.equals("user"))
		{
			if (userTasks.get(position).check) userTasks.get(position).check = false;
			else userTasks.get(position).check = true;
			userAdapter.notifyDataSetChanged();

		}
		else if (tag.equals("sys"))
		{
			if (sysTasks.get(position).check) sysTasks.get(position).check = false;
			else sysTasks.get(position).check = true;
			sysAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.title_3_bt:
			// Setting
			IntentUtil.startIntent(this, SettingActivity.class);
			break;

		case R.id.bt_group_bt_1:
			// check all

			if (isUserTask())
			{
				for (TaskInfo info : userTasks)
				{
					if (!info.appName.equals(ApkInfoUtil.getMyApkName()))
					{

						if (!bt_select.getText().equals(getString(R.string.check_all))) info.check = false;
						else info.check = true;

					}

				}

				userAdapter.notifyDataSetChanged();
			}
			else
			{
				for (TaskInfo info : sysTasks)
				{
					if (bt_select.getText().equals(getString(R.string.check_all))) info.check = true;
					else info.check = false;
				}

				sysAdapter.notifyDataSetChanged();
			}

			if (bt_select.getText().equals(getString(R.string.check_all))) bt_select.setText(getString(R.string.cancel_check_all));
			else bt_select.setText(getString(R.string.check_all));

			break;

		case R.id.bt_group_bt_2:
			// clear
			if (isUserTask()) killProcess(userTasks);

			else killProcess(sysTasks);

			break;

		default:
			break;
		}

	}

	private void killProcess(List<TaskInfo> tasks)
	{
		// TODO 依然 无法 杀死 一些 进程 root 的方式也不好使  目前 估计 只能用系统 级 应用的 办法 
		
		long totail = 0;

		List<Integer> pids = new ArrayList<Integer>();
		Iterator<TaskInfo> iter = tasks.iterator();

		while (iter.hasNext())
		{
			TaskInfo info = iter.next();
			if (info.check)
			{
				totail += info.memorySize;
				if (root) RootUtil.killProcess(info.pid);
				else TaskUtil.killProcess(info.packname);

				pids.add(info.pid);
				iter.remove();
			}
		}

		ToastUtil.ShortToast("释放了 " + Formatter.formatFileSize(this, totail) + "内存");
		userAdapter.notifyDataSetChanged();
		sysAdapter.notifyDataSetChanged();
		initTitle();
		initMemory();
	}
}
