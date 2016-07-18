package com.jayqqaa12.mobilesafe.view;
import android.content.Context;
import android.util.AttributeSet;

import com.appipv6.android.slipbutton.SlipClickButtonTpl;
import com.jayqqaa12.mobilesafe.R;

public class LockSwitchButton extends SlipClickButtonTpl
{

	public LockSwitchButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public LockSwitchButton(Context context)
	{
		super(context);
	}
	public LockSwitchButton(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public void init()
	{
		this.buttonType=SLIPBUTTON;
		this.btnLocationType=OKLEFT;
		
		bg_on=getBitmap(R.drawable.switchsoff);
		bg_off=getBitmap(R.drawable.switchson);
		bg_myButton=getBitmap(R.drawable.switchbutton);
	}


}