package com.bin.slidmenu;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * ���slidingmenu_library�⣬�����ÿ��v4�����ƹ����滻ԭ����v4��
 * @author Administrator
 *
 */
public class MainActivity extends SlidingFragmentActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);//���ò��������
		
		SlidingMenu slidingMenu=getSlidingMenu();//��ȡ���������
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//����ȫ������
		slidingMenu.setSecondaryMenu(R.layout.right_menu);//�����Ҳ����
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//����ģʽ�����Ҷ��в����
		
		slidingMenu.setBehindOffset(100);// ����Ԥ����Ļ�Ŀ��
		
	}
	
}