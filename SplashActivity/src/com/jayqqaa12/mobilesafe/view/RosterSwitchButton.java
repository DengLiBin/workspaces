package com.jayqqaa12.mobilesafe.view;
import android.content.Context;
import android.util.AttributeSet;

import com.appipv6.android.slipbutton.SlipClickButtonTpl;
import com.jayqqaa12.mobilesafe.R;

public class RosterSwitchButton extends SlipClickButtonTpl
{

	public RosterSwitchButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public RosterSwitchButton(Context context)
	{
		super(context);
	}

	public RosterSwitchButton(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public void init()
	{
		this.buttonType=SLIPBUTTON;
		this.btnLocationType=OKRIGHT;
		
		bg_on=getBitmap(R.drawable.switchson);
		bg_off=getBitmap(R.drawable.switchsoff);
		bg_myButton=getBitmap(R.drawable.switchbutton);
	}


}