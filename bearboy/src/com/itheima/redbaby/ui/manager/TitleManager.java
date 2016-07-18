package com.itheima.redbaby.ui.manager;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.ui.MyCountView;
import com.itheima.redbaby.ui.MyNewAddress;
import com.itheima.redbaby.ui.PaymentView;
import com.itheima.redbaby.ui.Search;
import com.itheima.redbaby.ui.SearchResult;
import com.itheima.redbaby.ui.SearchView;

/**
 * 标题管理类
 * 
 * @author zhangyun
 * 
 */
public class TitleManager implements Observer {

	private static final String TAG = "TitleManager";
	// 单例TitleManager
	private static TitleManager titleManager = new TitleManager();

	private TitleManager() {
	}

	public static TitleManager getinstance() {
		return titleManager;
	}

	/**
	 * 首页的title
	 */
	private LinearLayout homeTitle;
	/**
	 * 搜索的title
	 */
	private LinearLayout searchTitle;
	/**
	 * 一般的title
	 */
	private LinearLayout commonTitle;

	/**
	 * 购物车标题 fun
	 */
	private LinearLayout shoppingTitle;
	/**
	 * 购物车标题删除 fun 2014-04-18 17:22:28
	 */
	private LinearLayout shoppingDelete;

	/**
	 * 购物车标题返回 fun 2014-04-18 17:22:34
	 */
	private LinearLayout shoppingGoBack;
	/**
	 * 购物车标题文字 fun 2014-04-18 17:22:34
	 */
	private TextView shoppingTitleTv;

	/**
	 * 一般title中的头文字
	 */
	private TextView tv_title;
	/**
	 * 替班title中的checkbox
	 */
	private ImageView iv_checkbox;
	private ImageView iv_delete;
	private ImageView dl_iv_left;
	private ImageView dl_iv_rigth;
	private EditText dl_search_title_ed;
	private ImageView dl_search_my_title_img;
	private OnSearchImageClickListener onSearchImageClickListener;
	private EditText dl_home_search_title_ed;

