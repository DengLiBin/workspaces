package com.jayqqaa12.pop;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.kit.ManageKit;

class MenuAdapter extends BaseAdapter
{
	List<Menu> data = new ArrayList<Menu>();

	public  MenuAdapter(){
		
		data.add(new Menu(R.drawable.icon_item_directory, "toc", Menu.ATION_TOC));
		data.add(new Menu(R.drawable.icon_item_bright, "night", Menu.ATION_NIGHT));
		data.add(new Menu(R.drawable.icon_bookshelf_set_up, "setting", Menu.ATION_SETTING));
		data.add(new Menu(R.drawable.icon_btn_font_big, "font_add", Menu.ATION_FONT_ADD));
		data.add(new Menu(R.drawable.icon_btn_font_small, "font_dim", Menu.ATION_FONT_DIM));
		data.add(new Menu(R.drawable.icon_item_progress, "progress", Menu.ATION_PROGRESS));
		
	}
	

	@Override
	public int getCount()
	{
		return data.size();
	}

	@Override
	public Menu getItem(int position)
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = ManageKit.getInflater().inflate(R.layout.test_menu_item, null);
		TextView tv = (TextView) view.findViewById(R.id.m_tv);
		ImageView iv = (ImageView) view.findViewById(R.id.m_iv);
		tv.setText(getItem(position).text);
		iv.setImageResource(getItem(position).icon);
		

		return view;
	}
	
	

}
