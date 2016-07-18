package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.ui.manager.BaseView;
/**
 * 
 * 大图浏览
 */
public class GoodsDetailsBigMapView extends BaseView {
	/**
	 * ViewPager
	 */
	private ViewPager dl_goods_bigmap_mypager;
	private List<LinearLayout> viewpargerItem;
	/**
	 * ImageView1
	 */
	private ImageView dl_goods_bigma_img1;
	/**
	 * ImageView2
	 */
	private ImageView dl_goods_bigma_img2;
	/**
	 * item1
	 */
	private LinearLayout ll1;
	/**
	 * item2
	 */
	private LinearLayout ll2;

	public GoodsDetailsBigMapView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(getContext(), R.layout.dl_goods_entry_xiangqing_bigpic, null);
	}

	@Override
	protected void findViewById() {
		dl_goods_bigmap_mypager = (ViewPager) middleView.findViewById(R.id.dl_goods_bigmap_mypager);
		dl_goods_bigma_img1 = (ImageView) middleView.findViewById(R.id.dl_goods_bigma_img1);
		dl_goods_bigma_img2 = (ImageView) middleView.findViewById(R.id.dl_goods_bigma_img2);
	}

	@Override
	protected void setListener() {
	}

	private ImageView dl_goods_bigmap_image1;
	private ImageView dl_goods_bigmap_image2;
	private TextView dl_goods_bigmap_name1;
	private TextView dl_goods_bigmap_name2;
	private TextView dl_goods_bigmap_money1;
	private TextView dl_goods_bigmap_money2;

	@Override
	protected void processEngine() {
		ll1 = (LinearLayout) View.inflate(getContext(), R.layout.dl_goods_xiangqing_item, null);
		ll2 = (LinearLayout) View.inflate(getContext(), R.layout.dl_goods_xiangqing_item, null);
		dl_goods_bigmap_image1 = (ImageView) ll1.findViewById(R.id.dl_goods_bigmap_image);
		dl_goods_bigmap_image2 = (ImageView) ll2.findViewById(R.id.dl_goods_bigmap_image);
		dl_goods_bigmap_name1 = (TextView) ll1.findViewById(R.id.dl_goods_bigmap_name);
		dl_goods_bigmap_name2 = (TextView) ll2.findViewById(R.id.dl_goods_bigmap_name);
		dl_goods_bigmap_money1 = (TextView) ll1.findViewById(R.id.dl_goods_bigmap_money);
		dl_goods_bigmap_money2 = (TextView) ll2.findViewById(R.id.dl_goods_bigmap_money);
		viewpargerItem=new ArrayList<LinearLayout>();
		viewpargerItem.add(ll1);
		viewpargerItem.add(ll2);
		dl_goods_bigmap_mypager.setAdapter(new MyPagerAtapter());
		dl_goods_bigmap_mypager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private String imgURL1;
	private String imgURL2;

	@Override
	public void onResume() {
		Product product = (Product) bundle.getSerializable("product");
		if (product != null) {
			if (product.getBigPic() != null) {
				String[] split = product.getBigPic().split(",");
				if (split.length == 2) {
					imgURL1 = split[0];
					imgURL2 = split[1];
				}
			}
			imageLoader.displayImage(ConstantValue.URI + imgURL1, dl_goods_bigmap_image1, options);
			imageLoader.displayImage(ConstantValue.URI + imgURL2, dl_goods_bigmap_image2, options);
			dl_goods_bigmap_name1.setText(product.getName());
			dl_goods_bigmap_name2.setText(product.getName());
			dl_goods_bigmap_money1.setText("会员价:$" + product.getPrice());
			dl_goods_bigmap_money2.setText("会员价:$" + product.getPrice());
		}
	}

	/**
	 * 图片跳转后更新text,image
	 */
	class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			if (position == 0) {
				dl_goods_bigma_img1.setImageResource(R.drawable.slide_adv_selected);
				dl_goods_bigma_img2.setImageResource(R.drawable.slide_adv_normal);
			} else {
				dl_goods_bigma_img2.setImageResource(R.drawable.slide_adv_selected);
				dl_goods_bigma_img1.setImageResource(R.drawable.slide_adv_normal);
			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
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
	@Override
	protected int getID() {
		return ConstantValue.GOODSBIGMAP;
	}
}
