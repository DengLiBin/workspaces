package com.jayqqaa12.mobilesafe.ui.comm;

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
import com.jayqqaa12.mobilesafe.ui.comm.intercept.InterceptMainActivity;
import com.jayqqaa12.mobilesafe.ui.comm.phone.PhoneMainActivity;
import com.jayqqaa12.mobilesafe.ui.comm.sms.SmsMainActivity;

public class ManagerMainActivity extends AbaseActivity
{

	@FindView(id = R.id.lv, itemClick = true)
	private ListView lv;
	private ListViewAdapter adpater;

	private static final int[] icons = new int[] { R.drawable.communication_manager_phone, R.drawable.communication_manager_sms,
			R.drawable.communication_manager_intercept };

	@FindRes(id = R.array.communication_lables, type = ResType.STRING_ARRAY)
	private static String[] titles;
	@FindRes(id = R.array.communication_describes, type = ResType.STRING_ARRAY)
	private static String[] describers;
	
	@FindView(id = R.id.title_1_tv, textId = R.string.communication_lable)
	private TextView lv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.style_activity_manager);

		adpater = new ListViewAdapter(icons, titles, describers, this);

		lv.setAdapter(adpater);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:
			// phone manager
			IntentUtil.startIntent(this, PhoneMainActivity.class);
			break;
		case 1:
			// sms manager
			IntentUtil.startIntent(this, SmsMainActivity.class);

			break;

		case 2:
			// intecapt manager
			IntentUtil.startIntent(this, InterceptMainActivity.class);

		default:
			break;
		}
	}

}
