package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.HistoryProduct;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.dao.HistorySearchDao;
import com.itheima.redbaby.engine.impl.SearchEngineImpl;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.ui.manager.TitleManager;

public class SearchResult extends BaseView {

	private ListView dl_search_result_lv;

	private EditText editText;

	private MyAdapter adapter;

	private List<Product> listSearch;

	private String keyname;

	private EditText home_editText;

	public SearchResult(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadMiddleLayout() {
		// TODO Auto-generated method stub
		middleView = View.inflate(context, R.layout.dl_search_result, null);
	}

	@Override
	protected void findViewById() {
		dl_search_result_lv = (ListView) middleView
				.findViewById(R.id.dl_search_result_lv);
		editText = TitleManager.getinstance().getDl_search_title_ed();
		
		home_editText = TitleManager.getinstance().getDl_home_search_title_ed();
		
		/**
		 * editText文本改变监听
		 */
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				keyname = editText.getText().toString().trim();
				if(!TextUtils.isEmpty(keyname)){
					/**
					 * 网络异步处理
					 */
					new MyHttpTask<String>() {

						@Override
						protected Object doInBackground(String... params) {
							SearchEngineImpl engineImpl = new SearchEngineImpl();
							List<Product> listSearch = engineImpl
									.getGoodsMethod(params[0]);
							return listSearch;
						}

						@Override
						protected void onPostExecute(Object result) {
							changeAdapter((List<Product>) result);
							super.onPostExecute(result);
						}
					}.executeProxy(keyname);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		/**
		 * ListView点击事件
		 */
		dl_search_result_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println(position + "");
				/**
				 * 添加历史点击查询
				 */
				Product product = listSearch.get(position);
				String hoistryid = product.getId();
				String name = product.getName();
				addHistory(hoistryid, name);
				bundle.putString("id", hoistryid);
				MiddleManager.getInstance().changeView(GoodsDetailsView.class, bundle);
				home_editText.setText("");
			}
		});
		
		/**
		 * home_editText文本改变监听
		 */
		home_editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				keyname = home_editText.getText().toString().trim();
				if(!TextUtils.isEmpty(keyname)){
					/**
					 * 网络异步处理
					 */
					new MyHttpTask<String>() {

						@Override
						protected Object doInBackground(String... params) {
							SearchEngineImpl engineImpl = new SearchEngineImpl();
							List<Product> listSearch = engineImpl
									.getGoodsMethod(params[0]);
							if(listSearch==null) 
								return null;
							return listSearch;
						}

						@Override
						protected void onPostExecute(Object result) {
							changeAdapter((List<Product>) result);
							super.onPostExecute(result);
						}
					}.executeProxy(keyname);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	/**
	 * 当中间view开启的时候
	 */
	@Override
	/*
	 * public void onResume() { imple = new SearchEngineImpl();
	 * 
	 * productList = imple.getProductList(); int size = productList.size();
	 * System.out.println("size"); adapter = new MyAdapter();
	 * dl_search_result_lv.setAdapter(adapter); super.onResume(); }
	 */
	protected void setListener() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void processEngine() {
		listSearch = new ArrayList<Product>();
	}

	@Override
	protected int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * listview适配器
	 */

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listSearch.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				convertView = View.inflate(context, R.layout.dl_another_my_search,
						null);
				holder = new ViewHolder();
				convertView.setTag(holder);
				holder.tv = (TextView) convertView.findViewById(R.id.dl_search_result);
			}
			// holder.tv.setText(productList.get(position).getName());
			holder.tv.setText(listSearch.get(position).getName());
			return convertView;
		}

		class ViewHolder {
			TextView tv;
		}
	}

	private void changeAdapter(List<Product> result) {
		if (result != null) {
			listSearch = (List) result;
		}
		if(result.size()==0){
			Toast.makeText(context, "没有该商品", 0).show();
			
		}
		adapter = new MyAdapter();
		dl_search_result_lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	/**
	 * 向数据库添加搜索历史记录
	 * 
	 * @param name
	 * @param hoistryid
	 */
	private void addHistory(String hoistryid, String name) {
		// TODO Auto-generated method stub
		HistorySearchDao dao = new HistorySearchDao(getContext());

		List<HistoryProduct> findAll = dao.findAll();
		/**
		 * 不能添加重复的数据
		 */
		int count = 0;
		for (int i = 0; i < findAll.size(); i++) {
			if(name.equals(findAll.get(i).getName())){
				count++;
			}
		}
		if(count==0){
			 dao.add(hoistryid, name);	
		}
	}
}
