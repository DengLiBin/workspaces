
package com.jayqqaa12.mobilesafe.ui.space.lost;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindRes;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.annotation.view.FindRes.ResType;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.ListViewAdapter;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;

public class LostMainActivity extends AbaseActivity
{
	private static final String TAG = "LostActivity";

	@FindView(id = R.id.lost_cb_open_protecting, checkedChange = true)
	private CheckBox cb_open_protecting;
	@FindView(id = R.id.lv, itemClick = true)
	private ListView lv;
	@FindView(id = R.id.title_1_tv, textId = R.string.lost_lable)
	private TextView tv_title;

	private ListViewAdapter adpater;
	private static final int icons[] = new int[] { R.drawable.lost_phone, R.drawable.lost_settings, R.drawable.lost_code, R.drawable.lost_pwd,
			R.drawable.lost_white_list };

	@FindRes(id = R.array.lost_lables, type = ResType.STRING_ARRAY)
	private static String[] titles;
	@FindRes(id = R.array.lost_describes, type = ResType.STRING_ARRAY)
	private static String[] descibers;

	@FindEngine
	private LostEngine engine;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.i(TAG, "into lost main");
		super.onCreate(savedInstanceState);
		

		intoMain();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:
			// TODO 修改 号码
			break;
		case 1:
			intoSetupGuide();
			break;
		case 2:
			// TODO 进入 查看指令 界面。
			break;
		case 3:
			// TODO 进入 修改 密码 界面 可通过 安全 手机 来 设置 密码
			break;
		case 4:
			// TODO 进入 白名单 设置 防盗 的 SIM
			IntentUtil.startIntent(this, SimWhiteActivity.class);

		default:

			break;
		}

	}

	private void intoSetupGuide()
	{
		Log.i(TAG, "into setup guide");
		Intent intent = new Intent(this, SetupGuide1Activity.class);
		startActivityForResult(intent, 0);

	}



	@Override
	public void onCheckedChanged(CompoundButton view, boolean isChecked)
	{
		switch (view.getId())
		{
		case R.id.lost_cb_open_protecting:
			if (isChecked)
			{
				engine.setProtected(true);
			}
			else
			{
				engine.setProtected(false);
			}

			initView();
			break;
		default:
			break;
		}

	}

	private void intoMain()
	{
		// TODO onResume
		if (engine.isSetup())
		{
			descibers[0] = engine.getProtectedNumber();

			Log.i(TAG, "enter main");
			setContentView(R.layout.activity_lost);
			// init state
			initView();
			adpater = new ListViewAdapter(icons, titles, descibers, this);
			lv.setAdapter(adpater);

		}

		else intoSetupGuide();

	}

	private void initView()
	{

		if (engine.isOpenService())
		{

			cb_open_protecting.setChecked(true);
			cb_open_protecting.setText(getString(R.string.lost_lable_on_protected));
		}
		else
		{
			cb_open_protecting.setChecked(false);
			cb_open_protecting.setText(getString(R.string.lost_lable_un_protected));
		}

	}


	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		String name = data.getComponent().getClassName();

		Log.i(TAG, name);
		if (name.equals(SetupGuide1Activity.class.getName()))
		{
			if (engine.isSetup()) intoMain();
			else finish();

		}

	}
}
