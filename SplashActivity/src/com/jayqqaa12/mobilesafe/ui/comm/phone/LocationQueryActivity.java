package com.jayqqaa12.mobilesafe.ui.comm.phone;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseTextActivity;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.comm.phone.LocationNumberEngine;

public class LocationQueryActivity extends AbaseTextActivity
{
	private static final String TAG = "PhoneLocationQueryActivity";

	@FindView(id = R.id.et_input,textChanged=true)
	private EditText et_input;
	@FindView(id = R.id.tv_output)
	private TextView tv_output;
	@FindView(id =R.id.title_1_tv ,textId=R.string.phone_location_query_lable)
	private TextView tv_title;
	
	@FindEngine
	private LocationNumberEngine engine;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_location_query);
	}

	@Override
	public void afterTextChanged(Editable s)
	{
		Log.i(TAG, "editor is" + et_input.getText());
		
		if (et_input.length()>2&&et_input.length()<15)
		{
			String address =engine.getAddress(et_input.getText().toString());
			tv_output.setText(address);
		}
		else
		{
			
			tv_output.setText(getString(R.string.phone_location_query_lable_error));
		}
		
		
	}

}
