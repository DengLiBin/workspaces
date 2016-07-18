package com.bin.tabhost;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		TabHost tabHost=getTabHost();
		
		LayoutInflater.from(this).inflate(R.layout.activity_main, tabHost.getTabContentView(),true);
		
		//添加第一个标签页
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("已接电话").setContent(R.id.tab_01));
		
		//添加第二个标签页,在标签题上放置图标
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("呼出电话",getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.tab_02));
		//添加第三个标签页
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("未接电话").setContent(R.id.tab_03));
	}
	
}
