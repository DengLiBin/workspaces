package com.itheima.redbaby.ui;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
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
import com.itheima.redbaby.ui.manager.TitleManager.OnSearchImageClickListener;

public class Search extends BaseView {

	protected static final String TAG = "Search";
	
	private ExpandableListView dl_my_search_lv;
	private MyAdapter adapter;
	
	/**
	 * 热门搜索历史
	 */
	private String[] hotHistory=new String[]{};
	private EditText editText;
	private ImageView dl_search_my_title_img;
	private HistorySearchDao dao;
	
	private List<HistoryProduct> findAll;

	private ImageView dl_my_search_iv_delete;
	public Search(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadMiddleLayout() {
		// TODO Auto-generated method stub
		middleView = View.inflate(context, R.layout.dl_my_search, null);
		
		dao = new HistorySearchDao(getContext());
		
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		findAll = dao.findAll();
		adapter.notifyDataSetChanged();
		super.onResume();
	}
	
	@Override
	protected void findViewById() {
		editText = TitleManager.getinstance().getDl_search_title_ed();
		dl_search_my_title_img = TitleManager.getinstance().getDl_search_my_title_img();
		dl_my_search_lv = (ExpandableListView) middleView.findViewById(R.id.dl_my_search_lv);
		dl_my_search_iv_delete = (ImageView) middleView.findViewById(R.id.dl_my_search_iv_delete);
		
		adapter = new MyAdapter();
		
		dl_my_search_lv.setAdapter(adapter);
		
//		dl_my_search_lv_fl.setAdapter(adapterlv);
		/**
		 * ExpandableListView子条目点击事件
		 */
		dl_my_search_lv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				
				Log.i(TAG, childPosition+"");
				HistoryProduct product = findAll.get(childPosition);
				String hoistryid = product.getId();
				bundle.putString("id", hoistryid);
				MiddleManager.getInstance().changeView(GoodsDetailsView.class, bundle);
				return false;
			}
		});
		/**
		 * ExpandableListView长点击事件
		 */
		dl_my_search_lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				System.out.println("long"+position);
				
				return false;
			}
		});
		
//		/**
//		 * edittext点击事件
//		 * 
//		 */
//		editText.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
////				System.out.println("edittext");
				
//				
//			}
//		});
		/**
		 * 搜索图片点击事件
		 */
		dl_search_my_title_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String trim = editText.getText().toString().trim();
				if(!TextUtils.isEmpty(trim)){
					System.out.println(trim);
					MiddleManager.getInstance().changeView(SearchResult.class, null);
				}
				editText.setText("");
				
			}
		});	
		/**
		 * editText文本框文字改变的监听事件
		 * 
		 */
		editText.addTextChangedListener(new TextWatcher() {
			
			private SearchEngineImpl impl;

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				//跳转到搜索结果界面
//				String ed_product = editText.getText().toString().trim();
//				System.out.println(ed_product);
//				impl = new SearchEngineImpl();
//				List<Product> goodsTabulationList = impl.getGoodsTabulationList(ed_product);
//				int size = goodsTabulationList.size();
//				if(size==0){
//					Toast.makeText(context, "没有此商品", 0).show();
//				}else{
					MiddleManager.getInstance().changeView(SearchResult.class, null);
//				}
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
		
		new MyHttpTask<String>() {

			@Override
			protected Object doInBackground(String... params) {
				// TODO Auto-generated method stub
				return null;
			}
			
			protected void onPostExecute(Object result) {
 			};
		};
		/**
		 * 清除历史数据
		 */
		dl_my_search_iv_delete.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				AlertDialog.Builder builder = new Builder(
//				CallSmsSafeActivity.this);
//		builder.setTitle("警告");
//		builder.setMessage("确定要删除这条记录么？");
//		builder.setPositiveButton("确定",
//				new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog,
//							int which) {
//						// 删除数据库的内容
//						dao.delete(infos.get(position).getNumber());
//						// 更新界面。
//						infos.remove(position);
//						// 通知listview数据适配器更新
//						adapter.notifyDataSetChanged();
//					}
//				});
//		builder.setNegativeButton("取消", null);
//		builder.show();
				
				dao = new HistorySearchDao(getContext());
				
				dao.deleteAll();
				//跟新界面
				findAll.removeAll(findAll);
				//通知listview数据适配器更新
				adapter.notifyDataSetChanged();
				
				Toast.makeText(context, "清除历史查询", 0).show();
			}
		});
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void processEngine() {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	class MyAdapter extends BaseExpandableListAdapter{

		/**
		 * 组视图的显示文字
		 */
//		private String[] Types = new String[] {"历史搜索" };

		/**
		 * 子条目id
		 */
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		/**
		 * 子条目的view窗体
		 */
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				convertView = View.inflate(context,
						R.layout.dl_item_my_search, null);
				holder = new ViewHolder();
				convertView.setTag(holder);
				holder.tv = (TextView) convertView.findViewById(R.id.dl_search_iv_dec);
				
			}
			holder.tv.setText(getChild(groupPosition,childPosition).toString());
			
//			holder.iv_delete.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					AlertDialog.Builder builder = new Builder(
//							CallSmsSafeActivity.this);
//					builder.setTitle("警告");
//					builder.setMessage("确定要删除这条记录么？");
//					builder.setPositiveButton("确定",
//							new DialogInterface.OnClickListener() {
//								@Override
//								public void onClick(DialogInterface dialog,
//										int which) {
//									// 删除数据库的内容
//									dao.delete(infos.get(position).getNumber());
//									// 更新界面。
//									infos.remove(position);
//									// 通知listview数据适配器更新
//									adapter.notifyDataSetChanged();
//								}
//							});
//					builder.setNegativeButton("取消", null);
//					builder.show();
//				}
//			});
			
			return convertView;
			
		}

		/**
		 * 子条目的长度
		 */
		public int getChildrenCount(int groupPosition) {
			if(groupPosition<=1)
	    		return findAll.size();
	    	return 0;
		}

		/**
		 * 组的位置
		 */
		public Object getGroup(int groupPosition) {
			return 0;
		}

		/**
		 * 组的长度
		 */
		public int getGroupCount() {
			return 1;
		}

		/**
		 * 组的id
		 */
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		/**
		 * 组的View
		 */
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			View v = View.inflate(context, R.layout.dl_item_search_host, null);
			TextView tv = (TextView) v.findViewById(R.id.dl_iec_search_tv);
			tv.setText("历史搜索");
			return v;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		class ViewHolder {
			TextView tv;
			ImageView iv;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
//			if(groupPosition==0)
//				return generals[1][generals[1].length-childPosition-1];
			return findAll.get(childPosition);
		}
		
	}
	
	
	/**
	 * 获得本地搜索历史
	 * 
	 * @return
	 */
	private void getNativeHistory() {
		
	}
	
}
