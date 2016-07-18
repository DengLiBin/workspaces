package com.shopping.redboy.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.engine.SearchEngine;
import com.shopping.redboy.engine.Impl.TopicAndBrandEngineImpl;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.PromptManager;
import com.shopping.redboy.view.categoryDetail.ProductDetailView;
import com.shopping.redboy.view.categoryDetail.ProductListView;

@ResID(id = R.layout.home_activity)
@Category(id = R.id.imgHome, title = "红孩子", leftbutton = "", rightbutton = "")
public class HomeView extends BaseView {
	@ResID(id = R.id.editSearchInfo)
	private EditText editSearchInfo;
	@ResID(id = R.id.ok)
	private ImageView ok;
	@ResID(id = R.id.viewpager)
	private static ViewPager viewpager;
	@ResID(id = R.id.ll_points)
	private LinearLayout ll_points;
	@ResID(id = R.id.custonInfoListView)
	private ListView custonInfoListView;

	private PagerAdapter adapter;
	private List<View> list;
	private int currentposition = 0;
	private static boolean isLooping = true;
	private BaseAdapter listadapter;

	private static int[] resID = new int[] { R.drawable.i1, R.drawable.i2,
			R.drawable.i3, R.drawable.i4, R.drawable.i5 };
	private static int[] listitemID = new int[] { R.drawable.home_classify_01,
			R.drawable.home_classify_02, R.drawable.home_classify_03,
			R.drawable.home_classify_04, R.drawable.home_classify_05 };

	private static String[] listitemStr = new String[] { "限时抢购", "促销快报",
			"新品上架", "热门单品", "推荐品牌" };
	private static Class[] clazzRes = { LimitBuyView.class,
			PromotionView.class, FirstView.class, FirstView.class,
			BrandView.class };

	private static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
			if (isLooping) {
				handler.sendEmptyMessageDelayed(0, 2000);
			}
		}
	};

	public HomeView(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		list = new ArrayList<View>();
		for (int i = 0; i < 5; i++) {
			ImageView view = new ImageView(context);
			view.setBackgroundResource(resID[i]);
			view.setTag(i);
			list.add(view);

			View point = new View(context);
			point.setEnabled(false);
			LayoutParams layoutParams = new LayoutParams(10, 10);
			layoutParams.leftMargin = 5;
			point.setBackgroundResource(R.drawable.point_selector);
			point.setLayoutParams(layoutParams);
			ll_points.addView(point);
		}
		ll_points.getChildAt(currentposition).setEnabled(true);

		listadapter = new myAdapter2();
		custonInfoListView.setAdapter(listadapter);
	}

	@Override
	public void onResume() {
		adapter = new myAdapter();
		viewpager.setAdapter(adapter);
		viewpager.setCurrentItem(500);
	}

	@Override
	public void onPause() {
		viewpager.removeAllViews();
	}

	@Override
	protected void setListener() {
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				changeTextAndPoint(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		viewpager.setOnTouchListener(new OnTouchListener() {
			private long starttime;
			private long endtime;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					starttime = SystemClock.uptimeMillis();
					isLooping = false;
					break;
				case MotionEvent.ACTION_UP:
					endtime = SystemClock.uptimeMillis();
					if (endtime - starttime < 500) {
						handler.removeMessages(0);
						UIManager.getInstance().changeView(
								ProductDetailView.class);

					} else {
						isLooping = true;
						handler.sendEmptyMessageDelayed(0, 2000);
					}
					break;
				}
				return false;
			}
		});
		custonInfoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 2:
					new MyAsynTask() {

						@Override
						public void onPreExecute() {
						}

						@Override
						public void onPostExecute() {
							UIManager.getInstance().changeView(
									ProductListView.class);
						}

						@Override
						public void doInBackground() {
							List<Product> hotItemList = new TopicAndBrandEngineImpl(
									context).getNewItemList();
							Map<String, Object> map = UIManager.getInstance()
									.getMap();
							map.put("productList", hotItemList);
							map.put("title", "新品上架");
						}
					}.execute();
					break;
				case 3:
					new MyAsynTask() {

						@Override
						public void onPreExecute() {
						}

						@Override
						public void onPostExecute() {
							UIManager.getInstance().changeView(
									ProductListView.class);
						}

						@Override
						public void doInBackground() {
							List<Product> hotItemList = new TopicAndBrandEngineImpl(
									context).getHotItemList();
							Map<String, Object> map = UIManager.getInstance()
									.getMap();
							map.put("productList", hotItemList);
							map.put("title", "热门单品");
						}
					}.execute();
					break;

				default:
					UIManager.getInstance().changeView(clazzRes[position]);
					break;
				}
			}
		});
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String value = editSearchInfo.getText().toString().trim();
				if (TextUtils.isEmpty(value)) {
					PromptManager.showToast(context, "请输入要查询的内容");
				} else {
					UIManager.getInstance().getMap().put("value", value);
					UIManager.getInstance().changeView(clazzRes[1]);
				}
			}
		});
		handler.sendEmptyMessageDelayed(0, 2000);
	}

	private void changeTextAndPoint(int position) {
		ll_points.getChildAt(currentposition).setEnabled(false);
		ll_points.getChildAt(position % 5).setEnabled(true);
		currentposition = position % 5;
	}

	private class myAdapter2 extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(context, R.layout.home_item, null);
				holder = new ViewHolder();
				holder.textContent = (TextView) convertView
						.findViewById(R.id.textContent);
				holder.imgIcon = (ImageView) convertView
						.findViewById(R.id.imgIcon);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.textContent.setText(listitemStr[position]);
			holder.imgIcon.setBackgroundResource(listitemID[position]);
			return convertView;
		}

		private class ViewHolder {
			TextView textContent;
			ImageView imgIcon;
		}

	}

	private class myAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return 1000;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = list.get(position % 5);
			container.removeView(view);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = list.get(position % 5);
			container.addView(view);
			return view;
		}

	}

}
