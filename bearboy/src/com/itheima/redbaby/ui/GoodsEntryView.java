package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.goodsentry.GoodsTabulation;
import com.itheima.redbaby.engine.GoodsListEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 商品列表布局
 * 
 * @author qw
 * 
 */
public class GoodsEntryView extends BaseView implements OnClickListener, OnItemClickListener {
	/**
	 * 商品ListView
	 */
	private ListView listview;
	private MyAdapter listviewAdapter;
	private List<Product> products;
	/**
	 * 商品GridView
	 */
	private GridView gridview;
	private MyAdapter gridviewAdapter;
	/**
	 * 头部布局
	 */
	private LinearLayout title_ll;
	private TextView text_goback;
	/**
	 * 改变listview和gridview
	 */
	private ImageView img_changeadapter;
	/**
	 * 筛选
	 */
	private TextView text_choose;
	/**
	 * 分类
	 */
	private TextView dl_goods_entry_texttitle;
	private LinearLayout dl_goods_entry_sort1;
	private LinearLayout dl_goods_entry_sort2;
	private ImageView dl_goods_entry_sort2_img;
	private LinearLayout dl_goods_entry_sort3;
	/**
	 * 是否是listView布局
	 */
	private boolean isListView;
	/**
	 * 是否价格升序
	 */
	private boolean isMoneyUp;
	private String cId;