	/**
	 * 头部初始化
	 * 
	 * @param activity
	 */
	public void init(Activity activity) {
		homeTitle = (LinearLayout) activity.findViewById(R.id.dl_rl_home_title);
		searchTitle = (LinearLayout) activity.findViewById(R.id.dl_rl_search_title);
		commonTitle = (LinearLayout) activity.findViewById(R.id.ir_common_title);
		// 购物车头信息 fun 2014-04-17 14:13:33
		shoppingTitle = (LinearLayout) activity.findViewById(R.id.ir_shopping_title);
		shoppingDelete = (LinearLayout) activity.findViewById(R.id.shopping_cart_delete_layout);
		// 增加购物车头功能 fun 2014-04-18 17:24:24
		shoppingGoBack = (LinearLayout) activity.findViewById(R.id.id_shopping_cart_goback);
		shoppingTitleTv = (TextView) activity.findViewById(R.id.shopping_cart_title_tv);

		initTitle();
		tv_title = (TextView) commonTitle.findViewById(R.id.tv_alert);
		iv_delete = (ImageView) activity.findViewById(R.id.dl_iv_title_search_delete);
		dl_iv_left = (ImageView) commonTitle.findViewById(R.id.dl_iv_left);
		dl_iv_rigth = (ImageView) commonTitle.findViewById(R.id.dl_iv_rigth);
		dl_search_title_ed = (EditText) activity.findViewById(R.id.dl_search_title_ed);
		dl_search_my_title_img = (ImageView) activity.findViewById(R.id.dl_search_my_title_img);
		dl_home_search_title_ed = (EditText) activity.findViewById(R.id.dl_home_search_title_ed);
		/**
		 * 主页EditText点击事件
		 */
		dl_home_search_title_ed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("主页点击 ");
			}
		});

		dl_search_my_title_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onSearchImageClickListener.onImageClick();
			}
		});

		iv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dl_search_title_ed.setText("");
				System.out.println("delete");
			}
		});

		dl_search_title_ed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MiddleManager.getInstance().changeView(Search.class, null);
			}
		});
		/**
		 * 主界面EditText点击跳转到SearchResult
		 */
		dl_home_search_title_ed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MiddleManager.getInstance().changeView(SearchResult.class, null);
			}
		});

		dl_search_title_ed = (EditText) activity.findViewById(R.id.dl_search_title_ed);
		/**
		 * 设置搜索删除
		 */
		iv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dl_search_title_ed.setText("");
				MiddleManager.getInstance().changeView(SearchView.class, null);
			}

		});

	}

	public interface onClickDelete {
		void onClickDelete();
	}

	/**
	 * 初始化头部标签
	 */
	private void initTitle() {
		homeTitle.setVisibility(View.GONE);
		searchTitle.setVisibility(View.GONE);
		commonTitle.setVisibility(View.GONE);
		// 隐藏购物车标题 fun 2014-04-17 14:14:19
		shoppingTitle.setVisibility(View.GONE);
	}

	/**
	 * 显示首页的Title标题
	 */
	public void showHomeTitle() {
		initTitle();
		homeTitle.setVisibility(View.VISIBLE);
	}

	/**
	 * 显示搜索的Title
	 */
	public void showSearchTitle() {
		initTitle();
		searchTitle.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置普通类型的头信息
	 * 
	 * @param isShowLeft
	 * @param leftBg
	 * @param middleStr
	 * @param isShowRight
	 * @param rigthBg
	 */
	public void showCommonTitle(boolean isShowLeft, int leftBg, String middleStr, boolean isShowRight, int rigthBg, final OnImageClick onImageClick) {
		initTitle();
		commonTitle.setVisibility(View.VISIBLE);
		if (isShowLeft) {
			dl_iv_left.setVisibility(View.VISIBLE);
			dl_iv_left.setBackgroundResource(leftBg);
			dl_iv_left.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onImageClick != null) {
						onImageClick.onLeftClick();
					}
				}
			});
		} else {
			dl_iv_left.setVisibility(View.GONE);
		}

		if (StringUtils.isNotBlank(middleStr)) {
			tv_title.setText(middleStr);
		} else {
			tv_title.setText("");
		}

		if (isShowRight) {
			dl_iv_rigth.setVisibility(View.VISIBLE);
			dl_iv_rigth.setBackgroundResource(rigthBg);
			dl_iv_rigth.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onImageClick != null) {
						onImageClick.onRightClick();
					}
				}

				public void update(Observable observable, Object data) {
					// TODO Auto-generated method stub

				}
			});
		} else {
			dl_iv_rigth.setVisibility(View.GONE);
		}

	}

	public interface OnImageClick {
		/**
		 * 设置左边按钮的点击事件
		 * 
		 * @param leftImageView
		 */
		public void onLeftClick();

		/**
		 * 设置右边按钮的点击事件
		 * 
		 * @param rigthImageView
		 */
		public void onRightClick();

	}

	@Override
	public void update(Observable observable, Object data) {
		if (data != null && StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id) {
			case ConstantValue.VIEW_HOME:// 首页
				showHomeTitle();
				break;
			case ConstantValue.VIEW_SEARCH:// 搜索
				showSearchTitle();
				break;
			case ConstantValue.VIEW_BRAND:// 品牌
				showCommonTitle(true, R.drawable.dd_goback, "推荐品牌", false, R.drawable.check_default, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().goBack();
					}
				});
				break;
			case ConstantValue.VIEW_SHOPPINGCART:// 购物车
				initTitle();
				// fun 2014-04-17 14:23:36
				// showCommonTitle(true, R.drawable.icon, "购物车", true, 0, new
				// OnImageClick() {
				//
				// @Override
				// public void onRightClick() {
				// System.out.println("左边测试2");
				// }
				//
				// @Override
				// public void onLeftClick() {
				// System.out.println("右边测试2");
				// }
				// });
				shoppingTitle.setVisibility(View.VISIBLE);
				shoppingGoBack.setVisibility(View.INVISIBLE);
				shoppingTitleTv.setText("购物车");
				shoppingDelete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						BaseView currentUI = MiddleManager.getInstance().getCurrentUI();
						if (currentUI instanceof CartShoppingDelteProduct) {
							((CartShoppingDelteProduct) currentUI).deleteProduct();
						}
					}
				});

				break;
			/**
			 * 结算中心 fun 2014-04-18 15:24:37
			 */
			case ConstantValue.VIEW_PAYMENT:
				initTitle();
				shoppingTitle.setVisibility(View.VISIBLE);
				shoppingTitleTv.setText("结算中心");
				shoppingDelete.setVisibility(View.INVISIBLE);
				shoppingGoBack.setVisibility(View.VISIBLE);
				shoppingGoBack.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						BaseView currentUI = MiddleManager.getInstance().getCurrentUI();
						if (currentUI instanceof CartShoppingDelteProduct) {
							((CartShoppingDelteProduct) currentUI).goBack();
						}
					}
				});
				break;
			/**
			 * 结算中心 fun 2014-04-20 11:24:39
			 */
			case ConstantValue.VIEW_PAYMENT_SELECT:
				initTitle();
				shoppingTitle.setVisibility(View.VISIBLE);
				shoppingTitleTv.setText("结算信息");
				shoppingDelete.setVisibility(View.INVISIBLE);
				shoppingGoBack.setVisibility(View.VISIBLE);
				shoppingGoBack.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						BaseView currentUI = MiddleManager.getInstance().getCurrentUI();
						if (currentUI instanceof CartShoppingDelteProduct) {
							((CartShoppingDelteProduct) currentUI).goBack();
						}
					}
				});
				break;
			/**
			 * 账户中心
			 */
			case ConstantValue.MYCOUNTVIEW:
				// showCommonTitle(false, R.drawable.ic_launcher, "测试三", true,
				// R.drawable.check_default, new OnImageClick() {
				//
				// @Override
				// public void onRightClick() {
				// System.out.println("右边被点击了");
				// }
				//
				// @Override
				// public void onLeftClick() {
				//
				// }
				// });

				showCommonTitle(true, R.drawable.dd_goback, "我的账户", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLeftClick() {
						System.out.println("退出键被点击了");
						MiddleManager.getInstance().goBack();
					}
				});

				break;

			case ConstantValue.VIEW_ORDER_LIST:// 订单列表
				showCommonTitle(false, R.drawable.ic_launcher, "订单列表", false, R.drawable.check_default, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {

					}
				});
				break;
			case ConstantValue.SHOPPINGSPRING:// 限时抢购
				showCommonTitle(false, 0, "抢购列表", false, 0, null);
				break;

			case ConstantValue.NEWPRODUCT:// 限时抢购
				showCommonTitle(true, R.drawable.dd_goback, "新品首发", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().goBack();
					}
				});
				break;
			case ConstantValue.HOTPRODUCT:
				showCommonTitle(true, R.drawable.dd_goback, "热门单品", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {
						
					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().goBack();
					}
				});
				break;
			case ConstantValue.BRANDITEM:
				showCommonTitle(true, R.drawable.dd_goback, "品牌详情", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().goBack();
					}
				});
				break;

			case ConstantValue.FAVORITE:
				showCommonTitle(true, R.drawable.dd_mycount, "收藏夹", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().changeView(MyCountView.class, null);

					}
				});

				break;
			case ConstantValue.PROMOTIONCENTER:
			case ConstantValue.PROMOTIONITEM:
				showCommonTitle(true, R.drawable.dd_goback, "促销中心", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().goBack();
					}
				});
				break;
			case ConstantValue.ADDRESSLIST:// 地址列表
				showCommonTitle(false, 0, "地址列表", true, R.drawable.check_default, new OnImageClick() {
					// 新建地址按钮点击事件
					@Override
					public void onRightClick() {
						Bundle bundle = new Bundle();
						bundle.putSerializable("address", null);
						MiddleManager.getInstance().changeView(MyNewAddress.class, bundle);
						

					}

					@Override
					public void onLeftClick() {
						// TODO Auto-generated method stub

					}
				});
				break;
			case ConstantValue.NEWADDRESS:// 新建地址
				showCommonTitle(false, 0, "新建地址", true, R.drawable.check_default, new OnImageClick() {
					// 新建地址按钮点击事件
					@Override
					public void onRightClick() {
						BaseView currentUI = MiddleManager.getInstance().getCurrentUI();
						if (currentUI instanceof MyNewAddress) {
							((MyNewAddress) currentUI).myProcessEngine();

						}
					}

					@Override
					public void onLeftClick() {
						// TODO Auto-generated method stub

					}
				});
				break;
			case ConstantValue.UPDATEADDRESS:// 新建地址
				showCommonTitle(false, 0, "修改地址", true, R.drawable.check_default, new OnImageClick() {
					// 修改地址保存的按钮点击
					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						// TODO Auto-generated method stub

					}
				});
				break;
			case ConstantValue.GOODSENTRY:
				homeTitle.setVisibility(View.GONE);
				searchTitle.setVisibility(View.GONE);
				commonTitle.setVisibility(View.GONE);
				shoppingTitle.setVisibility(View.GONE);
				break;
			case ConstantValue.GOODSDETAILS:
				showCommonTitle(true, R.drawable.dd_goback, "商品详情", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().goBack();
					}
				});
				break;
			case ConstantValue.GOODSBIGMAP:
				showCommonTitle(true, R.drawable.dd_goback, "大图浏览", false, 0, new OnImageClick() {

					@Override
					public void onRightClick() {

					}

					@Override
					public void onLeftClick() {
						MiddleManager.getInstance().goBack();
					}
				});
				break;
			}
		}
	}

	public void setOnSearchImageClickListener(OnSearchImageClickListener onSearchImageClickListener) {
		this.onSearchImageClickListener = onSearchImageClickListener;
	}

	/**
	 * 更改购物车标题的头部 删除按钮显示 fun 2014-04-17 14:50:11
	 * 
	 * @param isShowDelete
	 */
	public void changeTitleDelete(boolean isShowDelete) {
		if (isShowDelete)
			shoppingDelete.setVisibility(View.VISIBLE);
		else
			shoppingDelete.setVisibility(View.INVISIBLE);
	}

	public interface OnSearchImageClickListener {
		void onImageClick();
	}

	/**
	 * EditText暴露出get，set方法 Jerry
	 * 
	 * @return
	 */
	public EditText getDl_search_title_ed() {
		return dl_search_title_ed;
	}

	public void setDl_search_title_ed(EditText dl_search_title_ed) {
		this.dl_search_title_ed = dl_search_title_ed;
	}

	/**
	 * 向外暴露搜索图片id的get方法
	 * 
	 * @return
	 */
	public ImageView getDl_search_my_title_img() {
		return dl_search_my_title_img;
	}

	/**
	 * 向外暴漏主页EditText组件get方法
	 */
	public EditText getDl_home_search_title_ed() {
		return dl_home_search_title_ed;
	}
}
