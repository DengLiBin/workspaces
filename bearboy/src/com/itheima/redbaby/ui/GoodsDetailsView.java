package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.GloableParameters;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.GoodsListEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.ui.manager.ShoppingCart;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.DensityUtil;
import com.itheima.redbaby.utils.PromptManager;
/**
 * 商品详情
 *
 */
public class GoodsDetailsView extends BaseView implements OnClickListener {
	/**
	 * pager
	 */
	private ViewPager dl_product_detail_mypager;
	/**
	 * 商品名字
	 */
	private TextView dl_product_detail_goodsname;
	/**
	 * 市场价
	 */
	private TextView dl_product_detail_goodsmoney;
	/**
	 * 会员价
	 */
	private TextView dl_product_detail_smallmoney;
	/**
	 * 尺码 颜色
	 */
	private RelativeLayout dl_product_detail_prod_property;
	/**
	 * 数量选择
	 */
	private TextView dl_product_detail_prodNumValue;
	/**
	 * 加入购物车
	 */
	private TextView dl_product_detail_textPutIntoShopcar;
	/**
	 * 收藏
	 */
	private TextView dl_product_detail_textProdToCollect;
	/**
	 * 可送货到
	 */
	private TextView dl_product_detail_where_have_huo;
	/**
	 * 察看评论
	 */
	private ImageView dl_product_detail_pinglun;
	/**
	 * 选择图片1
	 */
	private ImageView dl_product_detail_image1;
	/**
	 * 选择图片2
	 */
	private ImageView dl_product_detail_image2;

