package com.jayqqaa12.mobilesafe.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.core.adapter.AbaseBaseAdapter;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;
import com.jayqqaa12.abase.util.sys.TaskInfo;
import com.jayqqaa12.mobilesafe.R;

public class ProcessListViewAdapter extends AbaseBaseAdapter
{
	private List<TaskInfo> tasks;
	private LayoutInflater mInflater;

	public ProcessListViewAdapter(List<TaskInfo> tasks, Context context)
	{
		this.tasks = tasks;
		
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount()
	{
		return tasks.size();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		
		if (convertView == null)
		{
			holder = new ViewHolder();
			convertView = mInflater.inflate( R.layout.lv_item_app, null);

			holder.icon = (ImageView) convertView.findViewById(R.id.lv_iv);
			holder.name = (TextView) convertView.findViewById(R.id.lv_tv_1);
			holder.memeory = (TextView) convertView.findViewById(R.id.lv_tv_2);
			holder.check = (CheckBox) convertView.findViewById(R.id.lv_cb);

			convertView.setTag(holder);

		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		final boolean postApp =tasks.get(position).appName.equals(ApkInfoUtil.getMyApkName());

		holder.icon.setImageDrawable((Drawable) tasks.get(position).icon);
		holder.name.setText(tasks.get(position).appName);
		holder.memeory.setText("内存占用 :"+tasks.get(position).memory);
	
		if (postApp)
		{
			holder.check.setVisibility(View.INVISIBLE);
		}
		else
		{
			holder.check.setVisibility(View.VISIBLE);
			holder.check.setChecked(tasks.get(position).check);
		}
		
		return convertView;

	}

	public final class ViewHolder
	{
		public ImageView icon;
		public TextView name;
		public TextView memeory;
		public CheckBox check;
	}


}
