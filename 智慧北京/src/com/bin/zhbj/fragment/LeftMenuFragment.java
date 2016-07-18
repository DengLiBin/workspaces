package com.bin.zhbj.fragment;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import com.bin.zhbj.MainActivity;
import com.bin.zhbj.R;
import com.bin.zhbj.base.impl.NewsCenterPager;
import com.bin.zhbj.domain.NewsData;
import com.bin.zhbj.domain.NewsData.NewMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * �����
 * @author Administrator
 *
 */
public class LeftMenuFragment extends BaseFragment {

	private ListView lvList;
	private ArrayList<NewMenuData> mMenuList;
	private int mCurrentItem;
	private MenuAdapter mAdapter;

	@Override
	public View initView() {
		View fragment_left_menu=View.inflate(mActivity, R.layout.fragment_left_menu, null);
		lvList = (ListView) fragment_left_menu.findViewById(R.id.lv_list);
		return fragment_left_menu;
		
	}
	@Override
	public void initData() {
		
		lvList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mCurrentItem = position;
				mAdapter.notifyDataSetChanged();//��ˢ��Adapter,������getVeiw()����
				
				MainActivity mainUi=(MainActivity) mActivity;
				ContentFragment fragment=mainUi.getContentFragment();
				NewsCenterPager newsPage=(NewsCenterPager) fragment.getBasePager(1);//����ҳ����Ϊ1
				newsPage.setCurrentMenuDetailPage(position);//���õ�ǰ�˵�����ҳ
				
				toggleSlidingMenu();//�л�slidingMenu��״̬
			}
			
		});
	}
	/**
	 * ��������
	 */
	public void setMenuData(NewsData data){
		mAdapter=new MenuAdapter();
		mMenuList = data.data;
		lvList.setAdapter(mAdapter);
	}
	class MenuAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mMenuList.size();
		}

		@Override
		public NewMenuData getItem(int position) {
			return mMenuList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view=View.inflate(mActivity, R.layout.list_menu_item, null);
			TextView tvTitle=(TextView) view.findViewById(R.id.tv_menutitle);
			NewMenuData newsMenuData=getItem(position);
			tvTitle.setText(newsMenuData.title);
			tvTitle.setEnabled(mCurrentItem==position);//ע�⣺mCurrentItemĬ����0,����Ĭ�ϵ�0��listView��ѡ�е�
			return view;
		}
		
	}
	/**
	 * ����slidingMenu����ʾ������
	 * @param show
	 */
	protected void toggleSlidingMenu(){
		MainActivity mainUi=(MainActivity) mActivity;
		SlidingMenu slidingMenu=mainUi.getSlidingMenu();
		slidingMenu.toggle();//״̬�л�����ʾʱ�����أ�����ʱ����ʾ
	}

}
