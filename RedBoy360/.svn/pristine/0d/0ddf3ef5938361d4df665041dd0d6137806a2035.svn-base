package com.shopping.redboy.view.categoryDetail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.domain.ProductCategory;
import com.shopping.redboy.domain.ProductProperties;
import com.shopping.redboy.domain.ProductSift;
import com.shopping.redboy.engine.CategoryDetailEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.view.BaseView;

@ResID(id = R.layout.product_list_activity)
@Category(id = R.id.imgClassify, title = "商品列表", leftbutton = "返回", rightbutton = "筛选")
public class ProductListView extends BaseView implements ButtonClickListener {
	@ResID(id = R.id.textRankSale)
	private TextView textRankSale;

	@ResID(id = R.id.textRankPrice)
	private TextView textRankPrice;

	@ResID(id = R.id.textRankGood)
	private TextView textRankGood;

	@ResID(id = R.id.textRankTime)
	private TextView textRankTime;

	@ResID(id = R.id.productViewPager)
	private ViewPager productViewPager;

	@ResID(id = R.id.categoryEmptyListTv)
	private TextView categoryEmptyListTv;

	private MyAdapter adapter;

	private RelativeLayout rl;
	private List<Product> productList;

	private ProductSift productSift;

	/* ================方法区===================== */
	public ProductListView(Context context) {
		super(context);
		System.out.println("进入商品列表界面");
	}

	private boolean saleSortDesc = true;
	private boolean priceSortDesc = true;
	private boolean goodSortDesc = true;
	private boolean timeSortDesc = true;

	private static int[] listViewId = new int[] { R.id.lv_product_list1,
			R.id.lv_product_list2, R.id.lv_product_list3, R.id.lv_product_list4 };
	private static ListView[] listViews = new ListView[4];

