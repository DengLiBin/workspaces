package com.jayqqaa12.mobilesafe.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.GridViewAdapter;
import com.jayqqaa12.mobilesafe.ui.antivirus.AntivirusMainActivity;
import com.jayqqaa12.mobilesafe.ui.comm.ManagerMainActivity;
import com.jayqqaa12.mobilesafe.ui.process.ProcessMainActivity;
import com.jayqqaa12.mobilesafe.ui.software.SoftwareMainActivity;
import com.jayqqaa12.mobilesafe.ui.space.SpaceMainActivity;
import com.jayqqaa12.mobilesafe.ui.traffic.TrafficMainActivity;

public class MainActivity extends AbaseActivity
{

	@FindView(id=R.id.gv ,itemClick=true)
	private GridView gv;
	private GridViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new GridViewAdapter(this);
		gv.setAdapter(adapter);
		// TODO 设置 隐藏 图标
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

		switch (position)
		{
		case 0:
			Log.i(TAG.ACTIVITY, "进入个人中心");
			IntentUtil.startIntent(this,SpaceMainActivity.class);
			break;
		case 1:
			Log.i(TAG.ACTIVITY, "进入 通信卫士");
			IntentUtil.startIntent(this,ManagerMainActivity.class);
			break;
		case 2:
			Log.i(TAG.ACTIVITY, "进入 software");
			IntentUtil.startIntent(this,SoftwareMainActivity.class);
			break;
		case 3:
			Log.i(TAG.ACTIVITY,"进入 process管理");
			IntentUtil.startIntent(this,ProcessMainActivity.class);
			
			break;
		case 4:
			Log.i(TAG.ACTIVITY,"进入 firewall");
			IntentUtil.startIntent(this,TrafficMainActivity.class);

			break;
		case 5:
			Log.i(TAG.ACTIVITY,"INTO KILL antivitus");
			
			IntentUtil.startIntent(this,AntivirusMainActivity.class);
			

			break;
		case 6:
			break;
		case 7:

			break;
		case 8:

			break;
		default:
			break;
		}

	}

}
