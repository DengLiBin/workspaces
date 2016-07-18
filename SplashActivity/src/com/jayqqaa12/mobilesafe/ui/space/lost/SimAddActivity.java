package com.jayqqaa12.mobilesafe.ui.space.lost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.comm.PhoneUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.domain.Sim;

public class SimAddActivity extends AbaseActivity
{
	
	@FindView(id = R.id.et_sim)
	private EditText et_sim;
	@FindView(id = R.id.et_name)
	private EditText et_name;
	@FindView(id = R.id.bt_save, click = true)
	private Button bt_save;
	@FindView(id = R.id.bt_cancle, click = true)
	private Button bt_canel;
	@FindView(id =R.id.title_1_tv ,textId=R.string.add)
	private TextView tv_title;
	
	private AbaseDao dao = AbaseDao.create();

	private Sim sim;


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost_sim_add);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		Intent intent = getIntent();
		sim = (Sim) intent.getSerializableExtra("sim");

		if (sim != null)
		{
			et_sim.setText(sim.getSim());
			et_name.setText(sim.getName());
		}
		else
		{
			et_sim.setText(PhoneUtil.getSimSerialNumber());
		}
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
		case R.id.bt_save:
			// save sim
			if (sim == null)
			{
				Sim sim = new Sim();
				sim.setName(et_name.getText().toString());
				sim.setSim(et_sim.getText().toString());
				
				dao.save(sim);
			}
			else
			{
				sim.setName(et_name.getText().toString());
				dao.update(sim);
			}
			finish();
			break;

		case R.id.bt_cancle:
			finish();
			break;

		default:
			break;
		}

	}

}
