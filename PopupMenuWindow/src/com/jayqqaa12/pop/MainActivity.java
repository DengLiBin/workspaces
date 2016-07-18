package com.jayqqaa12.pop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;

import com.jayqqaa12.abase.core.APopup;
import com.jayqqaa12.abase.core.Abase;
public class MainActivity extends Activity
{
	
	APopup pop;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Abase.setContext(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity_main);
		pop= new MenuPop();
		
	}



	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		if (!pop.isShowing()) pop.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
		
		return false;
	}

}
