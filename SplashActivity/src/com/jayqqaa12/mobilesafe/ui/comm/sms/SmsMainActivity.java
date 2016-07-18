package com.jayqqaa12.mobilesafe.ui.comm.sms;

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

public class SmsMainActivity extends AbaseActivity
{

	@FindView(id = R.id.lv, itemClick = true)
	private ListView lv;
	@FindView(id = R.id.title_1_tv, textId = R.string.sms_lable)
	private TextView tv_title;
	
	private final static int[] icons = new int[] { R.drawable.sms_desk, R.drawable.sms_query, R.drawable.sms_backup, R.drawable.sms_manage };
	
	@FindRes(id = R.array.sms_lables, type = ResType.STRING_ARRAY)
	private static String[] titles;
	@FindRes(id = R.array.sms_describes, type = ResType.STRING_ARRAY)
	private static String[] describers;

	private ListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.style_activity_manager);
		
		adapter = new ListViewAdapter(icons, titles, describers, this);
		lv.setAdapter(adapter);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:
			//TODO sms desk
			

			break;
		case 1:
			//TODO sms queay

			break;

		case 2:
			// backup
			
			IntentUtil.startIntent(this,BackupActivity.class);

			break;

		case 3:
			//TODO sms manager

			break;
			
			
		default:
			break;
		}

	}

}
