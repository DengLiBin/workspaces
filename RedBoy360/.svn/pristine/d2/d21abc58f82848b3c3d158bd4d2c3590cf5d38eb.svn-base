package com.shopping.redboy.view;

import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.domain.Topic;
import com.shopping.redboy.engine.Impl.TopicAndBrandEngineImpl;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.view.categoryDetail.ProductListView;

/**
 * 促销快报页面
 * 
 * @author Fire
 * 
 */
@ResID(id = R.layout.prom_bulletin_activity)
@Category(id = R.id.imgHome, title = "促销快报", leftbutton = "返回", rightbutton = "")
public class PromotionView extends BaseView implements ButtonClickListener,
		OnItemClickListener {
	protected static final String TAG = "PromotionView";
	@ResID(id = R.id.promBulldtinLv)
	private ListView lv;
	// 存放促销信息的集合
	private List<Topic> list;
	// 发送的url
	private final static String URL = "/topic";
	// ListView 适配器
	private MyAdapter myAdapter;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	public PromotionView(Context context) {
		super(context);
	}

	@Override
	public void onResume() {
		super.onResume();
		getData();
	}

	@Override
	protected void init() {
	}

	private void getData() {
		new MyAsynTask() {
			ProgressDialog pd = new ProgressDialog(context);

			@Override
			public void onPreExecute() {
				pd.setMessage("正在加载数据");
				pd.show();
			}

			@Override
			public void onPostExecute() {
				pd.dismiss();
				pd = null;
				if (list == null) {
					Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
					return;
				}
				// 设置适配器
				myAdapter = new MyAdapter();
				lv.setAdapter(myAdapter);
			}

			@Override
			public void doInBackground() {
				list = new TopicAndBrandEngineImpl(context).getPromotionList();
			}
		}.execute();
	}

	/**
	 * ListView的适配器
	 * 
	 * @author Fire
	 * 
	 */
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
			final Topic topic = list.get(position);
			if (convertView != null) {
				view = convertView;
				holder = (Holder) view.getTag();
			} else {
				holder = new Holder();
				view = View.inflate(context, R.layout.prom_bulletin_item, null);
				holder.tv = (TextView) view.findViewById(R.id.textContent);
				holder.iv = (ImageView) view.findViewById(R.id.imgIcon);
				view.setTag(holder);
			}
			holder.tv.setText(topic.getName());
			holder.iv.setImageBitmap(topic.getBitmap());

			return view;
		}
	}

	private class Holder {
		TextView tv;
		ImageView iv;
	}

	@Override
	public void onPause() {
		super.onPause();
//		myAdapter = null;
//		list = null;
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
		new MyAsynTask() {

			@Override
			public void onPreExecute() {
			}

			@Override
			public void onPostExecute() {
				UIManager.getInstance().changeView(ProductListView.class);
			}

			@Override
			public void doInBackground() {
				List<Product> topicItemList = new TopicAndBrandEngineImpl(
						context).getTopicItemList();
				Map<String, Object> map = UIManager.getInstance().getMap();
				map.put("productList", topicItemList);
				map.put("title", "促销商品列表");
			}
		}.execute();

	}

}
