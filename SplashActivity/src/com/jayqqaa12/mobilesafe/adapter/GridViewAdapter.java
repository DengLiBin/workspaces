package com.jayqqaa12.mobilesafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.core.adapter.AbaseBaseAdapter;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.abase.util.ResUtil;
import com.jayqqaa12.mobilesafe.R;

public class GridViewAdapter extends AbaseBaseAdapter
{
	private LayoutInflater inflater;
	private ImageView icon;
	private TextView title;
	private static final int icons[] = { R.drawable.widget0, R.drawable.widget1, R.drawable.widget2, R.drawable.widget3, R.drawable.widget4,
			R.drawable.widget5, R.drawable.widget6, R.drawable.widget7, R.drawable.widget8 };
	private static String names[];

	public GridViewAdapter(Context context)
	{
		inflater = LayoutInflater.from(context);
		if (names == null) names = ResUtil.getStringArray( R.array.main_icon_titles);
	}

	@Override
	public int getCount()
	{
		return icons.length;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		View view = inflater.inflate(R.layout.gv_item, null);
		icon = (ImageView) view.findViewById(R.id.gv_iv);
		title = (TextView) view.findViewById(R.id.gv_tv);
		icon.setImageResource(icons[position]);
		title.setText(names[position]);

		if (position == 0)
		{
			boolean lost_hidden = ConfigSpUtil.getBoolean("lost_hidden", false);
			if (lost_hidden == true)
				view = null;
		}

		return view;
	}

}
