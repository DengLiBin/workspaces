package com.shopping.redboy.view.categoryDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.ProductCategory;
import com.shopping.redboy.view.BaseView;
import com.shopping.redboy.view.categoryDetail.ClassificationListView2.MyAdapter;
import com.shopping.redboy.view.categoryDetail.ClassificationListView2.MyAdapter.Holder;

/**
 * 继承了二级分类界面，修改了ListView的条目点击事件
 */
@ResID(id = R.layout.category_child_activity3)
@Category(id = R.id.imgClassify, title = "三级分类列表", leftbutton = "返回", rightbutton = "")
public class ClassificationListView3 extends BaseView implements ButtonClickListener{
	/**
	 * 全部的分类信息
	 */
	protected List<ProductCategory> categoryInfos;
	/**
	 * 本界面中使用的分类信息
	 */
	protected static List<ProductCategory> list = new ArrayList<ProductCategory>();

	@ResID(id = R.id.categoryList)
	protected ListView lv_category;

	@ResID(id = R.id.categoryEmptyListTv)
	protected TextView tv_category;

	protected MyAdapter adapter;

	public ClassificationListView3(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		adapter = new MyAdapter();
		lv_category.setAdapter(adapter);

	}
	@Override
	protected void setListener() {
		lv_category.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map<String,Object> map = UIManager.getInstance().getMap();
				ProductCategory productCategory = list.get(position);
				map.put("category", productCategory);
				UIManager.getInstance().changeView(ProductListView.class);
			}
		});
	}
	private ProductCategory parentCategory;
	@Override
	public void onResume() {
		System.out.println("进入三级分类界面");
		Map map = UIManager.getInstance().getMap();
		//判断是否有"categoryInfos"，"parentCategory"两个键值对。
		//有则是从一级分类列表进入，从map中拿出数据展示
		//没有则是从次级分类返回，展示原来数据
		if(map.get("categoryInfos") != null && map.get("parentCategory")!=null){
			tv_category.setVisibility(View.VISIBLE);
			categoryInfos = (List<ProductCategory>) map.get("categoryInfos");
			parentCategory = (ProductCategory)map.get("parentCategory");
			list = new ArrayList<ProductCategory>();
			for (ProductCategory categoryInfo : categoryInfos) {
				if (categoryInfo.getParentId() == parentCategory.getId())
					list.add(categoryInfo);
			}
			adapter.notifyDataSetChanged();
		}
		tv_category.setVisibility(View.INVISIBLE);
		TitleManager.getInstance().getTitle().setText(parentCategory.getName());
		map.clear();
		super.onResume();
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().getMap().put("firstBlood", false);
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
	}

	/**
	 * 中间listView的适配器
	 * @author hanli
	 *
	 */
	protected class MyAdapter extends BaseAdapter {

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
			holder.name.setText(list.get(position).getName());
			return layout;
		}

		@Override
		public int getCount() {
			return list.size();
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
