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
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
		setContentView(R.layout.activity_main);
		
		tabHost=(TabHost) findViewById(android.R.id.tabhost);//该id必须是系统的id，不能自己定义
		tabHost.setup();
		
		tabHost.addTab(tabHost.newTabSpec("tab_Time").setIndicator("时钟").setContent(R.id.tab_time));
		tabHost.addTab(tabHost.newTabSpec("tab_Alarm").setIndicator("闹钟").setContent(R.id.tab_alarm));
		tabHost.addTab(tabHost.newTabSpec("tab_Timer").setIndicator("计时器").setContent(R.id.tab_timer));
		tabHost.addTab(tabHost.newTabSpec("tab_StopWatch").setIndicator("秒表").setContent(R.id.tab_StopWatch));
	}

}
/*
设置tabhost位于底部的三种方法
方法一
1、tabcontent和tabs交换位置(如:当前activity_main.xml布局就是)
 
2、设置tabcontent的属性：android:layout_weight="1"

方法二
1、tabcontent和tabs交换位置
 
2、将tabs放到一个relativeLayout中，然后加上如下属性：android:layout_alignParentBottom="true"

方法三
1、将tabcontent和tabs交换位置（tabs移动到LinearLayout标签以下）
2、在tabcontent中加入属性：android:layout_gravity="top"
3、在tabs中加入属性：android:layout_gravity="bottom" 

*/