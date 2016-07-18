package com.bin.clock;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TabHost;

public class MainActivity extends Activity {
	private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر�����
		setContentView(R.layout.activity_main);
		
		tabHost=(TabHost) findViewById(android.R.id.tabhost);//��id������ϵͳ��id�������Լ�����
		tabHost.setup();
		
		tabHost.addTab(tabHost.newTabSpec("tab_Time").setIndicator("ʱ��").setContent(R.id.tab_time));
		tabHost.addTab(tabHost.newTabSpec("tab_Alarm").setIndicator("����").setContent(R.id.tab_alarm));
		tabHost.addTab(tabHost.newTabSpec("tab_Timer").setIndicator("��ʱ��").setContent(R.id.tab_timer));
		tabHost.addTab(tabHost.newTabSpec("tab_StopWatch").setIndicator("���").setContent(R.id.tab_StopWatch));
	}

}
/*
����tabhostλ�ڵײ������ַ���
����һ
1��tabcontent��tabs����λ��(��:��ǰactivity_main.xml���־���)
 
2������tabcontent�����ԣ�android:layout_weight="1"

������
1��tabcontent��tabs����λ��
 
2����tabs�ŵ�һ��relativeLayout�У�Ȼ������������ԣ�android:layout_alignParentBottom="true"

������
1����tabcontent��tabs����λ�ã�tabs�ƶ���LinearLayout��ǩ���£�
2����tabcontent�м������ԣ�android:layout_gravity="top"
3����tabs�м������ԣ�android:layout_gravity="bottom" 

*/