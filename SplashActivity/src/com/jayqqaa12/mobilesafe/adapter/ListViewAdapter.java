package com.jayqqaa12.mobilesafe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.core.adapter.AbaseBaseAdapter;
import com.jayqqaa12.abase.util.ResUtil;
import com.jayqqaa12.mobilesafe.R;

public class ListViewAdapter extends AbaseBaseAdapter
{
	private int [] icons ;
	private String [] titles;
	private String [] descibers;
	private Context context;
	private ImageView  iv_icon;
	private TextView tv_title,tv_describer;

	public ListViewAdapter(int[] icons, String[] titles, String[] describes,Context context)
	{
		super();
		this.icons = icons;
		this.titles = titles;
		this.descibers = describes;
		this.context = context;
	}

	public ListViewAdapter(int[] icons, int[] titles, int[] describes, Context context)
	{
		
		super();
		this.icons = icons;
		this.titles = ResUtil.getStringArray(titles);
		this.descibers = ResUtil.getStringArray(describes);
		this.context = context;
		
	}

	@Override
	public int getCount()
	{
		return icons.length;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if(convertView!=null) return convertView;
		
		
		View view = View.inflate(context, R.layout.lv_item,null );
		
		iv_icon = (ImageView) view.findViewById(R.id.lv_iv);
		tv_describer = (TextView) view.findViewById(R.id.lv_tv_2);
		tv_title = (TextView) view .findViewById(R.id.lv_tv_1);
		
		iv_icon.setImageResource(icons[position]);
		tv_describer.setText(descibers[position]);
		tv_title.setText(titles[position]);
		return view;
	}

}
