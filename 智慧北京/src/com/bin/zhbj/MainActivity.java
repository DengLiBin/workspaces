package com.bin.zhbj;


import com.bin.zhbj.bmob.Json;
import com.bin.zhbj.fragment.ContentFragment;
import com.bin.zhbj.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends SlidingFragmentActivity {
	private static final String FRAGMENT_LEFT_MENU =  "fragment_left_menu";////标记。可以根据标记找到该fragment
	private static final String FRAGMENT_CONTENT = "fragment_content";
	private FragmentManager fm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
		setContentView(R.layout.activity_main);
		//saveDataToBmob();
		setBehindContentView(R.layout.left_menu);// 设置侧边栏
		SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置触摸范围
		//拿到屏幕宽度
		int width=getWindowManager().getDefaultDisplay().getWidth();
		slidingMenu.setBehindOffset(width*200/320);// 设置预留屏幕的宽度(200个像素，写死了不是很好,设置占屏幕宽度的百分比，注意是整数)
		
		initFragment();
	}
	/**
	 * 初始化fragment,将fragment填充给布局（frameLayout）文件
	 * 
	 */
	private void initFragment(){
		fm = getSupportFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();//开启事务
		
		//用fragment替换frameLayout
		ft.replace(R.id.activity_main, new ContentFragment(),FRAGMENT_CONTENT);//标记。可以根据标记找到该fragment
		ft.replace(R.id.left_menu, new LeftMenuFragment(),FRAGMENT_LEFT_MENU);
		
		ft.commit();//提交事务
	}
	
	private void saveDataToBmob(){
		Bmob.initialize(this, "7f7e7486632f08fdb780bc60852c589f");
		Json json = new Json();
		json.setId("1");
		json.setJson("{'retcode':200,'data':[{'id':10000,'title':'新闻','type':1,'children':[{'id':10007,'title':'北京','type':1,'url':'/10007/list_1.json'},{'id':10006,'title':'中国','type':1,'url':'/10006/list_1.json'},{'id':10008,'title':'国际','type':1,'url':'/10008/list_1.json'},{'id':10010,'title':'体育','type':1,'url':'/10010/list_1.json'},{'id':10091,'title':'生活','type':1,'url':'/10091/list_1.json'},{'id':10012,'title':'旅游','type':1,'url':'/10012/list_1.json'},{'id':10095,'title':'科技','type':1,'url':'/10095/list_1.json'},{'id':10009,'title':'军事','type':1,'url':'/10009/list_1.json'},{'id':10093,'title':'时尚','type':1,'url':'/10093/list_1.json'},{'id':10011,'title':'财经','type':1,'url':'/10011/list_1.json'},{'id':10094,'title':'育儿','type':1,'url':'/10094/list_1.json'},{'id':10105,'title':'汽车','type':1,'url':'/10105/list_1.json'}]},{'id':10002,'title':'专题','type':10,'url':'/10006/list_1.json','url1':'/10007/list1_1.json'},{'id':10003,'title':'组图','type':2,'url':'/10008/list_1.json'},{'id':10004,'title':'互动','type':3,'excurl':'','dayurl':'','weekurl':''}],'extend':[10007,10006,10008,10014,10012,10091,10009,10010,10095]}");
		json.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(MainActivity.this, "注册失败：" + arg1,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	//获取侧边栏LeftMenuFragment
	public LeftMenuFragment getLeftMenuFragment(){
		return (LeftMenuFragment) fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
	}
	//获取ContentFragment
	public ContentFragment getContentFragment(){
		return (ContentFragment) fm.findFragmentByTag(FRAGMENT_CONTENT);
	}

	
}
