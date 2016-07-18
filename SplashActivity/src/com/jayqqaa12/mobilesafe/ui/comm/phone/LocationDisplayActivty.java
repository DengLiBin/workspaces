package com.jayqqaa12.mobilesafe.ui.comm.phone;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import com.jayqqaa12.abase.util.ui.DialogUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.ListViewSettingAdapter;
import com.jayqqaa12.mobilesafe.config.Style;
import com.jayqqaa12.mobilesafe.config.Timeout;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationDisplayEngine;
import com.jayqqaa12.mobilesafe.service.PhoneNumberService;

public class LocationDisplayActivty extends AbaseActivity implements OnClickListener
{
	private static final String TAG = "LocationDisplayActivty";

	@FindView(id = R.id.lv, itemClick =true)
	private ListView lv;
	@FindView(id = R.id.cb_enable, checkedChange =true)
	private CheckBox cb_enable;
	@FindView(id = R.id.tv_enable)
	private TextView tv_enable;
	@FindView(id = R.id.title_1_tv,textId=R.string.phone_location_display_lable)
	private TextView tv_title;
	
	@FindRes(id = R.array.phone_location_display_describes, type = ResType.STRING_ARRAY)
	private static String[] describers;
	@FindRes(id = R.array.phone_location_display_lables, type = ResType.STRING_ARRAY)
	private static String[] titles;

	private ListViewSettingAdapter adapter;
	@FindEngine
	private LocationDisplayEngine engine;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_location_display);
		adapter = new ListViewSettingAdapter(titles, describers, this);

		lv.setAdapter(adapter);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// init data
		initCheckBoxDate();
		initAdapterData();

	}

	private void initAdapterData()
	{
		describers[0] = Timeout.getNameByValue(engine.getDisplayTimeout());
		describers[2] = Style.getName(engine.getDisplayStyle());
		adapter.notifyDataSetChanged();
	}

	private void initCheckBoxDate()
	{
		// TODO 设置 不可选状态的 颜色
		if (engine.isOpenService())
		{
			cb_enable.setChecked(true);
			tv_enable.setText(getString(R.string.already_open));
			lv.setEnabled(true);

		}
		else
		{
			cb_enable.setChecked(false);
			tv_enable.setText(getString(R.string.un_open));
			lv.setEnabled(false);
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton v, boolean isChecked)
	{

		switch (v.getId())
		{
		case R.id.cb_enable:

			if (isChecked)
			{
				engine.onDisplay();
			}
			else
			{
				engine.offDisplay();
				Log.i(TAG, "location service is stop");
				IntentUtil.stopService(this, PhoneNumberService.class);
			}
			initCheckBoxDate();
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:
			// timeout
			int checkedItem = Timeout.getOrdinal(engine.getDisplayTimeout());

			DialogUtil.showSingleChoiceDiaog(this, getString(R.string.phone_location_query_lable_timeout), Timeout.getNames(), checkedItem,
					new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							Log.i(TAG, "timeout is = " + Timeout.getNameByOrdinal(which));
							engine.setDisplayTimeout(Timeout.getTimeoutByOrdinal(which));
							initAdapterData();
							dialog.dismiss();
						}
					}, this);
			break;
		case 1:
			// position
			Intent intent = new Intent(this, LocationDisplayDragActivity.class);
			startActivity(intent);
			break;
		case 2:
			// style
			int checkedItem2 = engine.getDisplayStyle();

			DialogUtil.showSingleChoiceDiaog(this, getString(R.string.phone_location_query_lable_style), Style.getNames(), checkedItem2,
					new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int which)
						{
							engine.setDisplayStyle(Style.getStyle(which));
							initAdapterData();
							Log.i(TAG, "dismiss");
							dialog.dismiss();
						}
					}, this);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which)
	{
		
	}

}
