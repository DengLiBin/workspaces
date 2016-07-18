package com.shopping.redboy.view;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.util.HttpClientUtil;
import com.shopping.redboy.util.JSONUtil;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.NetUtil;
import com.shopping.redboy.view.categoryDetail.ProductDetailView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.TimeFormatException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@ResID(id = R.layout.limit_buy_activity)
@Category(id = R.id.imgHome, title = "限时抢购", leftbutton = "返回", rightbutton = "")
public class LimitBuyView extends BaseView implements ButtonClickListener,
		OnItemClickListener {
	@ResID(id = R.id.productList)
	private ListView lv;
	// 封装了商品信息的集合
	private List<Product> list;

	private final static String URL = "/limitbuy";

	private List<Bitmap> bitmapList;

	private MyAdapter myAdapter;

	private List<TextView> tvList;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				System.out.println(list.size());
				System.out.println(tvList.size());
				for (int i = 0; i < list.size(); i++) {
					TextView tv = tvList.get(i);
					Product product = list.get(i);
					long time = product.getLeftTime();
					time -= 1000;
					product.setLeftTime(time);
					String leftTime = formatLeftTime(time);
					tv.setText(leftTime);
				}
				myAdapter.notifyDataSetChanged();
				handler.sendEmptyMessageDelayed(1, 1000);
				break;
			}
		};
	};

	private String formatLeftTime(long time) {
		time = time / 1000;
		StringBuffer result = new StringBuffer();
		int day = (int) (time / 86400);
		result.append(day).append("天");
		if (day > 0) {
			time = time - day * 86400;
		}
		int hour = (int) (time / 3600);
		result.append(hour).append("时");
		if (hour > 0) {
			time = time - hour * 3600;
		}
		int minute = (int) (time / 60);
		result.append(minute).append("分");
		if (minute > 0) {
			time = time - minute * 60;
		}
		result.append(time).append("秒");
		return result.toString();
	}

	public LimitBuyView(Context context) {
		super(context);
	}

	private class MyAdapter extends BaseAdapter {

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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			final Holder holder;
			final Product product = list.get(position);
			if (convertView != null) {
				view = convertView;
				holder = (Holder) view.getTag();
			} else {
				holder = new Holder();
				view = View.inflate(context, R.layout.limit_buy_item, null);

				holder.name = (TextView) view
						.findViewById(R.id.textClothesName);
				holder.price = (TextView) view
						.findViewById(R.id.tv_limit_buy_marketPrice);
				holder.limitPrice = (TextView) view
						.findViewById(R.id.textClothesPrice);
				holder.leftTime = (TextView) view
						.findViewById(R.id.textLeftTime);
				holder.pic = (ImageView) view.findViewById(R.id.imgLimitBuy);

				view.setTag(holder);
			}
			tvList.add(position, holder.leftTime);
			holder.name.setText(product.getName());
			holder.price.setText(product.getPrice() + "");
			holder.limitPrice.setText(product.getLimitPrice() + "");
			holder.leftTime.setText(formatLeftTime(product.getLeftTime()));
			// tvList.add(holder.leftTime);
			// holder.leftTime.setTag(position);
			holder.pic.setImageBitmap(bitmapList.get(position));
			return view;
		}
	}

	private class Holder {
		TextView name;
		TextView price;
		TextView limitPrice;
		TextView leftTime;
		ImageView pic;
	}

	@Override
	protected void init() {
		// tvList = new ArrayList<TextView>();
	}

	@Override
	public void onResume() {
		super.onResume();
		getData();
	}

	private void getData() {
		new MyAsynTask() {
			ProgressDialog pd = new ProgressDialog(context);

			@Override
			public void onPreExecute() {
				pd.setMessage("正在加载数据");
				pd.show();
				tvList = new ArrayList<TextView>();
			}

			@Override
			public void onPostExecute() {
				pd.dismiss();
				pd = null;
				if (list == null) {
					Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
				} else if (bitmapList == null) {
					Toast.makeText(context, "网络异常，无法加载图片", Toast.LENGTH_SHORT)
							.show();
				} else {
					// 设置适配器
					myAdapter = new MyAdapter();
					lv.setAdapter(myAdapter);
					handler.sendEmptyMessageDelayed(1, 1000);
				}
			}

			@Override
			public void doInBackground() {
				if (NetUtil.checkNet(context)) {
					HttpClientUtil clientUtil = new HttpClientUtil();
					String json = clientUtil.SendGet(URL, null);
					list = JSONUtil.getList_New(json, Product.class,
							"productlist");

					bitmapList = new ArrayList<Bitmap>();
					for (Product product : list) {
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
				}
			}
		}.execute();
	}

	@Override
	public void onPause() {
		super.onPause();
		// myAdapter = null;
		if (list != null) {

			list.removeAll(list);
		}
		if (bitmapList != null) {

			bitmapList.removeAll(bitmapList);
		}
		if (tvList != null) {

			tvList.removeAll(tvList);
		}
		if (handler != null) {
			handler.removeMessages(1);
		}
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
	}

	@Override
	protected void setListener() {
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Product product = list.get(position);

		String pic = product.getPic();
		String[] pics = { pic, pic };
		product.setPics(pics);

		UIManager manager = UIManager.getInstance();
		manager.getMap().put("product", product);
		UIManager.getInstance().changeView(ProductDetailView.class);
	}

}
