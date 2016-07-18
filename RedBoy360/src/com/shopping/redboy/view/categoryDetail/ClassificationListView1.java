package com.shopping.redboy.view.categoryDetail;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.ProductCategory;
import com.shopping.redboy.engine.CategoryDetailEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.view.BaseView;

/**
 * 一级分类界面
 * @author hanli
 *
 */
@ResID(id=R.layout.category_activity)
@Category(id=R.id.imgClassify,title="分类浏览",leftbutton="",rightbutton="")
public class ClassificationListView1 extends BaseView{
	@ResID(id=R.id.categoryList)
	private ListView lv_category;
	
	@ResID(id=R.id.categoryEmptyListTv)
	private TextView tv_category;

	/**
	 * 本界面中使用的分类信息
	 */
	private static List<ProductCategory> list = new ArrayList<ProductCategory>();
	/**
	 * 全部的分类信息
	 */
	private List<ProductCategory> categoryInfos;
	
	private MyCategoryAdapter adapter ;
	
	public ClassificationListView1(Context context) {
		super(context);
	}

	@Override
	protected void setListener() {
		/**
		 * 设置中间ListView的条目点击事件
		 */
		lv_category.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ProductCategory category = list.get(position);
				UIManager.getInstance().getMap().put("parentCategory", category);
				UIManager.getInstance().getMap().put("categoryInfos", categoryInfos);
				UIManager.getInstance().changeView(ClassificationListView2.class);
			}
		});
	}

	//分类listView的适配器
	private class MyCategoryAdapter extends BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			RelativeLayout layout = null;
			Holder holder = null;
			if(convertView == null){
				layout = (RelativeLayout) View.inflate(context, R.layout.category_item,null );
				holder = new Holder();
				holder.image = (SmartImageView) layout.findViewById(R.id.imgIcon);
				holder.content = (TextView) layout.findViewById(R.id.textContent);
				holder.description = (TextView) layout.findViewById(R.id.item_describe);
				layout.setTag(holder);
			}else{
				layout = (RelativeLayout) convertView;
				holder = (Holder) layout.getTag();
			}
			holder.image.setImageUrl(list.get(position).getPic());
			holder.content.setText(list.get(position).getName());
			holder.description.setText(list.get(position).getTag());
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
			public SmartImageView image;
			public TextView content ;
			public TextView description;
		}
	}
	
	@Override
	public void onResume() {
		//判断map中是否有firstBlood这个键值对，没有的话说明是从主页第一次进入
		//从服务器拿数据，如果有，则说明是从次级页面返回，直接展现原来界面
		if(UIManager.getInstance().getMap().get("firstBlood")!=null){
			UIManager.getInstance().getMap().clear();
			return ;
		}
		new MyAsynTask() {
			@Override
			public void onPreExecute() {
				lv_category.setVisibility(View.INVISIBLE);
				tv_category.setVisibility(View.VISIBLE);
			}
			@Override
			public void onPostExecute() {
				adapter.notifyDataSetChanged();
				lv_category.setVisibility(View.VISIBLE);
				tv_category.setVisibility(View.INVISIBLE);
			}
			@Override
			public void doInBackground() {
				CategoryDetailEngine engine = BeanFactory.getImpl(CategoryDetailEngine.class);
				categoryInfos = engine.getCategoryInfo();
				list.clear();
				for(ProductCategory categoryInfo : categoryInfos){
					if(categoryInfo.getParentId()==0){
						list.add(categoryInfo);
					}
				}
			}
		}.execute();
			
		UIManager.getInstance().getMap().clear();
		super.onResume();
	}

	@Override
	protected void init() {
		adapter = new MyCategoryAdapter();
		lv_category.setAdapter(adapter);
	}
}
