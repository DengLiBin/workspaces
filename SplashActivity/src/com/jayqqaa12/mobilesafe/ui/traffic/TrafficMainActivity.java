package com.jayqqaa12.mobilesafe.ui.traffic;

import android.content.Intent;
import android.os.Bundle;

import com.jayqqaa12.abase.core.activity.AbaseTabActivity;
import com.jayqqaa12.mobilesafe.R;

public class TrafficMainActivity extends AbaseTabActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String[] labels = new String[] {getString(R.string.traffic_label_details),getString(R.string.traffic_label_top),getString(R.string.traffic_label_firewall)};
		
		initMyTab( R.layout.tab_widget_1, labels, R.id.tab_widget_1_tv, labels, new Intent[] {
				new Intent(this, DetailsActivity.class), new Intent(this, TopActivity.class), new Intent(this, FireWallActivity.class) });

	}

}
