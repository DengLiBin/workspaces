package com.jayqqaa12.mobilesafe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jayqqaa12.abase.core.adapter.AbaseBaseAdapter;
import com.jayqqaa12.mobilesafe.R;



/**
 * 这种 类 虽然 不是 很必要 多少  如果 类似的 布局多可以 省事
* @author jayqqaa12 
* @date 2013-5-4
 */
public class ListViewSettingAdapter extends AbaseBaseAdapter
{
	private Context context;
	private String[] describes;
	private String[] titles;



	public ListViewSettingAdapter(String[] titles, String[] describes, Context context)
	{
		this.context = context;
		this.titles =titles;
		this.describes = describes;
	}

	@Override
	public int getCount()
	{
		return titles.length;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = null;
		if (convertView != null)
		{
			view = convertView;
		}
		else
		{
			view = View.inflate(context, R.layout.lv_item_settings, null);
		}
		
		TextView title = (TextView) view.findViewById(R.id.lv_tv_1);
		title.setText(titles[position]);
		TextView describe = (TextView) view.findViewById(R.id.lv_tv_2);
		describe.setText(describes[position]);


		return view;
	}

}
