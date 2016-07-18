package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.home.Banner;
import com.itheima.redbaby.bean.home.HomeBean;
import com.itheima.redbaby.engine.HomeEngine;
import com.itheima.redbaby.engine.ShoppingSpringEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.TimerUtil;

/**
 * 首页的界面
 * 
 * @author my
 * 
 */
public class HomeView extends BaseView implements OnClickListener {

	protected static final String TAG = "HomeView";
	/**
	 * viewPager的广告
	 */
	private ViewPager vp_ad;
	/**
	 * 促销快报
	 */
	private ImageView iv_sales;
	/**
	 * 新品首发
	 */
	private ImageView iv_new_staff;
	/**
	 * 推荐品牌
	 */
	private ImageView iv_recommand;
	/**
	 * 热门单品
	 */
	private ImageView iv_single;
	private ArrayList<ImageView> imageViewList;
	private LinearLayout ll_points;
	private int priviousSelectPosition = 0;

	public HomeView(Context context) {
		super(context);

	}

	@Override
	public void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_home, null);
	}

	@Override
	public void findViewById() {

		vp_ad = (ViewPager) middleView.findViewById(R.id.dl_vp_home_ad);

		iv_sales = (ImageView) middleView.findViewById(R.id.dl_iv_home_sales);
		iv_new_staff = (ImageView) middleView.findViewById(R.id.dl_iv_home_new_staff);
		iv_recommand = (ImageView) middleView.findViewById(R.id.dl_iv_home_recommand);
		iv_single = (ImageView) middleView.findViewById(R.id.dl_iv_home_single);
		ll_points = (LinearLayout) middleView.findViewById(R.id.dl_ll_home_point);
		ll_panic_buying = (LinearLayout) middleView.findViewById(R.id.dl_ll_home_panic_buying);
		ll_sales_promotion = (LinearLayout) middleView.findViewById(R.id.dl_ll_home_sales_promotion);

	}

	@Override
	public void setListener() {
		iv_sales.setOnClickListener(this);
		iv_new_staff.setOnClickListener(this);
		iv_recommand.setOnClickListener(this);
		iv_single.setOnClickListener(this);
	}

	private PagerAdapter adapter;
	/**
	 * 限时抢购
	 */
	private LinearLayout ll_panic_buying;
	private LinearLayout ll_sales_promotion;
	private ListView lv_home;

	@Override
	public void processEngine() {
		prepareData();

	}

	private void addUrl() {
		imageLoader.displayImage(ConstantValue.URI + typeUrl.get(0), iv_new_staff, options);
		imageLoader.displayImage(ConstantValue.URI + typeUrl.get(1), iv_recommand, options);
		imageLoader.displayImage(ConstantValue.URI + typeUrl.get(2), iv_sales, options);
		imageLoader.displayImage(ConstantValue.URI + typeUrl.get(3), iv_single, options);
	}

	/**
	 * TODO 张运 促销活动
	 */
	private void loadSalesPromotion() {
		ll_sales_promotion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MiddleManager.getInstance().changeView(PromotionCenterView.class, null);
			}
		});
	}

	/**
	 * TODO 张运 限时抢购load页面
	 */
	private void loadPanicBuying() {
		ll_panic_buying.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MiddleManager.getInstance().changeView(ShoppingSpringView.class, null);
			}
		});
	}

	@Override
	protected int getID() {
		return ConstantValue.VIEW_HOME;
	}

	/**
	 * TODO 张运 将各种界面做好以后放上去
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dl_iv_home_sales:
			// TODO 促销快报
			MiddleManager.getInstance().changeView(PromotionCenterView.class, null);
			break;
		case R.id.dl_iv_home_new_staff:
			// TODO 新品首发
			MiddleManager.getInstance().changeView(NewProductView.class, null);
			break;
		case R.id.dl_iv_home_recommand:
			// TODO 推荐品牌
			MiddleManager.getInstance().changeView(BrandView.class, null);
			break;
		case R.id.dl_iv_home_single:
			// TODO 热门单品
			MiddleManager.getInstance().changeView(HotProductView.class, null);
			break;

		default:
			break;
		}
	}

	/**
	 * 准备ViewPager的各种
	 * <p>
	 * string id
	 * <p>
	 * string url
	 */
	private Map<String, String> viewPagerUrl = null;
	/**
	 * 准备下面商品的各种图片
	 */
	private List<String> typeUrl = null;

	private HomeBean bean;
	private HorizontalScrollView pranicBuyView;

	private void prepareData() {

		new MyHttpTask<Void>() {

			@Override
			protected Object doInBackground(Void... params) {

				HomeEngine homeEngine = BeanFactory.getFactory().getInstance(HomeEngine.class);
				return homeEngine.getHomeMsg();
				
			}

			@Override
			protected void onPostExecute(Object result) {
				viewPagerUrl = new HashMap<String, String>();
				typeUrl = new ArrayList<String>();
				if (result != null) {
					bean = (HomeBean) result;
					prepareDataUrl();
					addUrl();
					adapter = new ViewPagerAdapter();
					vp_ad.setAdapter(adapter);
					loadPanicBuying();
					loadSalesPromotion();
				}
			}
		}.executeProxy();

	}

	/**
	 * 准备数据
	 */
	private void prepareDataUrl() {
		List<Banner> banners = bean.getHome_banner();
		for (Banner banner : banners) {
			if (Integer.parseInt(banner.getId()) <= 5) {
				viewPagerUrl.put(banner.getId(), banner.getPic());
			} else {
				typeUrl.add(banner.getPic());
			}
		}
		imageViewList = new ArrayList<ImageView>();
		ImageView iv;
		// 点view
		View view;
		// viewPagerUrl Map<String,String>
		Set<String> keySet = viewPagerUrl.keySet();
		for (String string : keySet) {

			iv = new ImageView(getContext());

			final String count = string;
			imageLoader.displayImage(ConstantValue.URI + viewPagerUrl.get(string), iv, options);
			imageViewList.add(iv);
			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO count为id
					System.out.println(count);
				}
			});
			// 添加点view对象
			view = new View(getContext());
			view.setBackgroundDrawable(getChild().getResources().getDrawable(R.drawable.point_background));
			LayoutParams lp = new LayoutParams(5, 5);
			lp.leftMargin = 5;
			view.setLayoutParams(lp);
			view.setEnabled(false);
			ll_points.addView(view);
		}
		ll_points.getChildAt(priviousSelectPosition).setEnabled(true);

		vp_ad.setAdapter(adapter);
		vp_ad.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				System.out.println(position);
				// 改变图片的描述信息
				// 切换选中的点
				ll_points.getChildAt(priviousSelectPosition).setEnabled(false);
				ll_points.getChildAt(position % imageViewList.size()).setEnabled(true);
				priviousSelectPosition = position % imageViewList.size();

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		// 设置第一张图片位置
		int n = Integer.MAX_VALUE / 2 % imageViewList.size();
		int m = Integer.MAX_VALUE / 2 - n;
		vp_ad.setCurrentItem(m);

	}

	/**
	 * viewpager
	 * 
	 * @author my
	 * 
	 */
	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * 判断出去的view是否等于是否等于进来的view，如果为true，则复用
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 销毁预加载以外的view对象，会把需要销毁的对象的索引位对象销毁
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(imageViewList.get(position % imageViewList.size()));
		}

		/**
		 * 创建一个view
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imageViewList.get(position % imageViewList.size()));
			return imageViewList.get(position % imageViewList.size());
		}

	}

}
