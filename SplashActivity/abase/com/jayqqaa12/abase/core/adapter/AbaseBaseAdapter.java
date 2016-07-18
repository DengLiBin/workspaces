package com.jayqqaa12.abase.core.adapter;

import android.widget.BaseAdapter;

public abstract class AbaseBaseAdapter extends BaseAdapter
{
	

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}


}
