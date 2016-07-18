package com.jayqqaa12.mobilesafe.ui.software;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.jayqqaa12.abase.core.activity.AbaseTabActivity;
import com.jayqqaa12.mobilesafe.R;

public class SoftwareMainActivity extends AbaseTabActivity
{

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_software);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		int[][] widgetResIds = { { R.drawable.icon_software_install_selector, R.string.software_uninstall_label },
				{ R.drawable.icon_software_apk_selector, R.string.software_apk_label } };

		initMyTab(tabHost, R.layout.tab_widget_2, widgetResIds, new int[] { R.id.tab_widget_2_iv, R.id.tab_widget_2_tv },
				new String[] { getString(R.string.software_uninstall_label), getString(R.string.software_apk_label) }, new Intent[] { new Intent(
						this, UninstallActivity.class) ,new Intent(this,ApkActivity.class)});
	}

}
