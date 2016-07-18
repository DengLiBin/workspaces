package com.shopping.redboy.view;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.engine.SearchEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.myBaseAdapterTest;
import com.shopping.redboy.view.categoryDetail.ProductListView;

@ResID(id = R.layout.search_activity)
@Category(id = R.id.imgSearch, title = "搜索", leftbutton = "", rightbutton = "")
public class SearchView extends BaseView {
	@ResID(id=R.id.keyWordEdit)
	private EditText keyWordEdit;
	@ResID(id=R.id.ok)
	private ImageView ok;
	@ResID(id=R.id.hotWordsLv)
	private ListView hotWordsLv;
	
	private List<String> list;
	private myBaseAdapterTest<String, ViewHolder> adapter;

	public SearchView(Context context) {
		super(context);
	}

	@Override
	protected void init() {}
	
	@Override
	public void onResume() {
		new MyAsynTask() {
			@Override
			public void onPreExecute() {}
			@Override
			public void onPostExecute() {
				adapter = new MyAdapter(list, context, ViewHolder.class);
				hotWordsLv.setAdapter(adapter);
			}
			@Override
			public void doInBackground() {
				SearchEngine engine = BeanFactory.getImpl(SearchEngine.class);
				list = engine.getKeywords();
			}
		}.execute();
	}

	@Override
	protected void setListener() {
		hotWordsLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				jumpProductsDetail();
			}

		});
	}
	private List<Product> products;
	private void jumpProductsDetail() {
		new MyAsynTask() {
			
			@Override
			public void onPreExecute() {
			}
			
			@Override
			public void onPostExecute() {
				UIManager.getInstance().getMap().put("productList", products);
				UIManager.getInstance().getMap().put("title", "搜索");
				UIManager.getInstance().changeView(ProductListView.class);
			}
			
			@Override
			public void doInBackground() {
				SearchEngine engine = BeanFactory.getImpl(SearchEngine.class);
				products = engine.getProducts();	
				for(Product item : products){
					System.out.println(item.toString());
				}
			}
		}.execute();
	}
	
	@ResID(id=R.layout.test)
	public static class ViewHolder{
		TextView tv_show;
	}
	
	private class MyAdapter extends myBaseAdapterTest<String, ViewHolder>{

		public MyAdapter(List<String> list, Context context,
				Class<ViewHolder> clazz) {
			super(list, context, clazz);
		}

		@Override
		public void initholder(ViewHolder holder, String listitem) {
			holder.tv_show.setText(listitem);
		}
		
	}

}
