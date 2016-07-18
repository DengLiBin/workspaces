package com.jayqqaa12.pop;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.jayqqaa12.abase.core.APopup;
import com.jayqqaa12.abase.kit.ManageKit;

public class MenuPop extends APopup implements OnItemClickListener
{
	MenuAdapter adapter ;

	@Override
	protected View initView()
	{
		View view = ManageKit.getInflater().inflate(R.layout.test_menu, null);
		GridView gv = (GridView) view.findViewById(R.id.gv);
		adapter= new MenuAdapter();
		gv.setAdapter(adapter);
		this.setAnimationStyle(R.style.AnimBottom);
		gv.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		
		switch (adapter.getItem(position).ation)
		{
		case Menu.ATION_TOC:

			break;
		case Menu.ATION_SETTING:

			break;
		case Menu.ATION_FONT_ADD:

			break;
		case Menu.ATION_FONT_DIM:

			break;
		case Menu.ATION_NIGHT:
			break;
		case Menu.ATION_PROGRESS:

			break;

		}

	}

}
