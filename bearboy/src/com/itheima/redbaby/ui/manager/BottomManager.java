package com.itheima.redbaby.ui.manager;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.ui.BrandView;
import com.itheima.redbaby.ui.HomeView;
import com.itheima.redbaby.ui.MyCountView;
import com.itheima.redbaby.ui.SearchView;
import com.itheima.redbaby.ui.ShoppingView;

/**
 * 底部导航栏管理类
 * 
 * @author zhangyun
 * 
 */
public class BottomManager implements OnClickListener, Observer {
	private static BottomManager bottomManager = new BottomManager();

	private BottomManager() {
	}

	public static BottomManager getInstance() {
		return bottomManager;
	}

	/**
	 * 底部的导航栏
	 */
	private LinearLayout dl_footer;

	/**
	 * 底部导航栏元素:主页
	 */
	private ImageView rl_home;
	/**
	 * 底部导航栏元素:搜索
	 */
	private ImageView rl_search;
	/**
	 * 底部导航栏元素:品牌
	 */
	private ImageView rl_brand;
	/**
	 * 底部导航栏元素:购物车
	 */
	private ImageView rl_shopping;
	/**
	 * 底部导航栏元素:更多
	 */
	private ImageView rl_mycount;

	/**
	 * 初始化底部导航栏
	 * 
	 * @param activity
	 */
	public void init(Activity activity) {
		dl_footer = (LinearLayout) activity.findViewById(R.id.dl_ll_footer);
		rl_home = (ImageView) dl_footer.findViewById(R.id.dl_iv_foot_home);
		rl_search = (ImageView) dl_footer.findViewById(R.id.dl_iv_foot_search);
		rl_brand = (ImageView) dl_footer.findViewById(R.id.dl_iv_foot_brand);
		rl_shopping = (ImageView) dl_footer.findViewById(R.id.dl_iv_foot_shopping);
		rl_mycount = (ImageView) dl_footer.findViewById(R.id.dl_iv_foot_mycount);
		setListener();
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		rl_home.setOnClickListener(this);
		rl_search.setOnClickListener(this);
		rl_brand.setOnClickListener(this);
		rl_shopping.setOnClickListener(this);
		rl_mycount.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dl_iv_foot_shopping:
			MiddleManager.getInstance().changeView(ShoppingView.class, null);
			break;
		case R.id.dl_iv_foot_home:
			MiddleManager.getInstance().changeView(HomeView.class, null);

			break;
		case R.id.dl_iv_foot_search:
			MiddleManager.getInstance().changeView(SearchView.class, null);
			break;
		case R.id.dl_iv_foot_brand:
			MiddleManager.getInstance().changeView(BrandView.class, null);

			break;
		case R.id.dl_iv_foot_mycount:

			MiddleManager.getInstance().changeView(MyCountView.class, null);
			break;
		}
	}

	/**
	 * 设置是否显示底部布局
	 */
	public void setShowFoot(boolean isShowFoot) {
		if (isShowFoot) {
			dl_footer.setVisibility(View.VISIBLE);
		} else {
			dl_footer.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置正在底部导航栏显示模块，默认为显示情况下
	 * 
	 * @param 正在显示的底部导航可以为
	 *            ： ConstantValue.isShowingHome ConstantValue.isShowingSearch
	 *            ConstantValue.isShowingClass ConstantValue.isShowingShopping
	 *            ConstantValue.isShowingMore
	 */
	public void setBottomBarView(int isShowingBottom) {
		if (isShowingBottom == 0) {
			throw new RuntimeException("请设置底部背景");
		}
		switch (isShowingBottom) {
		case ConstantValue.isShowingHome:
			initBottom();
			rl_home.setImageResource(R.drawable.dd_home_select);
			break;
		case ConstantValue.isShowingShopping:
			initBottom();
			rl_shopping.setImageResource(R.drawable.dd_shoppingcart_select);
			break;
		case ConstantValue.isShowingSearch:
			initBottom();
			rl_search.setImageResource(R.drawable.dd_search_select);
			break;
		case ConstantValue.isShowingBrand:
			initBottom();
			rl_brand.setImageResource(R.drawable.dd_brand_select);
			break;
		case ConstantValue.isShowingMyCount:
			initBottom();
			rl_mycount.setImageResource(R.drawable.dd_mycount_select);
			break;
		default:
			initBottom();
			break;
		}
	}

	/**
	 * 初始化底部导航栏
	 */
	private void initBottom() {
		rl_home.setImageResource(R.drawable.dd_home_normal);
		rl_search.setImageResource(R.drawable.dd_search_normal);
		rl_brand.setImageResource(R.drawable.dd_brand_normal);
		rl_shopping.setImageResource(R.drawable.dd_shoppingcart_normal);
		rl_mycount.setImageResource(R.drawable.dd_mycount_normal);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data != null && StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id) {
			case ConstantValue.VIEW_HOME:
			case ConstantValue.PROMOTIONCENTER:
			case ConstantValue.PROMOTIONITEM:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingHome);
				break;
			case ConstantValue.VIEW_SEARCH:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingSearch);
				break;
			case ConstantValue.VIEW_BRAND:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingBrand);
				break;
			case ConstantValue.VIEW_SHOPPINGCART:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingShopping);
				break;
			case ConstantValue.VIEW_MYCOUNT:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingMyCount);
				break;
			case ConstantValue.SHOPPINGSPRING:
				setShowFoot(false);
				setBottomBarView(ConstantValue.isShowingHome);
				break;
			case ConstantValue.NEWPRODUCT:
			case ConstantValue.HOTPRODUCT:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingSearch);
				break;
			case ConstantValue.BRANDITEM:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingBrand);
				break;
			case ConstantValue.MYCOUNTVIEW:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingMyCount);
				break;
			case ConstantValue.GOODSENTRY:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingHome);
				break;
			case ConstantValue.GOODSDETAILS:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingHome);
				break;
			case ConstantValue.GOODSBIGMAP:
				setShowFoot(true);
				setBottomBarView(ConstantValue.isShowingHome);
				break;
			default:
				break;
			}
		}
	}

}
