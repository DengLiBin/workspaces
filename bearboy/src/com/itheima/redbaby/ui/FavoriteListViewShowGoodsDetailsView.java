package com.itheima.redbaby.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.impl.FavoriteInfoEngineImpl;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.ui.manager.ShoppingCart;

/**
 * 展示收藏夹
 * 
 * @author Crist
 * 
 */
public class FavoriteListViewShowGoodsDetailsView extends BaseView {

	private ListView favoriteList;

	private Map<String, List<Product>> favoriteInfo;

	private List<Product> producList;

	private List<Product> pros;

	public FavoriteListViewShowGoodsDetailsView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context,
				R.layout.dd_favorite_listview_showgoods_detailsview, null);

	}

	@Override
	protected void findViewById() {
		favoriteList = (ListView) middleView
				.findViewById(R.id.dd_favorite_listview_showgoods_detailsview);
	}

	@Override
	protected void setListener() {
		favoriteList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle bundle1 =  new Bundle();
				String productId = producList.get(position).getId();
				bundle1.putString("id",productId );
				MiddleManager.getInstance().changeView(GoodsDetailsView.class, bundle1);
				
			}
		});
	}

	@Override
	protected void processEngine() {
		/*new MyHttpTask<Map>() {

			@Override
			protected Object doInBackground(Map... params) {
				FavoriteInfoEngineImpl engineImpl = new FavoriteInfoEngineImpl();
				favoriteInfo = engineImpl.getFavoriteInfo();
				return favoriteInfo;
			}

			@Override
			protected void onPostExecute(Object result) {
				if (result != null) {
					favoriteInfo = (Map<String, List<Product>>) result;
					Set<String> keySet = favoriteInfo.keySet();
					for (String string : keySet) {
						producList = favoriteInfo.get(string);
					}
					MyFavoriteAdapter favoriteAdapter = new MyFavoriteAdapter();
					favoriteList.setAdapter(favoriteAdapter);
					// Map<HashMap<String,Integer>,HashMap<String,Integer>> map
					// = new
					// HashMap<HashMap<String,Integer>,HashMap<String,Integer>>();
				}
				super.onPostExecute(result);
			}
		};*/
	}
	@Override
	public void onResume() {
		pros = ShoppingCart.getInstance().getPros();
		System.out.println(pros);
		MyFavoriteAdapter adapter = new MyFavoriteAdapter();
		favoriteList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		super.onResume();
	}

	@Override
	protected int getID() {
		return ConstantValue.FAVORITE;
	}

	private class MyFavoriteAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return pros.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Hodler hodler = null;
			if (convertView != null) {
				hodler = (Hodler) convertView.getTag();
			} else {
				convertView = View.inflate(getContext(), R.layout.dl_goods_entry_listview_item, null);
				hodler = new Hodler();
				hodler.dl_goods_listview_buynum = (TextView) convertView
						.findViewById(R.id.dl_goods_listview_buynum);
				hodler.dl_goods_listview_img = (ImageView) convertView
						.findViewById(R.id.dl_goods_listview_img);
				hodler.dl_goods_listview_money = (TextView) convertView
						.findViewById(R.id.dl_goods_listview_money);
				hodler.dl_goods_listview_title = (TextView) convertView
						.findViewById(R.id.dl_goods_listview_title);
				convertView.setTag(hodler);
				}
			Product product = pros.get(position);
			hodler.dl_goods_listview_buynum.setText("已卖出"
					+ product.getBuyCount() + "个");
			hodler.dl_goods_listview_money.setText("$" + product.getPrice());
			hodler.dl_goods_listview_title.setText(product.getName());
			imageLoader.displayImage(ConstantValue.URI + product.getPic(),
					hodler.dl_goods_listview_img, options);
			return convertView;

		}

		private class Hodler {
			ImageView dl_goods_listview_img;
			TextView dl_goods_listview_title;
			TextView dl_goods_listview_buynum;
			TextView dl_goods_listview_money;
		}

	}
}
