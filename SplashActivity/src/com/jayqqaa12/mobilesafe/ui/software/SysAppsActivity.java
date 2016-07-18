package com.jayqqaa12.mobilesafe.ui.software;

import android.os.Bundle;

public class SysAppsActivity extends AppsActivity
{

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setSysApps(true);
		super.onCreate(savedInstanceState);
	}
}
