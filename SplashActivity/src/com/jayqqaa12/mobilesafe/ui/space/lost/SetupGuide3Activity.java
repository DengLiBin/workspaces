package com.jayqqaa12.mobilesafe.ui.space.lost;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseGestureAllActivity;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.engine.space.lost.LostEngine;

public class SetupGuide3Activity extends AbaseGestureAllActivity
{
	private static final String TAG = "SetupGudie3Activity";
	
	@FindView(id = R.id.lost_guide3_ll,gesture=true)
	private LinearLayout ll;
	@FindView(id = R.id.lost_guide3_bt_select_contact,click=true)
	private Button bt_select_contact;
	@FindView(id = R.id.lost_guide3_et_number,textChanged=true)
	private EditText et_number;
	
	@FindEngine
	private LostEngine engine;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide3);
		// became hava intent can't on resume init
		String phone = engine.getProtectedNumber();
		
		if (phone != null) et_number.setText(phone);
		setChangeActivity(this, SetupGuide2Activity.class, SetupGuide4Activity.class);

	}
	
	@Override
	public void afterTextChanged(Editable s)
	{
		// TODO 应该写个 错误 提示框
		if (et_number.length()>11||et_number.length() == 11 && !et_number.getText().toString().matches(Config.PHONE_PATTERN))
		{
			ToastUtil.ShortToast("手机号码输入有误");
		}

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lost_guide3_bt_select_contact:

			Intent intent = new Intent(this, SelectContactActivity.class);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (data != null && resultCode == 0)
		{
			String phone = data.getStringExtra("phone");
			et_number.setText(phone);

			Log.i(TAG,phone);
		}

	}

	@Override
	protected void onPause()
	{
		super.onPause();
		// save phone
		String phone = et_number.getText().toString();
		if (phone.matches(Config.PHONE_PATTERN))
		{
			engine.setProtectedNumber(phone);
		}

	}
}