	public GoodsEntryView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_goods_entry, null);
	}

	@Override
	protected void findViewById() {
		listview = (ListView) middleView.findViewById(R.id.dl_goods_entry_listview);
		gridview = (GridView) middleView.findViewById(R.id.dl_goods_entry_gridview);
		title_ll = (LinearLayout) middleView.findViewById(R.id.dl_goods_entry_title_ll);
		text_goback = (TextView) middleView.findViewById(R.id.dl_goods_entry_goback);
		dl_goods_entry_texttitle = (TextView) middleView.findViewById(R.id.dl_goods_entry_texttitle);
		img_changeadapter = (ImageView) middleView.findViewById(R.id.dl_goods_entry_change_adapter);
		text_choose = (TextView) middleView.findViewById(R.id.dl_goods_entry_choose);
		dl_goods_entry_sort1 = (LinearLayout) middleView.findViewById(R.id.dl_goods_entry_sort1);
		dl_goods_entry_sort2 = (LinearLayout) middleView.findViewById(R.id.dl_goods_entry_sort2);
		dl_goods_entry_sort2_img = (ImageView) middleView.findViewById(R.id.dl_goods_entry_sort2_img);
		dl_goods_entry_sort3 = (LinearLayout) middleView.findViewById(R.id.dl_goods_entry_sort3);
	}

	@Override
	protected void setListener() {
		title_ll.setOnClickListener(this);
		text_goback.setOnClickListener(this);
		img_changeadapter.setOnClickListener(this);
		text_choose.setOnClickListener(this);
		dl_goods_entry_sort1.setOnClickListener(this);
		dl_goods_entry_sort2.setOnClickListener(this);
		dl_goods_entry_sort3.setOnClickListener(this);
		listview.setOnItemClickListener(this);
		gridview.setOnItemClickListener(this);
	}

	@Override
	protected void processEngine() {
		products = new ArrayList<Product>();
		isListView = true;
		isMoneyUp = true;
		listviewAdapter = new MyAdapter(R.layout.dl_goods_entry_listview_item);
		gridviewAdapter = new MyAdapter(R.layout.dl_goods_entry_gridview_item);
		listview.setAdapter(listviewAdapter);
	}

	@Override
	public void onPause() {
	}

	/**
	 * 从bundle获取对应2级类型,获取网络数据封装成GoodsTabulation
	 */
	@Override
	public void onResume() {
		whichNow = 0;
		// products = (List<Product>) getBundle().get("key");
		cId = getBundle().getString("cId");
		if (cId == null)
			cId = "3";
		// if (products == null) {
		// products = new ArrayList<Product>();
		// new MyHttpTastImpl(cId, "1", "10", "comment_down", 1).executeProxy();
		// } else {
		// gridviewAdapter.notifyDataSetChanged();
		// listviewAdapter.notifyDataSetChanged();
		// }
		new MyHttpTastImpl(cId, "1", "10", "comment_down", 1).executeProxy();
		gridview.setAdapter(null);
		listview.setAdapter(listviewAdapter);
		isListView = true;
		img_changeadapter.setImageResource(R.drawable.listview_haha);
		if (bundle.getString("one") != null) {
			dl_goods_entry_texttitle.setText(bundle.getString("one") + "->" + bundle.getString("two"));
		} else {
			dl_goods_entry_texttitle.setText("所有商品");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dl_goods_entry_title_ll:
			// 处理标题隐藏,后期处理
			break;
		case R.id.dl_goods_entry_goback:
			// 返回键
			MiddleManager.getInstance().goBack();
			break;
		case R.id.dl_goods_entry_change_adapter:
			// 更改listview和adapter
			if (isListView) {
				listview.setAdapter(null);
				gridview.setAdapter(gridviewAdapter);
				img_changeadapter.setImageResource(R.drawable.gridview_hehe);
			} else {
				gridview.setAdapter(null);
				listview.setAdapter(listviewAdapter);
				img_changeadapter.setImageResource(R.drawable.listview_haha);
			}
			isListView = !isListView;
			break;
		case R.id.dl_goods_entry_choose:
			// 筛选
			screenGoods();
			break;
		case R.id.dl_goods_entry_sort1:
			// 综合排序(评价数)
			new MyHttpTastImpl(cId, "1", "10", "comment_down", 1).executeProxy();
			break;
		case R.id.dl_goods_entry_sort2:
			// 价格排序
			if (isMoneyUp) {
				runAnim(dl_goods_entry_sort2_img, 0, 180, 300);
				new MyHttpTastImpl(cId, "1", "10", "price_up", 2).executeProxy();
			} else {
				runAnim(dl_goods_entry_sort2_img, 180, 360, 300);
				new MyHttpTastImpl(cId, "1", "10", "price_down", 3).executeProxy();
			}
			isMoneyUp = !isMoneyUp;
			break;
		case R.id.dl_goods_entry_sort3:
			// \下载数排序
			new MyHttpTastImpl(cId, "1", "10", "sale_down", 4).executeProxy();
			break;
		}
	}

	private void runAnim(View rl, float start, float end, long duration) {
		RotateAnimation ra = new RotateAnimation(start, end, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(duration);
		ra.setFillAfter(true);
		rl.startAnimation(ra);
	}

	private class MyAdapter extends BaseAdapter {
		private int layout;

		public MyAdapter(int layout) {
			super();
			this.layout = layout;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Hodler hodler = null;
			if (convertView != null) {
				hodler = (Hodler) convertView.getTag();
			} else {
				convertView = View.inflate(getContext(), layout, null);
				hodler = new Hodler();
				hodler.dl_goods_listview_buynum = (TextView) convertView.findViewById(R.id.dl_goods_listview_buynum);
				hodler.dl_goods_listview_img = (ImageView) convertView.findViewById(R.id.dl_goods_listview_img);
				hodler.dl_goods_listview_money = (TextView) convertView.findViewById(R.id.dl_goods_listview_money);
				hodler.dl_goods_listview_title = (TextView) convertView.findViewById(R.id.dl_goods_listview_title);
				convertView.setTag(hodler);
			}
			Product product = products.get(position);
			hodler.dl_goods_listview_buynum.setText("已卖出" + product.getBuyCount() + "个");
			hodler.dl_goods_listview_money.setText("$" + product.getPrice());
			hodler.dl_goods_listview_title.setText(product.getName());
			imageLoader.displayImage(ConstantValue.URI + product.getPic(), hodler.dl_goods_listview_img, options);
			return convertView;
		}

		@Override
		public int getCount() {
			return products.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		private class Hodler {
			ImageView dl_goods_listview_img;
			TextView dl_goods_listview_title;
			TextView dl_goods_listview_buynum;
			TextView dl_goods_listview_money;
		}
	}

	/**
	 * 填充list,更改adapter
	 * 
	 * @param sign
	 */
	private void fillAdapter(GoodsTabulation result, int sign) {
		products = result.getProductlist();
		switch (sign) {
		case 1:// 评论数
			Collections.sort(products, new MyComparatorSrot1());
			break;
		case 2:// 价格升
			Collections.sort(products, new MyComparatorSrot2());
			break;
		case 3:// 价格降
			Collections.sort(products, Collections.reverseOrder(new MyComparatorSrot2()));
			break;
		case 4:// 下载数
			Collections.sort(products, new MyComparatorSrot3());
			break;
		}
		switch (whichNow) {
		case 0:
			shaixuanGoods(0, 50000);
			break;
		case 1:
			shaixuanGoods(0, 100);
			break;
		case 2:
			shaixuanGoods(100, 200);
			break;
		case 3:
			shaixuanGoods(200, 300);
			break;
		case 4:
			shaixuanGoods(300, 50000);
			break;
		}
		gridviewAdapter.notifyDataSetChanged();
		listviewAdapter.notifyDataSetChanged();
	};

	public class MyHttpTastImpl extends MyHttpTask<Void> {
		private String cId;
		private String page;
		private String pageNum;
		private String srot;
		private int sign;

		public MyHttpTastImpl(String cId, String page, String pageNum, String srot, int sign) {
			super();
			this.cId = cId;
			this.page = page;
			this.pageNum = pageNum;
			this.srot = srot;
		}

		@Override
		protected Object doInBackground(Void... params) {
			GoodsListEngine goodsListEngine = BeanFactory.getFactory().getInstance(GoodsListEngine.class);
			return goodsListEngine.getGoodsList(cId, page, pageNum, srot);
		}

		protected void onPostExecute(Object result) {
			if (result != null) {
				fillAdapter((GoodsTabulation) result, sign);
			} else {
				PromptManager.showToast(getContext(), "请求数据异常");
			}
		}
	}

	private class MyComparatorSrot1 implements Comparator<Product> {
		@Override
		public int compare(Product lhs, Product rhs) {
			if (lhs.getCommentCount() > rhs.getCommentCount()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	private class MyComparatorSrot2 implements Comparator<Product> {
		@Override
		public int compare(Product lhs, Product rhs) {
			if (lhs.getMarketprice() > rhs.getMarketprice()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	private class MyComparatorSrot3 implements Comparator<Product> {
		@Override
		public int compare(Product lhs, Product rhs) {
			if (lhs.getBuyCount() > rhs.getBuyCount()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String GoodsId = products.get(position).getId();
		Bundle bundle2 = new Bundle();
		bundle2.putString("id", GoodsId);
		MiddleManager.getInstance().changeView(GoodsDetailsView.class, bundle2);
	}

	@Override
	protected int getID() {
		return ConstantValue.GOODSENTRY;
	}

	/**
	 * 筛选
	 */
	private String[] items = { "查看所有", "0~100元", "100~200元", "200~300元", "300以上" };
	private int whichNow = 0;

	private void screenGoods() {
		AlertDialog.Builder builder = new Builder(getContext());
		builder.setTitle("商品会员价格筛选");
		builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				whichNow = which;
				new MyHttpTastImpl(cId, "1", "10", "comment_down", 2).executeProxy();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}

	private void shaixuanGoods(int min, int max) {
		for (int i = 0; i < products.size(); i++) {
			if (min > products.get(i).getPrice() || products.get(i).getPrice() > max) {
				products.remove(i);
				i--;
			}
		}
	}
}