	@Override
	protected void init() {
		rl = (RelativeLayout) View.inflate(context, R.layout.product_listview,
				null);
		for (int i = 0; i < 4; i++) {
			listViews[i] = (ListView) rl.findViewById(listViewId[i]);
			rl.removeView(listViews[i]);
		}
		productList = new ArrayList<Product>();
		adapter = new MyAdapter();
		productViewPager.setAdapter(new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return 4;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				System.out.println("什么位置上的object被销毁了：" + position);
				container.removeView((View) object);
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ListView productLv = listViews[position];
				container.addView(productLv);
				productLv.setAdapter(adapter);
				productLv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Product product =  productList.get(position);
						product.setPics(new String[]{"小图的url","小兔的url"});
						//大图暂时用的固定数据
						UIManager.getInstance().getMap().put("product",product);
						UIManager.getInstance().changeView(ProductDetailView.class);
					}
				});
				return productLv;
			}

		});
	}

	private void initTextView() {
		textRankSale.setBackgroundResource(R.drawable.segment_normal_2_bg);
		textRankPrice.setBackgroundResource(R.drawable.segment_normal_2_bg);
		textRankGood.setBackgroundResource(R.drawable.segment_normal_2_bg);
		textRankTime.setBackgroundResource(R.drawable.segment_normal_2_bg);
	}

	@Override
	protected void setListener() {
		textRankSale.setOnClickListener(this);
		textRankPrice.setOnClickListener(this);
		textRankGood.setOnClickListener(this);
		textRankTime.setOnClickListener(this);

		productViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				// segment_selected_1_bg segment_normal_2_bg
				case 0: // 根据销量进行降序排序
					saleSortDesc = true;
					sortDesc(productList, "saleNmuber");
					break;
				case 1: // 根据会员价格进行降序排序
					saleSortDesc = true;
					sortDesc(productList, "price");
					break;
				case 2: // 根据好评度进行降序排序
					saleSortDesc = true;
					sortDesc(productList, "score");
					break;
				case 3: // 根据上架时间进行降序排序
					saleSortDesc = true;
					sortDesc(productList, "timeGrounding");
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textRankSale:
			productViewPager.setCurrentItem(0);
			if(saleSortDesc){
				sortAsc(productList, "saleNmuber");
				saleSortDesc = false;
			}else{
				sortDesc(productList, "saleNmuber");
				saleSortDesc = true;
			}
			break;
		case R.id.textRankPrice:
			productViewPager.setCurrentItem(1);
			if(priceSortDesc){
				sortAsc(productList, "price");
				priceSortDesc = false;
			}else{
				sortDesc(productList, "price");
				priceSortDesc = true;
			}
			break;
		case R.id.textRankGood:
			productViewPager.setCurrentItem(2);
			if(goodSortDesc){
				sortAsc(productList, "score");
				goodSortDesc = false;
			}else{
				sortDesc(productList, "score");
				goodSortDesc = true;
			}
			break;
		case R.id.textRankTime:
			productViewPager.setCurrentItem(3);
			if(timeSortDesc){
				sortAsc(productList, "timeGrounding");
				timeSortDesc = false;
			}else{
				sortDesc(productList, "timeGrounding");
				timeSortDesc = true;
			}
			break;
		}
		super.onClick(v);
	}

	/**
	 * viewPager中对数组进行降序排序的方法
	 * 
	 * @param list
	 *            需要被排序的数组
	 * @param field
	 *            依据排序的字段
	 */
	public void sortDesc(final List<Product> list, final String fieldStr) {
		new MyAsynTask() {
			@Override
			public void onPreExecute() {
			}

			@Override
			public void onPostExecute() {
				adapter.notifyDataSetChanged();
				initTextView();
				if ("saleNmuber".equals(fieldStr)) {
					textRankSale
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				} else if ("price".equals(fieldStr)) {
					textRankPrice
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				} else if ("score".equals(fieldStr)) {
					textRankGood
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				} else if ("timeGrounding".equals(fieldStr)) {
					textRankTime
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				}
			}

			@Override
			public void doInBackground() {
				try {
					Field field = Product.class.getDeclaredField(fieldStr);
					field.setAccessible(true);
					for (int i = 0; i < list.size() - 1; i++) {
						for (int j = i + 1; j < list.size(); j++) {
							if (Double.parseDouble(field.get(list.get(i)).toString()) < Double.parseDouble(field
									.get(list.get(j)).toString())) {
								Product temp = list.get(i);
								list.set(i, list.get(j));
								list.set(j, temp);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.execute();
	}

	/**
	 * viewPager中对数组进行升序排序的方法
	 * 
	 * @param list
	 *            需要被排序的数组
	 * @param field
	 *            依据排序的字段
	 */
	public void sortAsc(final List<Product> list, final String fieldStr) {
		new MyAsynTask() {
			@Override
			public void onPreExecute() {
			}

			@Override
			public void onPostExecute() {
				adapter.notifyDataSetChanged();
				initTextView();
				if ("saleNmuber".equals(fieldStr)) {
					textRankSale
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				} else if ("price".equals(fieldStr)) {
					textRankPrice
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				} else if ("score".equals(fieldStr)) {
					textRankGood
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				} else if ("timeGrounding".equals(fieldStr)) {
					textRankTime
							.setBackgroundResource(R.drawable.segment_selected_1_bg);
				}
			}

			@Override
			public void doInBackground() {
				try {
					Field field = Product.class.getDeclaredField(fieldStr);
					field.setAccessible(true);
					for (int i = 0; i < list.size() - 1; i++) {
						for (int j = i + 1; j < list.size(); j++) {
							if (Double.parseDouble(field.get(list.get(i)).toString()) > Double.parseDouble(field
									.get(list.get(j)).toString())) {
								Product temp = list.get(i);
								list.set(i, list.get(j));
								list.set(j, temp);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.execute();
	}

	private List<Product> originalList = null;

	@Override
	public void onResume() {
		categoryEmptyListTv.setVisibility(View.INVISIBLE);
		final Map<String, Object> map = UIManager.getInstance().getMap();
		// 如果category不等于空说明是从分类列表跳进来的
		if (map.get("category") != null) {
			new MyAsynTask() {
				@Override
				public void onPreExecute() {
				}

				@Override
				public void onPostExecute() {
					adapter.notifyDataSetChanged();
					categoryEmptyListTv.setVisibility(View.INVISIBLE);
					map.clear();
				}

				@Override
				public void doInBackground() {
					ProductCategory category = (ProductCategory) map
							.get("category");
					String categoryName = category.getName();
					CategoryDetailEngine engine = BeanFactory
							.getImpl(CategoryDetailEngine.class);
					Map<String, String> params = new HashMap<String, String>();
					params.put("cId", "1010101");
					productList = engine.getProductList(params);
				}
			}.execute();
		} else if (map.get("productList") != null) {
			// 如果productList 不等于空 说明是从其他界面跳过来的
			// 获取传过来的productList
			productList = (List<Product>) map.get("productList");
			// 获得传进来的title并设置
			TitleManager.getInstance().getTitle()
					.setText(map.get("title").toString());
			adapter.notifyDataSetChanged();
			categoryEmptyListTv.setVisibility(View.INVISIBLE);
			map.clear();
		} else if (map.get("productSift") != null) {
			productSift = (ProductSift) map.get("productSift");
			originalList = (originalList == null) ? productList : originalList; // 如果是从筛选界面进来的话就让吧初始的list列表存储起来
			sift();
		}
		super.onResume();
	}

	private List<Product> fuben ;
	/**
	 * 从筛选界面回来的时候进行的筛选
	 */
	private void sift() {
		new MyAsynTask() {
			@Override
			public void onPreExecute() {
			}

			@Override
			public void onPostExecute() {
				productList = fuben;
				UIManager.getInstance().getMap().clear();
				String fieldStr = "";
				switch (productViewPager.getCurrentItem()) {
				case 0:
					fieldStr = "saleNmuber";
					saleSortDesc = true;
					sortDesc(productList, fieldStr);
					break;
				case 1:
					fieldStr = "price";
					priceSortDesc = true;
					sortDesc(productList, fieldStr);
					break;
				case 2:
					fieldStr = "score";
					goodSortDesc = true;
					sortDesc(productList, fieldStr);
					break;
				case 3:
					fieldStr = "timeGrounding";
					timeSortDesc = true;
					sortDesc(productList, fieldStr);
					break;
				}
				sortDesc(productList, fieldStr);
				categoryEmptyListTv.setVisibility(View.INVISIBLE);
			}

			@Override
			public void doInBackground() {
				fuben = new ArrayList<Product>();
				System.out.println("originalList的长度："+originalList.size());
				Random random = new Random();
				for (Product product : originalList) {
					if (!"全部".equals(productSift.getPrice()) && (int) product.getPrice() < Integer.parseInt(productSift
							.getPrice())) {
						System.out.println(product.getSaleNmuber());
						if (100 > random.nextInt(200)) {
							System.out.println("有被筛选出来的");
							fuben.add(product);
						}
					}
				}
				
			}
		}.execute();
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
		UIManager.getInstance().changeView(SiftView1.class);
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				convertView = View.inflate(context,
						R.layout.product_list_items, null);
				holder = new Holder();
				holder.goodsIconIv = (SmartImageView) convertView
						.findViewById(R.id.goodsIconIv);
				holder.textClothesName = (TextView) convertView
						.findViewById(R.id.textClothesName);
				holder.textClothesPrice = (TextView) convertView
						.findViewById(R.id.textClothesPrice);
				holder.textMarketPrice = (TextView) convertView
						.findViewById(R.id.textMarketPrice);
				holder.textProductCommentNum = (TextView) convertView
						.findViewById(R.id.textProductCommentNum);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			Product product = productList.get(position);
//			holder.goodsIconIv.setImageResource(R.drawable.i1);
			 holder.goodsIconIv.setImageUrl(product.getPic());
			holder.textClothesName.setText(product.getName());
			holder.textClothesPrice.setText("￥"+String.valueOf(product.getPrice()));
			holder.textMarketPrice.setText("渣"+String.valueOf(product
					.getMarketprice()));
			holder.textProductCommentNum.setText(String.valueOf(product
					.getComment_count()));
			return convertView;
		}

		@Override
		public int getCount() {
			return productList.size();
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
			public SmartImageView goodsIconIv; // 商品图标
			public TextView textClothesName; // 商品名字
			public TextView textClothesPrice; // 商品灰色价格
			public TextView textMarketPrice; // 商品市场价格
			public TextView textProductCommentNum; // 商品评论数

		}
	}

}
