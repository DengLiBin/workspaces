package com.shopping.redboy.view;

import java.util.List;
import java.util.Map;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Brand;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.engine.Impl.TopicAndBrandEngineImpl;
import com.shopping.redboy.util.BitmapUtil;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.view.categoryDetail.ProductListView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

@ResID(id = R.layout.brand_activity)
@Category(id = R.id.imgHome, title = "推荐品牌", leftbutton = "返回", rightbutton = "")
public class BrandView extends BaseView implements ButtonClickListener,
		OnChildClickListener {
	@ResID(id = R.id.listBrandInfo)
	private ExpandableListView listBrandInfo;

	private Map<String, List<Brand>> map;

	private String[] keys;

	private TopicAndBrandEngineImpl engine;

	public BrandView(Context context) {
		super(context);
	}

	private class MyAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return map.get(keys[groupPosition]).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ImageView image;
			if (convertView == null) {
				image = new ImageView(context);
				String key = keys[groupPosition];
				List<Brand> list = map.get(key);
				Brand brand = list.get(childPosition);
				Bitmap bitmap = brand.getBitmap();
				image.setImageBitmap(bitmap);
			} else {
				image = (ImageView) convertView;
			}
			return image;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return map.get(keys[groupPosition]).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return map.get(keys[groupPosition]);
		}

		@Override
		public int getGroupCount() {
			return map.keySet().size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {

			TextView tv;
			if (convertView == null) {
				tv = new TextView(context);
				tv.setBackgroundColor(Color.GREEN);
				tv.setGravity(Gravity.CENTER);
				String key = keys[groupPosition];
				tv.setText(key);
				tv.setTextSize(15);
			} else {
				tv = (TextView) convertView;
			}
			return tv;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

	}

	@Override
	protected void init() {
	}

	@Override
	public void onResume() {
		super.onResume();
		getDate();
	}

	private void getDate() {
		new MyAsynTask() {
			ProgressDialog pd;

			@Override
			public void onPreExecute() {
				pd = new ProgressDialog(context);
				pd.setMessage("正在载入数据");
				pd.show();
				engine = new TopicAndBrandEngineImpl(context);
			}

			@Override
			public void onPostExecute() {
				pd.dismiss();
				pd = null;
				if (map == null) {
					Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
				} else {
					MyAdapter myAdapter = new MyAdapter();
					listBrandInfo.setAdapter(myAdapter);
				}
			}

			@Override
			public void doInBackground() {
				map = engine.getBrandMap();
				if (map != null) {
					Object[] array = map.keySet().toArray();
					keys = new String[array.length];
					for (int i = 0; i < array.length; i++) {
						keys[i] = (String) array[i];
					}
					for (String key : keys) {
						List<Brand> list = map.get(key);
						for (Brand item : list) {
							try {
								Bitmap bm = BitmapUtil.getBitMapFormURL(item
										.getPic());
								item.setBitmap(bm);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.execute();

	}

	@Override
	public void onPause() {
		super.onPause();
		// map.clear();
		// map = null;
		// keys = null;
		// engine = null;
	}

	@Override
	protected void setListener() {
		listBrandInfo.setOnChildClickListener(this);
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
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
				List<Product> brandItemList = new TopicAndBrandEngineImpl(
						context).getBrandItemList();
				Map<String, Object> map = UIManager.getInstance().getMap();
				map.put("productList", brandItemList);
				map.put("title", "品牌商品列表");
			}
		}.execute();
		return true;
	}

}
