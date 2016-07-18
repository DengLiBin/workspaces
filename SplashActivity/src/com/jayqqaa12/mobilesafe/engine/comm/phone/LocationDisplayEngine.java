package com.jayqqaa12.mobilesafe.engine.comm.phone;

import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.config.Style;
import com.jayqqaa12.mobilesafe.config.Timeout;

public class LocationDisplayEngine extends AbaseEngine
{
	
	public void onDisplay()
	{
		ConfigSpUtil.setValue(Config.PHONE_LOCATION_DISPLAY_ENABLE, true);
	}

	public void offDisplay()
	{
		ConfigSpUtil.setValue(Config.PHONE_LOCATION_DISPLAY_ENABLE, false);
	}

	public boolean isOpenService()
	{
		return sp.getBoolean(Config.PHONE_LOCATION_DISPLAY_ENABLE, true);
	}

	public void setDisplayTimeout(Timeout time)
	{
		ConfigSpUtil.setValue(Config.PHONE_LOCATION_DISPLAY_TIMEOUT, time.getValue());
	}

	public Integer getDisplayTimeout()
	{
		return sp.getInt(Config.PHONE_LOCATION_DISPLAY_TIMEOUT, Timeout.FOREVEN.getValue());
	}

	public Integer getDisplayStyle()
	{
		return sp.getInt(Config.PHONE_LOCATION_DISPLAY_STYLE, Style.GRAY.ordinal());
	}

	public void setDisplayStyle(Style value)
	{
		ConfigSpUtil.setValue(Config.PHONE_LOCATION_DISPLAY_STYLE, value.ordinal());
	}

	public int getDragX()
	{
		return sp.getInt(Config.PHONE_LOCATION_DISPLAY_DRAG_X, Config.CENTER_X);
	}

	public int getDragY()
	{
		return sp.getInt(Config.PHONE_LOCATION_DISPLAY_DRAG_Y, Config.CENTER_Y);
	}

	public void setDragX(int value)
	{
		ConfigSpUtil.setValue(Config.PHONE_LOCATION_DISPLAY_DRAG_X, value);
	}

	public void setDragY(int value)
	{
		ConfigSpUtil.setValue(Config.PHONE_LOCATION_DISPLAY_DRAG_Y, value);
	}

	public int getDisplayStyleDrwable()
	{

		return Style.getDrawable(getDisplayStyle());
	}

	
	
}