	public GoodsDetailsView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(getContext(), R.layout.dl_product_detail_activity, null);
	}

	@Override
	protected void findViewById() {
		dl_product_detail_mypager = (ViewPager) middleView.findViewById(R.id.dl_product_detail_mypager);
		dl_product_detail_goodsname = (TextView) middleView.findViewById(R.id.dl_product_detail_goodsname);
		dl_product_detail_goodsmoney = (TextView) middleView.findViewById(R.id.dl_product_detail_goodsmoney);
		dl_product_detail_smallmoney = (TextView) middleView.findViewById(R.id.dl_product_detail_smallmoney);
		dl_product_detail_prod_property = (RelativeLayout) middleView.findViewById(R.id.dl_product_detail_prod_property);
		dl_product_detail_prodNumValue = (TextView) middleView.findViewById(R.id.dl_product_detail_buyLimit);
		dl_product_detail_textPutIntoShopcar = (TextView) middleView.findViewById(R.id.dl_product_detail_textPutIntoShopcar);
		dl_product_detail_textProdToCollect = (TextView) middleView.findViewById(R.id.dl_product_detail_textProdToCollect);
		dl_product_detail_where_have_huo = (TextView) middleView.findViewById(R.id.dl_product_detail_where_have_huo);
		dl_product_detail_pinglun = (ImageView) middleView.findViewById(R.id.dl_product_detail_pinglun);
		dl_product_detail_image1 = (ImageView) middleView.findViewById(R.id.dl_product_detail_image1);
		dl_product_detail_image2 = (ImageView) middleView.findViewById(R.id.dl_product_detail_image2);
	}

	@Override
	protected void setListener() {
		dl_product_detail_textPutIntoShopcar.setOnClickListener(this);
		dl_product_detail_textProdToCollect.setOnClickListener(this);
		dl_product_detail_pinglun.setOnClickListener(this);
	}

	/**
	 * 存放viewpager的item
	 */
	private List<LinearLayout> viewpargerItem;
	/**
	 * 
	 */
	private ImageView dl_goods_entry_viewpager_image1;
	private ImageView dl_goods_entry_viewpager_image2;

	@Override
	protected void processEngine() {
		viewpargerItem = new ArrayList<LinearLayout>();
		LinearLayout ll = (LinearLayout) View.inflate(getContext(), R.layout.dl_goods_entry_viewpager_item, null);
		dl_goods_entry_viewpager_image1 = (ImageView) ll.findViewById(R.id.dl_goods_entry_viewpager_image);
		LinearLayout ll2 = (LinearLayout) View.inflate(getContext(), R.layout.dl_goods_entry_viewpager_item, null);
		dl_goods_entry_viewpager_image2 = (ImageView) ll2.findViewById(R.id.dl_goods_entry_viewpager_image);
		viewpargerItem.add(ll);
		viewpargerItem.add(ll2);
		dl_product_detail_mypager.setAdapter(new MyPagerAtapter());
		dl_product_detail_mypager.setOnPageChangeListener(new MyOnPageChangeListener());
		dl_goods_entry_viewpager_image1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MiddleManager.getInstance().changeView(GoodsDetailsBigMapView.class, bundle3);
			}
		});
		dl_goods_entry_viewpager_image2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MiddleManager.getInstance().changeView(GoodsDetailsBigMapView.class, bundle3);
			}
		});
		initpopupwindow();
	}

	@Override
	protected int getID() {
		return ConstantValue.GOODSDETAILS;
	}

	@Override
	public void onResume() {
		new MyHttpTask<String>() {
			@Override
			protected Object doInBackground(String... params) {
				GoodsListEngine goodsListEngine = BeanFactory.getFactory().getInstance(GoodsListEngine.class);
				return goodsListEngine.getProduct(params[0]);
			}

			@Override
			protected void onPostExecute(Object result) {
				if (result != null) {
					changeContext((Product) result);
				} else {
					PromptManager.showToast(getContext(), "请求数据异常");
					MiddleManager.getInstance().goBack();
				}
			}
		}.execute(bundle.getString("id"));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dl_product_detail_textPutIntoShopcar:// 加入购物车
			if ("".equals(GloableParameters.USER_NAME)) {
				PromptManager.showToast(getContext(), "请先登录");
				MiddleManager.getInstance().changeView(LoginView.class, null);
			} else {
				PromptManager.showToast(getContext(), "加入成功");
				ShoppingCart.getInstance().getCart().add(product);
			}
			break;
		case R.id.dl_product_detail_textProdToCollect:// 收藏
			if ("".equals(GloableParameters.USER_NAME)) {
				PromptManager.showToast(getContext(), "请先登录");
				MiddleManager.getInstance().changeView(LoginView.class, null);
			} else {
				ShoppingCart.getInstance().getPros().add(product);
				PromptManager.showToast(getContext(), "收藏成功");
			}
			break;
		case R.id.dl_product_detail_pinglun:// 评论
			if ("".equals(GloableParameters.USER_NAME))
				MiddleManager.getInstance().changeView(LoginView.class, null);
			else
				PromptManager.showToast(getContext(), "评论系统暂没开放");
			break;
		}
	}

	/**
	 * ViewPager适配器
	 * 
	 */
	class MyPagerAtapter extends PagerAdapter {
		@Override
		public int getCount() {
			return viewpargerItem.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewpargerItem.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(viewpargerItem.get(position));
			return viewpargerItem.get(position);
		}
	}

	/**
	 * 图片跳转后更新text,image
	 */
	class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			if (position == 0) {
				dl_product_detail_image1.setImageResource(R.drawable.slide_adv_selected);
				dl_product_detail_image2.setImageResource(R.drawable.slide_adv_normal);
			} else {
				dl_product_detail_image2.setImageResource(R.drawable.slide_adv_selected);
				dl_product_detail_image1.setImageResource(R.drawable.slide_adv_normal);
			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	}

	private Product product;
	private String imgURL1;
	private String imgURL2;
	private Bundle bundle3;

	/**
	 * 获取到数据后更新布局
	 */
	private void changeContext(Product result) {
		dl_product_detail_goodsname.setText(result.getName());
		// dl_product_detail_goodsmoney.setText("$" + result.getMarketprice());
		dl_product_detail_smallmoney.setText("$" + result.getPrice());
		dl_product_detail_prodNumValue.setText(result.getBuyLimit() + "个");
		dl_product_detail_where_have_huo.setText(result.getInventoryArea());
		if (result.getBigPic() != null) {
			String[] split = result.getBigPic().split(",");
			if (split.length == 2) {
				imgURL1 = split[0];
				imgURL2 = split[1];
			}
		}
		imageLoader.displayImage(ConstantValue.URI + imgURL1, dl_goods_entry_viewpager_image1, options);
		imageLoader.displayImage(ConstantValue.URI + imgURL2, dl_goods_entry_viewpager_image2, options);
		product = result;
		bundle3 = new Bundle();
		bundle3.putSerializable("product", product);
		middleView.findViewById(R.id.dl_product_detail_textSize).setVisibility(View.VISIBLE);
		middleView.findViewById(R.id.dl_product_detail_textSizeValue).setVisibility(View.VISIBLE);
		if (result.getProduct_property() != null && result.getProduct_property().size() == 5) {
			Map<String, String> map = new HashMap<String, String>();
			product.setProduct_propertys(map);
			product.getProduct_propertys().put("颜色", "1");
			product.getProduct_propertys().put("大小", "4");
			dl_product_detail_prod_property.setVisibility(View.VISIBLE);
		} else if (result.getProduct_property() != null && result.getProduct_property().size() == 3) {
			Map<String, String> map = new HashMap<String, String>();
			product.setProduct_propertys(map);
			product.getProduct_propertys().put("颜色", "1");
			product.getProduct_propertys().put("大小", "4");
			dl_product_detail_prod_property.setVisibility(View.VISIBLE);
			middleView.findViewById(R.id.dl_product_detail_textSize).setVisibility(View.INVISIBLE);
			middleView.findViewById(R.id.dl_product_detail_textSizeValue).setVisibility(View.INVISIBLE);
		} else {
			dl_product_detail_prod_property.setVisibility(View.GONE);
		}
	}

	/**
	 * 以下是popuwindow~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	private String[] colors = new String[] { "红色", "蓝色", "绿色" };
	private String[] daxiao = new String[] { "L", "K", "M" };
	private TextView dl_product_detail_textColorValue;
	private TextView dl_product_detail_textSizeValue;
	private PopupWindow popupWindow1;
	private PopupWindow popupWindow2;
	private ListView listView;
	private ListView listView2;

	private void initpopupwindow() {
		dl_product_detail_textColorValue = (TextView) middleView.findViewById(R.id.dl_product_detail_textColorValue);
		dl_product_detail_textSizeValue = (TextView) middleView.findViewById(R.id.dl_product_detail_textSizeValue);
		listView = new ListView(getContext());
		listView.setVerticalScrollBarEnabled(false);// 隐藏滚动条
		listView.setDividerHeight(0);// 没有分割线
		listView.setDivider(null);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				dl_product_detail_textColorValue.setText(colors[arg2]);
				product.getProduct_propertys().put("颜色", (arg2 + 1) + "");
				dismissPopupWindow(popupWindow1);
			}
		});
		listView.setAdapter(new MyListViewAdapter(true));
		listView2 = new ListView(getContext());
		listView2.setVerticalScrollBarEnabled(false);// 隐藏滚动条
		listView2.setDividerHeight(0);// 没有分割线
		listView2.setDivider(null);
		listView2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				dl_product_detail_textSizeValue.setText(daxiao[arg2]);
				product.getProduct_propertys().put("大小", (arg2 + 4) + "");
				dismissPopupWindow(popupWindow2);
			}
		});
		listView2.setAdapter(new MyListViewAdapter(false));
		popupWindow1 = new PopupWindow(listView, DensityUtil.px2dip(getContext(), 90), -2);
		popupWindow1.setOutsideTouchable(true);// 点击外部关闭
		popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
		popupWindow1.setFocusable(true);// 可以得到焦点
		dl_product_detail_textColorValue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow1.showAsDropDown(dl_product_detail_textColorValue, 5, -5);
			}
		});
		popupWindow2 = new PopupWindow(listView2, DensityUtil.px2dip(getContext(), 60), -2);
		popupWindow2.setOutsideTouchable(true);// 点击外部关闭
		popupWindow2.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
		popupWindow2.setFocusable(true);// 可以得到焦点
		dl_product_detail_textSizeValue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow2.showAsDropDown(dl_product_detail_textSizeValue, 5, -5);
			}
		});
	}

	private class MyListViewAdapter extends BaseAdapter {
		private boolean isColor;

		public MyListViewAdapter(boolean isColor) {
			super();
			this.isColor = isColor;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			TextView tv = new TextView(getContext());
			if (isColor) {
				tv.setText(colors[position]);
			} else {
				tv.setText(daxiao[position]);
			}
			return tv;
		}
	}

	private void dismissPopupWindow(PopupWindow popupWindow) {
		if (popupWindow != null) {
			popupWindow.dismiss();
			popupWindow = null;
		}
	}

}
