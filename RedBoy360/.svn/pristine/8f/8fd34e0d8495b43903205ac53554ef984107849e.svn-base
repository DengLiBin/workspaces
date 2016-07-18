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
import com.shopping.redboy.domain.ProductSift;
import com.shopping.redboy.view.BaseView;

@ResID(id = R.layout.sift_list1)
@Category(id = R.id.imgClassify, title = "筛选", leftbutton = "返回", rightbutton = "确定")
public class SiftView1 extends BaseView implements ButtonClickListener {

	@ResID(id = R.id.categoryList)
	private ListView categoryList;

	@ResID(id = R.id.categoryEmptyListTv)
	private TextView categoryEmptyListTv;

	private String[] list ;
	private String[] sift;

	private MyAdapter adapter;

	public SiftView1(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		 list = new String[] { "全部", "全部", "全部", "全部" };
		 sift = new String[] { "品牌", "功能", "价格", "库存" };
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
				map.put("siftCategory", position);
				UIManager.getInstance().changeView(SiftView2.class);
			}
		});
	}

	@Override
	public void onResume() {
		categoryEmptyListTv.setVisibility(View.VISIBLE);
		Map<String, Object> map = UIManager.getInstance().getMap();
		if (map.get("position") != null) {
			int position = (Integer) map.get("position");
			String value = (String) map.get("value");
			list[position] = value;
			adapter.notifyDataSetChanged();
			map.clear();
		}
		categoryEmptyListTv.setVisibility(View.INVISIBLE);
		super.onResume();
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
		ProductSift productSift = new ProductSift();
		productSift.setBrand(list[0]);
		productSift.setPrice(list[2]);
		productSift.setFunction(list[1]);
		productSift.setNumber(list[3]);
		UIManager.getInstance().getMap().put("productSift", productSift);
		UIManager.getInstance().goback();
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
			holder.name.setText(sift[position] + "：" + list[position]);
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

}
