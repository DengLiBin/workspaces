package com.jayqqaa12.mobilesafe.ui.traffic;

import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbasePagerActivity;
import com.jayqqaa12.abase.core.adapter.AbaseBaseAdapter;
import com.jayqqaa12.abase.core.adapter.AbasePagerAdapter;
import com.jayqqaa12.abase.util.TextUtil;
import com.jayqqaa12.abase.util.common.DateUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.sys.ApkInfo;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.domain.Traffic;
import com.jayqqaa12.mobilesafe.engine.traffic.TrafficEngine;

public class TopActivity extends AbasePagerActivity
{

	@FindView(pageId = R.layout.lv, id = R.id.lv, pageNum = 0, itemClick = true)
	private ListView lv_month;
	@FindView(pageId = R.layout.lv, id = R.id.lv, pageNum = 1, itemClick = true)
	private ListView lv_day;

	@FindView(id = R.id.tv_group_1_tv_1)
	private TextView tv_data;
	@FindView(id = R.id.tv_group_1_tv_2, textId = R.string.traffic_label_unit)
	private TextView tv_unit;
	@FindView(id = R.id.tv_title)
	private TextView tv_title;
	@FindView(id = R.id.pb_1)
	private View pb;
	@FindView(id = R.id.vp,pagerChange=true )
	private ViewPager vp;
	private MyAdapter adapter;
	private List<ApkInfo<Traffic>> data;
	
	@FindEngine
	private TrafficEngine engine;

	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			pb.setVisibility(View.INVISIBLE);
			vp.setVisibility(View.VISIBLE);

			initView();
			initAdapter();
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traffic_top);
		getData();

	}

	private void getData()
	{

		new Thread()
		{
			public void run()
			{
				data = engine.getAppTrafficData();
				handler.sendEmptyMessage(0);
			};

		}.start();

	}

	private void initAdapter()
	{
		adapter = new MyAdapter();

		lv_day.setAdapter(adapter);
		lv_month.setAdapter(adapter);

		vp.setAdapter(new AbasePagerAdapter(this, pageViews));

	}
	
	private void changeTitle()
	{
		
		if(vp.getCurrentItem() != 0)
		{
			tv_title.setText(getString(R.string.traffic_top_label_today));
			tv_data.setText( DateUtil.getDate(new Date()));
			
		}
		else
		{
			tv_title.setText(getString(R.string.traffic_top_label_all));
			tv_data.setText(engine.getTrafficStartDate() + "到" + DateUtil.getDate(new Date()));

		}
		
		
		
		
		
	}


	private void initView()
	{
		tv_title.setText(getString(R.string.traffic_top_label_all));

		tv_data.setText(engine.getTrafficStartDate() + "到" + DateUtil.getDate(new Date()));

	}

	private class MyAdapter extends AbaseBaseAdapter
	{
		@Override
		public int getCount()
		{
			return data.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHandler holder = null;

			if (convertView == null)
			{
				holder = new ViewHandler();
				convertView = View.inflate(TopActivity.this, R.layout.lv_item_traffic, null);

				holder.icon = (ImageView) convertView.findViewById(R.id.lv_iv);
				holder.name = (TextView) convertView.findViewById(R.id.lv_tv_1);
				holder.mobile = (TextView) convertView.findViewById(R.id.lv_tv_2);
				holder.wifi = (TextView) convertView.findViewById(R.id.lv_tv_3);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHandler) convertView.getTag();
			}

			ApkInfo<Traffic> info = data.get(position);

			holder.icon.setImageDrawable(info.iconDrawable);
			holder.name.setText(info.appName);

			if (tv_title.equals(getString(R.string.traffic_top_label_all)))
			{
				holder.wifi.setText(TextUtil.getBitToMb(info.obj.getWifiMonth()));
				holder.mobile.setText(TextUtil.getBitToMb(info.obj.getMobileMonth()));
			}
			else
			{

				holder.wifi.setText(TextUtil.getBitToMb(info.obj.getWifiDay()));

				holder.mobile.setText(TextUtil.getBitToMb(info.obj.getMobileDay()));

			}

			return convertView;

		}

		private class ViewHandler
		{
			ImageView icon;
			TextView wifi;
			TextView mobile;
			TextView name;
		}

	}

	@Override
	public void onPageSelected(int pos)
	{
		changeTitle();

	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

	}

}
