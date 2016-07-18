package com.itheima.redbaby.ui;

import java.util.List;

import org.apache.commons.lang3.time.StopWatch;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.ShoppingSpringEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.TimerUtil;
import com.itheima.redbaby.view.MyScrollView;

/**
 * 抢购狂欢节之限时抢购
 * 
 * @author zhangyun
 * 
 */
public class ShoppingSpringView extends BaseView {

	public ShoppingSpringView(Context context) {
		super(context);
	}

	private MyScrollView viewPager;

	/**
	 * 产品集合
	 */
	private List<Product> productList;

	private List<View> viewList;

	@Override
	protected void loadMiddleLayout() {
		middleView = View
				.inflate(context, R.layout.dl_shoppingspringview, null);
		viewPager = (MyScrollView) middleView.findViewById(R.id.dl_vp_shopping);

	}
	
	@Override
	public void onResume() {

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

					for (int i = 0; i < productList.size(); i++) {
						final Product product = productList.get(i);

						View itemView = View.inflate(context,
								R.layout.dl_shoppingspringview_item, null);
						//当前背景容器
						final RelativeLayout rl_shoppingspring_item = (RelativeLayout) itemView.findViewById(R.id.rl_shoppingspring_item);
						//商品图片
						ImageView imageView = (ImageView) itemView
								.findViewById(R.id.iv_shoppingspring_product);
						//距离开始
						final TextView leftTime = (TextView) itemView
								.findViewById(R.id.tv_shoppingspring_last_time);
						//剩余件数
						final TextView leftnumber = (TextView) itemView
								.findViewById(R.id.tv_shoppingspring_last_number);
						//抢购价格
						final TextView leftprice = (TextView) itemView
								.findViewById(R.id.tv_shoppingspring_pice);
						//抢购名称
						final TextView leftpname = (TextView) itemView
								.findViewById(R.id.tv_shoppingspring_pname);
						//按钮状态
						final Button butState = (Button) itemView
								.findViewById(R.id.but_shoppingspring_state);
						

						imageLoader.displayImage(product.getPic(), imageView);
						leftTime.setText(TimerUtil.secToTime((int) product
								.getLefttime()));
						
						leftprice.setText("抢购价：" + String.valueOf(product.getLimitprice()));
						leftpname.setText(product.getName());
						leftnumber.setText("原价:" + String.valueOf(product.getPrice()));
						

						//倒计时核心代码
						new CountDownTimer(Long.MAX_VALUE, 1000) {
							int count = 0;
							@Override
							public void onTick(long millisUntilFinished) {
								count++;
								int startTime = (int) product.getLefttime() / 1000- count;
								int endTime = (int) product.getLefttime() / 1000 + 10 -count;
								if(startTime>0){
									leftTime.setText("距离开始：" + TimerUtil.secToTime(startTime));
									butState.setText("即将开始");
									rl_shoppingspring_item.setBackgroundResource(R.drawable.tm_bg_yellow_get_nothing);
									butState.setBackgroundResource(R.drawable.dd_yellow_qianggou);
									butState.setClickable(false);
								}else if(startTime==0){
									leftTime.setText("距离结束：" + TimerUtil.secToTime(endTime));
									rl_shoppingspring_item.setBackgroundResource(R.drawable.tm_bg_pink_get_nothing);
									butState.setBackgroundResource(R.drawable.dd_red_qianggou);
									butState.setText("立即抢购");
									butState.setClickable(true);
								}else if(startTime<0 && endTime>0){
									leftTime.setText("距离结束：" + TimerUtil.secToTime(endTime));
									butState.setText("立即抢购");
									rl_shoppingspring_item.setBackgroundResource(R.drawable.tm_bg_pink_get_nothing);
									butState.setBackgroundResource(R.drawable.dd_red_qianggou);
									butState.setClickable(true);
									butState.setOnClickListener(new OnClickListener() {
										
										@Override
										public void onClick(View v) {
											MiddleManager.getInstance().changeView(GoodsDetailsView.class, bundle);
										}
									});
								}else if(startTime<0 && endTime==0){
									this.cancel();
									leftTime.setText("距离结束：" + TimerUtil.secToTime(endTime));
									butState.setText("已经结束");
									rl_shoppingspring_item.setBackgroundResource(R.drawable.tm_bg_grey_get_nothing);
									butState.setBackgroundResource(R.drawable.dd_grey_qianggou);
									butState.setClickable(false);
								}
								
							}
							
							@Override
							public void onFinish() {
								
							}
						}.start();
						
						viewPager.addView(itemView);

					}

				}
				super.onPostExecute(result);
			}
		}.executeProxy();
		super.onResume();
	}

	@Override
	protected void findViewById() {
	}

	@Override
	protected void setListener() {
	}

	@Override
	protected void processEngine() {

	}

	@Override
	protected int getID() {
		return ConstantValue.SHOPPINGSPRING;
	}

}
