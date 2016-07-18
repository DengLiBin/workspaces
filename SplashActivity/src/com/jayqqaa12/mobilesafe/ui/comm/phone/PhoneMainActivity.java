
package com.jayqqaa12.mobilesafe.ui.comm.phone;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindRes;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.annotation.view.FindRes.ResType;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.ListViewAdapter;

public class PhoneMainActivity extends AbaseActivity
{
	@FindView(id = R.id.lv, itemClick = true)
	private ListView lv;
	@FindView(id = R.id.title_1_tv, textId = R.string.phone_lable)
	private TextView tv_title;

	@FindRes(id = R.array.phone_lables, type = ResType.STRING_ARRAY)
	private static String[] titles;
	@FindRes(id = R.array.phone_describes, type = ResType.STRING_ARRAY)
	private static String[] desciber;

	private ListViewAdapter adapter;
	private static final int[] icons = new int[] { R.drawable.communication_manager_phone_location_number_query,
			R.drawable.communication_manager_phone_ip, R.drawable.communication_manager_phone_location_setting,
			R.drawable.communication_manager_phone_sp_number_query };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.style_activity_manager);

		adapter = new ListViewAdapter(icons, titles, desciber, this);
		lv.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:
			// 归属地 查询

			IntentUtil.startIntent(this, LocationQueryActivity.class);

			break;
		case 1:
			// TODO ip phone
			break;
		case 2:
			// 归属地 setting
			IntentUtil.startIntent(this, LocationDisplayActivty.class);
			break;
		case 3:
			//  常用号码 query
			IntentUtil.startIntent(this,CommPhoneActivity.class);
			
			
			break;

		default:
			break;
		}

	}

}
