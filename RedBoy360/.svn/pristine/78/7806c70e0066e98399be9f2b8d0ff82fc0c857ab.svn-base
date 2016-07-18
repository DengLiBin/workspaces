package com.shopping.redboy.view.categoryDetail;

import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.view.BaseView;

@ResID(id = R.layout.sift_list2)
@Category(id = R.id.imgClassify, title = "筛选", leftbutton = "返回", rightbutton = "")
public class SiftView2 extends BaseView implements ButtonClickListener{
	
	@ResID(id=R.id.categoryList)
	private ListView categoryList;

	@ResID(id=R.id.categoryEmptyListTv)
	private TextView categoryEmptyListTv;
	
	
	private String[] list;
	private String[] brand;
	private String[] price;
	private String[] function;
	private String[] number;

	private MyAdapter adapter ;
	
	private int positionM = 0;
	
	public SiftView2(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		list = new String[] {};
		brand = new String[] { "雅培", "天线宝宝", "海绵宝宝", "奥特曼", "葫芦娃",
				"憨豆", "脚趾产", "两个老虎", "毛和老鼠", "薯塔", "被他", "欧米伽" };
		price = new String[] { "1000", "2000", "3000", "4000",
				"5000", "6000", "7000", "8000" };
		function = new String[] { "打家", "劫舍", "杀人", "放火", "无恶不作" };
		number = new String[] { "有货", "无货", "暂缺" };
		adapter = new MyAdapter();
		categoryList.setAdapter(adapter);
	}

	@Override
	protected void setListener() {
		categoryList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map<String, Object> map = UIManager.getInstance().getMap();
				map.put("position", positionM);
				map.put("value", list[position]);
				UIManager.getInstance().goback();
			}
		});
	}

	@Override
	public void onResume() {
		categoryEmptyListTv.setVisibility(View.VISIBLE);
		Map<String, Object> map = UIManager.getInstance().getMap();
		int position = (Integer) map.get("siftCategory");
		switch (position) {
		case 0:
			this.positionM = 0;
			list = brand;
			adapter.notifyDataSetChanged();
			break;
		case 1:
			this.positionM = 1;
			list = function;
			adapter.notifyDataSetChanged();
			break;
		case 2:
			this.positionM = 2;
			list = price;
			adapter.notifyDataSetChanged();
			break;
		case 3:
			this.positionM = 3;
			list = number;
			adapter.notifyDataSetChanged();
			break;

		}
		map.clear();
		categoryEmptyListTv.setVisibility(View.INVISIBLE);
		super.onResume();
	}

	/**
	 * 中间listView的适配器
	 * 
	 * @author hanli
	 * 
	 */
	private class MyAdapter extends BaseAdapter {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			RelativeLayout layout = null;
			Holder holder = null;
			if (convertView == null) {
				layout = (RelativeLayout) View.inflate(context,
						R.layout.category_child_item, null);
				holder = new Holder();
				holder.name = (TextView) layout.findViewById(R.id.textContent);
				layout.setTag(holder);
			} else {
				layout = (RelativeLayout) convertView;
				holder = (Holder) layout.getTag();
			}
			holder.name.setText(list[position]);
			return layout;
		}

		@Override
		public int getCount() {
			return list.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		class Holder {
			public TextView name;
		}
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
	}
}
