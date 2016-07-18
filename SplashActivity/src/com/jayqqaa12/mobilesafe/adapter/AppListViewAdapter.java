package com.jayqqaa12.mobilesafe.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.core.adapter.AbaseBaseAdapter;
import com.jayqqaa12.abase.util.sys.ApkInfo;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;
import com.jayqqaa12.mobilesafe.R;

public class AppListViewAdapter extends AbaseBaseAdapter
{
	protected static final String TAG = "CheckBoxListViewAdapter";
	private LayoutInflater mInflater;
	private List<ApkInfo> data;

	public AppListViewAdapter(Context context, List<ApkInfo> data)
	{
		this.data = data;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		return data == null ? 0 : data.size();
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		if (convertView == null)
		{
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.lv_item_app, null);

			holder.icon = (ImageView) convertView.findViewById(R.id.lv_iv);
			holder.title = (TextView) convertView.findViewById(R.id.lv_tv_1);
			holder.describe = (TextView) convertView.findViewById(R.id.lv_tv_2);
			holder.check = (CheckBox) convertView.findViewById(R.id.lv_cb);

			convertView.setTag(holder);

		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		final boolean isMyapp = data.get(position).appName.equals(ApkInfoUtil.getMyApkName());

		holder.icon.setImageDrawable((Drawable) data.get(position).iconDrawable);
		holder.title.setText(data.get(position).appName.toString());
		holder.describe.setText(data.get(position).size.toString());

		holder.check.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!isMyapp) data.get(position).check = !data.get(position).check;
			}
		});

		if (isMyapp)
		{
			holder.check.setVisibility(View.INVISIBLE);
		}
		else
		{
			holder.check.setVisibility(View.VISIBLE);
			holder.check.setChecked((Boolean) data.get(position).check);
		}

		return convertView;
	}

	public final class ViewHolder
	{
		public ImageView icon;
		public TextView title;
		public TextView describe;
		public CheckBox check;
	}

}
