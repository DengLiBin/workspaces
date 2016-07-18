package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.ShoppingSpringEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.TimerUtil;

/**
 * 抢购狂欢节之限时抢购
 * 
 * @author zhangyun
 * 
 */
public class ShoppingSpringView1 extends BaseView {

	public ShoppingSpringView1(Context context) {
		super(context);
	}

	private MyPageAdpater pageAdpater = new MyPageAdpater();
	private ViewPager viewPager;

	/**
	 * 产品集合
	 */
	private List<Product> productList;

	private List<View> viewList;


	@Override
	protected void loadMiddleLayout() {
		middleView = View
				.inflate(context, R.layout.dl_shoppingspringview, null);
		viewPager = (ViewPager) middleView.findViewById(R.id.dl_vp_shopping);
		viewList = new ArrayList<View>();
		new MyHttpTask<Void>() {

			@Override
			protected Object doInBackground(Void... params) {
				ShoppingSpringEngine engine = BeanFactory.getFactory()
						.getInstance(ShoppingSpringEngine.class);
				return engine.getShoppingSpring();
			}

			@Override
			protected void onPostExecute(Object result) {
				if (result != null) {
					productList = (List<Product>) result;
					pageAdpater = new MyPageAdpater();
					viewPager.setAdapter(pageAdpater);
				}
				super.onPostExecute(result);
			}
		}.executeProxy();

	}

	@Override
	protected void findViewById() {
	}

	@Override
	protected void setListener() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				int currentItem = viewPager.getCurrentItem();
				TextView view = (TextView) viewList.get(currentItem)
						.findViewById(R.id.tv_shoppingspring_last_time);
				final Product product = productList.get(position);
				final TextView leftTime = (TextView) viewList.get(position)
						.findViewById(R.id.tv_shoppingspring_last_time);
				
				
				
//				if(isTimer){
//					new CountDownTimer(Long.MAX_VALUE, 1000) {
//
//						@Override
//						public void onTick(long millisUntilFinished) {
//							count++;
//							int lastTime = (int) product.getLefttime() / 1000
//									- count;
//							leftTime.setText("剩余时间："
//									+ TimerUtil.secToTime(lastTime));
//						}
//
//						@Override
//						public void onFinish() {
//
//						}
//					}.start();
//					
//					isTimer = false;
//				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	@Override
	protected void processEngine() {

	}

	@Override
	protected int getID() {
		return ConstantValue.SHOPPINGSPRING;
	}

	boolean isTimer = false;
	

	private class MyPageAdpater extends PagerAdapter {

		@Override
		public int getCount() {
			return productList.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			System.out.println(position);
			final Product product = productList.get(position);
			View itemView = View.inflate(context,
					R.layout.dl_shoppingspringview_item, null);
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.iv_shoppingspring_product);
			final TextView leftTime = (TextView) itemView
					.findViewById(R.id.tv_shoppingspring_last_time);
			Button butState = (Button) itemView
					.findViewById(R.id.but_shoppingspring_state);

			imageLoader.displayImage(product.getPic(), imageView);
			leftTime.setText(TimerUtil.secToTime((int) product.getLefttime()));

			//倒计时核心代码
			CountDownTimer timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
				int count = 0;
				@Override
				public void onTick(long millisUntilFinished) {
					count++;
					int lastTime = (int) product.getLefttime() / 1000
							- count;
					leftTime.setText("剩余时间："
							+ TimerUtil.secToTime(lastTime));
				}

				@Override
				public void onFinish() {
					
				}
			}.start();
			itemView.setTag(timer);
			
			container.addView(itemView);
			viewList.add(itemView);
			return itemView;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = viewList.get(position);
			container.removeView(view);
		}
		

	}

}
