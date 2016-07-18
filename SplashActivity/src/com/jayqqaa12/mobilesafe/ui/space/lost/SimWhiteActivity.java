package com.jayqqaa12.mobilesafe.ui.space.lost;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.core.adapter.AbaseAdapter;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.comm.PhoneUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.domain.Sim;

public class SimWhiteActivity extends AbaseActivity
{
	private static final String TAG = "SimWhiteActivity";
	@FindView(id =  R.id.ll_add, click = true)
	private LinearLayout ll_add;
	@FindView(id = R.id.lv,contextMenu=true)
	private ListView lv;
	@FindView(id =  R.id.ll_empty)
	private LinearLayout ll_empty;
	@FindView(id =R.id.title_1_tv ,textId=R.string.lost_sim_lable)
	private TextView tv_title;
	private AbaseAdapter<Sim> adapter;
	private List<Sim> list;
	
	private AbaseDao dao = AbaseDao.create();
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_sim);
		list = dao.findAll(Sim.class);
		adapter = new AbaseAdapter<Sim>(this, list,R.layout.lv_item_contact,new String[]{"name","sim"}, new int[]{R.id.lv_tv_1,R.id.lv_tv_2});
		lv.setAdapter(adapter);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lost_sim, menu);

	}

	@Override
	protected void onResume()
	{
		Log.i(TAG,"resume");
		super.onResume();
		initDate();
		
		
	}

	private void initDate()
	{
		if (dao.count(Sim.class) == 0)
		{
			ll_empty.setVisibility(View.VISIBLE);
			lv.setVisibility(View.INVISIBLE);
		}

		else
		{
			Log.i(TAG,"init data");

			ll_empty.setVisibility(View.INVISIBLE);
			lv.setVisibility(View.VISIBLE);
			list = dao.findAll(Sim.class);
			
			adapter.notifyDataSetChanged();
		}

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case  R.id.ll_add:
			// add sim white

			if (TextUtils.isEmpty(PhoneUtil.getSimSerialNumber()))
			{
				ToastUtil.ShortToast("当前 sim 卡为空");
			}
			else if (dao.isFindByWhere(Sim.class, "sim="+PhoneUtil.getSimSerialNumber()))
			{
				ToastUtil.ShortToast("当前 sim 卡已存在");
			}

			else
				IntentUtil.startIntent(this, SimAddActivity.class);

			break;

		default:
			break;
		}

	}

	
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		// get sim id
		
		
		Sim sim = list.get(info.position);
		
		int id = sim.getId();
		
		switch (item.getItemId())
		{
		case R.id.menu_lost_sim_delete:
			
			Log.i(TAG,"id"+id);
			
			dao.delete(id+"");
			
			initDate();
			break;
		case R.id.menu_lost_sim_edit:
		
			Intent intent = new Intent(this,SimAddActivity.class);
			intent.putExtra("sim",sim );
			startActivity(intent);
			
			break;

		default:
			break;
		}

		return false;
	}

}
