package com.shopping.redboy.view;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.util.HttpClientUtil;
import com.shopping.redboy.util.JSONUtil;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.NetUtil;
import com.shopping.redboy.view.categoryDetail.ProductDetailView;

@ResID(id = R.layout.my_favorite_activity)
@Category(id = R.id.imgHome, title = "收藏夹", leftbutton = "账户中心", rightbutton = "清空")
public class MyFavoriteView extends BaseView implements ButtonClickListener {
	protected static final String URL = "/productlist";
	@ResID(id = R.id.myfavorite_product_list)
	private ListView myfavorite_product_list;
	@ResID(id = R.id.ll_loading)
	private LinearLayout ll_loading;// 加载
	@ResID(id = R.id.ll_empty)
	private LinearLayout ll_empty;// 收藏夹是否为空

	private MyFavoriteViewAdapter adapter;
	private List<Product> products; // 商品列表
	private int offset = 0;
	private int maxnumber = 20;
	private List<Bitmap> bitmapList;

	private boolean isLoading = false;

	public MyFavoriteView(Context context) {
		super(context);

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 0:
				Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	@Override
	protected void init() {
		new MyAsynTask() {

			@Override
			public void onPreExecute() {
			}

			@Override
			public void onPostExecute() {
				if (adapter == null) {
					adapter = new MyFavoriteViewAdapter();
					myfavorite_product_list.setAdapter(adapter);
				} else {
					adapter.notifyDataSetChanged();
				}
				ll_loading.setVisibility(View.INVISIBLE);
			}

			@Override
			public void doInBackground() {
				isLoading = true;
				ll_loading.setVisibility(View.VISIBLE);
				ll_empty.setVisibility(View.INVISIBLE);
				if (NetUtil.checkNet(context)) {
					HttpClientUtil clientUtil = new HttpClientUtil();
					String json = clientUtil.SendGet(URL, null);
					products = JSONUtil.getList_New(json, Product.class,
							"productlist");

					bitmapList = new ArrayList<Bitmap>();
					for (Product product : products) {
						try {
							URL url = new URL(product.getPic());
							HttpURLConnection conn = (HttpURLConnection) url
									.openConnection();
							int code = conn.getResponseCode();
							if (code == 200) {
								InputStream in = conn.getInputStream();
								Bitmap bm = BitmapFactory.decodeStream(in);
								bitmapList.add(bm);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} else {
					handler.sendEmptyMessage(0);
				}
			}
		}.execute();
	}

	/**
	 * 左边按钮
	 */
	public void onLeftButtonClicked() {
		UIManager.getInstance().changeView(AccountView.class);
	}

	/**
	 * 右边按钮
	 */
	public void onRightButtonClicked() {
		if (products != null) {
			products.clear();
			ll_empty.setVisibility(View.VISIBLE);
			adapter.notifyDataSetChanged();
		}

	}

	@Override
	protected void setListener() {
		/**
		 * 点击进入商品详情页
		 */
		myfavorite_product_list
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						UIManager.getInstance().changeView(
								ProductDetailView.class);
					}
				});
		myfavorite_product_list.setOnScrollListener(new OnScrollListener() {

			@Override
			// 当滚动的状态发生变化的时候。
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_IDLE:// 空闲状态
					// 判断当前listview滚动的位置
					// 获取最后一个可见条目在集合里面的位置。
					int lastposition = myfavorite_product_list
							.getLastVisiblePosition();

					// 集合里面有20个item 位置从0开始的 最后一个条目的位置 19
					if (lastposition == (products.size() - 1)) {
						Toast.makeText(context, "已经没有更多的数据了", 0).show();
						offset += maxnumber;
					}
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});
	}

	/**
	 * ListView的适配器
	 * 
	 * @author Fire
	 * 
	 */
	private class MyFavoriteViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return products.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			final ViewHolder holder;

			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				holder = new ViewHolder();
				view = View.inflate(context, R.layout.my_favorite_listitem,
						null);

				holder.iv_imag = (ImageView) view
						.findViewById(R.id.myfavorite_product_img);
				holder.tv_title = (TextView) view
						.findViewById(R.id.myfavorite_title_text);
				holder.tv_detprice = (TextView) view
						.findViewById(R.id.myfavorite_deleteprice_text);
				holder.tv_nostock = (TextView) view
						.findViewById(R.id.myfavorite_nostock_text);
				holder.tv_price = (TextView) view
						.findViewById(R.id.myfavorite_price_text);

				view.setTag(holder);
			}
			Product product = products.get(position);
			holder.iv_imag.setImageBitmap(bitmapList.get(position));
			holder.tv_title.setText(product.getName());
			holder.tv_price.setText("市场价￥"
					+ String.valueOf(product.getMarketprice()));
			holder.tv_detprice.setText("会员价￥"
					+ String.valueOf(product.getPrice()));
			holder.tv_nostock.setText("已有"
					+ (product.getComment_count() + 500 + position) + "人评价");
			return view;
		}
	}

	private class ViewHolder {
		ImageView iv_imag;
		TextView tv_detprice;
		TextView tv_nostock;
		TextView tv_price;
		TextView tv_title;
	}
}
