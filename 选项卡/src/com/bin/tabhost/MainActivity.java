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
		
		//��ӵ�һ����ǩҳ
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("�ѽӵ绰").setContent(R.id.tab_01));
		
		//��ӵڶ�����ǩҳ,�ڱ�ǩ���Ϸ���ͼ��
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("�����绰",getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.tab_02));
		//��ӵ�������ǩҳ
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("δ�ӵ绰").setContent(R.id.tab_03));
	}
	
}
