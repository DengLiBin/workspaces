package com.jayqqaa12.mobilesafe.ui.software;

import android.os.Bundle;

public class PreloadAppsActivity extends AppsActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setPreLoadApp(true);
		super.onCreate(savedInstanceState);
	}
}
