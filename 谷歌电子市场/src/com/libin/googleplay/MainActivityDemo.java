package com.libin.googleplay;

import com.libin.googleplay.fragment.AppFragment;
import com.libin.googleplay.fragment.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


/**
 * ������ʾ��actionBar�����actionBar���tab
 * @author Administrator
 *
 */
public class MainActivityDemo extends BaseActivity implements OnQueryTextListener  {

	private DrawerLayout drawer;
	private ActionBar actionBar;
	private Tab tab1,tab2,tab3,tab4;
	
	private ActionBarDrawerToggle drawerToggle;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getSupportActionBar();
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		mViewPager=(ViewPager) findViewById(R.id.vp_pager);
		//Ҫ����tab����֮ǰ����Ȼ��tab�л�ʱ���ò���ViewPager,����ָ���쳣
		mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//��ActionBar�������һ��tab��
		tab1 = actionBar.newTab().setText("��ǩһ").setTabListener(new MyTabListener());
		actionBar.addTab(tab1);
		tab2 = actionBar.newTab().setText("��ǩ��").setTabListener(new MyTabListener());
		actionBar.addTab(tab2);
		tab3 = actionBar.newTab().setText("��ǩ��").setTabListener(new MyTabListener());
		actionBar.addTab(tab3);
		tab4 = actionBar.newTab().setText("��ǩ��").setTabListener(new MyTabListener());
		actionBar.addTab(tab4);
		
		drawer = (DrawerLayout) findViewById(R.id.drawer);
		
		drawerToggle = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_drawer_am, R.string.open_drawer,R.string.close_drawer){
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				Toast.makeText(getApplicationContext(), "����ر���", 0).show();
			}
			
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				Toast.makeText(getApplicationContext(), "�������", 0).show();
			}
		};
		
		//�ÿ��غ�actionBar������ϵ
		drawer.setDrawerListener(drawerToggle);
		drawerToggle.syncState();
		
		//�ڷ�����onOptionsItemSelected(MenuItem item)������
		
		//Ҫ����tab����֮�󣬲�Ȼ���л�viewpagerʱ���ò���tab������ָ���쳣
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int arg0) {
				super.onPageSelected(arg0);
				getSupportActionBar().setSelectedNavigationItem(arg0);
			}
			
		});
		
	}
	
	/**
	 * ��ǩ�л�������
	 * @author Administrator
	 *
	 */
	private class MyTabListener implements TabListener{

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			//��tab��ǩ��ѡ��ʱ����ViwePager���ó���Ӧ��ҳ��
			mViewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	 // ������еĻ��� (����ʲô�汾���ֻ� )����3.0
 		if (android.os.Build.VERSION.SDK_INT > 11) {
 			SearchView searchView = (SearchView) menu.findItem(
 					R.id.action_search).getActionView();
 			searchView.setOnQueryTextListener(this);// �����ļ���
 		} 
	    return true;
	}
	/**
	 *����actionBar�˵���Ŀ�ĵ���¼�
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   if(item.getItemId()==R.id.action_search){//id�Ѿ���menu/main.xml�ļ�������
		   Toast.makeText(getApplicationContext(), "����", 0).show();
	   }
	   //��drawerToggleȥ����ѡ�е�item
	   return drawerToggle.onOptionsItemSelected(item)|super.onOptionsItemSelected(item);
	}
	
	//�������ı������仯ʱ����
	@Override
	public boolean onQueryTextChange(String arg0) {
		
		return true;
	}
	//�ύʱ����
	@Override
	public boolean onQueryTextSubmit(String arg0) {
		Toast.makeText(getApplicationContext(), arg0, 0).show();
		return true;
	}
	
	public  void onClick(View v){
		startActivity(new Intent(getApplicationContext(),DetailActivity.class));
	}
	
	/**
	 * mViewPager��������
	 * @author Administrator
	 *
	 */
	private class MainAdapter extends FragmentStatePagerAdapter{

		public MainAdapter(FragmentManager fm) {
			super(fm);
		}

		/**
		 * ÿ����ǩ���ص�Fragment,��0��ʼ
		 */
		@Override
		public Fragment getItem(int arg0) {
			if(arg0==0){
				return new HomeFragment();
			}else{
				return new AppFragment();
			}
			
		}
		
		//һ�����ٸ���ǩ
		@Override
		public int getCount() {
			return 4;
		}
		//����ÿ��pager�ı���
		@Override
		public CharSequence getPageTitle(int position) {
			
			return "����"+position;
			
			//��ViewPager�������һ���ؼ���android.support.v4.view.PagerTitleStrip
			//�ͻ���ViewPager��������ʾһ��tab��������ķ���ֵ����tab���ı��⣬��ʱ�ı����ǲ��ɵ���ģ���Ҫ����л��Ļ���Ҫ��PagerTitleStrip����PagerTabStrip
			//���ˣ�ActionBar��ӵļ���tab�Ϳ�����ȥ�ˡ���ش��������ɾ��
		}
	}
}
