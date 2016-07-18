package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.GloableParameters;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Cart;
import com.itheima.redbaby.bean.CartItem;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.CartShopppingEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.CartShoppingDelteProduct;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.ui.manager.ShoppingCart;
import com.itheima.redbaby.ui.manager.TitleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.Logger;

/**
 * 购物车
 * 
 * @author Administrator
 * 
 */
public class ShoppingView extends BaseView implements OnClickListener,
		CartShoppingDelteProduct {

	private Button goShopping;

	private LinearLayout noData;
	private ListView cartList;
	private TextView countPrice;
	private TextView countPoint;
	private TextView increase;
	private TextView reduce;
	private ToggleButton selectAll;
	private Button payBtn;
	private RelativeLayout priceItem;
	private RelativeLayout nogoodsRl;
	private View cartHead;

	private List<CartItem> cartItems;
	private Cart cart;

	private CartAdapter cartAdapter;
	private boolean isFirst = true;
	private String[] colors = new String[] { "红色", "蓝色", "绿色" };
	private String[] daxiao = new String[] { "L", "K", "M" };

	public ShoppingView(Context context) {
		super(context);
	}

	private View noGoods;

	@Override
	protected void loadMiddleLayout() {
		cartHead = View.inflate(context, R.layout.dl_shopping_cart_header_prom,
				null);

		middleView = View.inflate(context, R.layout.dl_shopping_cart_layout,
				null);
		noGoods = View.inflate(context, R.layout.dl_shopping_cart_no_goods,
				null);
	}

	@Override
	protected void findViewById() {
		goShopping = (Button) middleView
				.findViewById(R.id.dl_cart_no_data_forward_cuxiao);

		noData = (LinearLayout) middleView
				.findViewById(R.id.dl_shopping_cart_no_data);
		cartList = (ListView) middleView
				.findViewById(R.id.dl_shopping_cart_list);
		countPrice = (TextView) middleView
				.findViewById(R.id.dl_cart_count_price_tv);
		selectAll = (ToggleButton) middleView
				.findViewById(R.id.dl_cart_select_all_button);
		increase = (TextView) cartHead.findViewById(R.id.prom_increase);
		reduce = (TextView) cartHead.findViewById(R.id.prom_reduce);
		countPoint = (TextView) middleView
				.findViewById(R.id.dl_cart_total_point_tv);
		payBtn = (Button) middleView
				.findViewById(R.id.dl_cart_settle_accounts_but);

		priceItem = (RelativeLayout) middleView
				.findViewById(R.id.dl_shopping_cart_price);

		nogoodsRl = (RelativeLayout) noGoods
				.findViewById(R.id.cart_shoppong_no_good);

		cartList.setDividerHeight(0);
	}

	@Override
	protected void setListener() {
		goShopping.setOnClickListener(this);
		selectAll.setOnClickListener(this);
		payBtn.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		processEngine();
	}

	@Override
	protected void processEngine() {
		// 查询数据库，拿数据

		final List<Product> shoppingList = ShoppingCart.getInstance().getCart();
		if (shoppingList.size() == 0) {
			priceItem.setVisibility(View.GONE);
			noData.setVisibility(View.VISIBLE);
			cartList.setVisibility(View.GONE);
			TitleManager.getinstance().changeTitleDelete(false);
			return;
		} else {
			TitleManager.getinstance().changeTitleDelete(true);
			priceItem.setVisibility(View.VISIBLE);
			noData.setVisibility(View.GONE);
			cartList.setVisibility(View.VISIBLE);

			new MyHttpTask<Void>() {

				@Override
				protected Cart doInBackground(Void... params) {
					// CartShopppingEngine engin = BeanFactory.getFactory()
					// .getInstance(CartShopppingEngine.class);
					// Cart cart = engin.getCart(context, shoppingList);
					// return cart;
					List<Product> productList = ShoppingCart.getInstance()
							.getCart();
					System.out.println("productList"+productList.size());
					if(productList.size()<=0){
						Toast.makeText(context, "数据异常···请稍后重试", 0).show();
					}
					Cart productCart = new Cart();
					List<CartItem> itemList = new ArrayList<CartItem>();
					for (Product product : productList) {
						CartItem item = new CartItem();
						item.setCheck(true);
						item.setProdNum("1");
						product.setUplimit(10);
						item.setProduct(product);
						itemList.add(item);
					}
					productCart.setCartItems(itemList);
					return productCart;
				}

				@Override
				protected void onPostExecute(Object result) {
					cart = (Cart) result;
					initData();
				}
			}.executeProxy();
		}
		
	}

	protected void initData() {
		cartItems = cart.getCartItems();
		initGoods();
		if (isFirst) {
			cartList.addHeaderView(cartHead);
			isFirst = false;
		}
		cartAdapter = new CartAdapter();
		cartList.setAdapter(cartAdapter);
		cartList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int newPosotion = position - 1;
				String idStr = "";
				if (position == 0) {
					return;
				}
				// if (newPosotion < have.size()) {
				// idStr = have.get(newPosotion).getProduct().getId();
				//
				// return;
				// } else {
				// newPosotion = position - have.size() - 2;
				// idStr = havent.get(newPosotion).getProduct().getId();
				// }
				idStr = have.get(newPosotion).getProduct().getId();
				if (!StringUtils.isEmpty(idStr)) {
					bundle.putString("id", idStr);
					MiddleManager.getInstance().changeView(
							GoodsDetailsView.class, bundle);
				}
			}
		});
		cartList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				int newPosotion = position - 1;
				if (position == 0) {
					return true;
				}
				if (newPosotion < have.size()) {
					have.get(newPosotion).setCheck(
							!have.get(newPosotion).isCheck());
				} else if (newPosotion == have.size()) {
					return true;
				} else {
					newPosotion = position - have.size() - 2;
					havent.get(newPosotion).setCheck(
							!havent.get(newPosotion).isCheck());
				}
				cartAdapter.notifyDataSetChanged();
				changePrice();
				initSelectAll();
				return true;
			}
		});

		cartList.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					x = (int) event.getX();
					y = (int) event.getY();
					position = cartList.pointToPosition(x, y);
					break;

				case MotionEvent.ACTION_UP:
					int numberChange = 0;
					moveX = (int) (event.getX() - x);
					moveY = (int) (event.getY() - y);
					if (moveX > 100) {
						numberChange = 1;
					} else if (moveX < -100) {
						numberChange = -1;
					}

					int newPosotion = position - 1;

					if (position <= 0) {
						return true;
					}
					// if (newPosotion < have.size()) {
					// int buyNumer = Integer.parseInt(have.get(newPosotion)
					// .getProdNum());
					// int canBuy = buyNumer + numberChange;
					// if (canBuy <= 0) {
					// have.remove(newPosotion);
					// cartAdapter.notifyDataSetChanged();
					// changePrice();
					// return true;
					// } else if (canBuy > have.get(newPosotion).getProduct()
					// .getUplimit()) {
					// Toast.makeText(context, "你购买的数量已达上限·····",
					// Toast.LENGTH_SHORT).show();
					// return true;
					// }
					// have.get(newPosotion).setProdNum(
					// String.valueOf(buyNumer + numberChange));
					//
					// } else if (newPosotion == have.size()) {
					// return true;
					// } else {
					// newPosotion = position - have.size() - 2;
					// int buyNumer = Integer.parseInt(havent.get(newPosotion)
					// .getProdNum());
					// System.out.println(position);
					// int canBuy = buyNumer + numberChange;
					// if (canBuy <= 0) {
					// havent.remove(newPosotion);
					// cartAdapter.notifyDataSetChanged();
					// changePrice();
					// return true;
					// } else if (canBuy > havent.get(newPosotion)
					// .getProduct().getUplimit()) {
					// Toast.makeText(context, "你购买的数量已达上限·····",
					// Toast.LENGTH_SHORT).show();
					// return true;
					// }
					// havent.get(newPosotion).setProdNum(
					// String.valueOf(buyNumer + numberChange));
					// }
					System.out.println(numberChange+"numberChange");
					if (numberChange != 0) {
						System.out.println(newPosotion);
						int buyNumer = Integer.parseInt(have.get(newPosotion)
								.getProdNum());
						System.out.println("buynumber" + buyNumer);
						int canBuy = 10 - buyNumer;
						if (canBuy <= 0&&numberChange>0) {
							Toast.makeText(context, "你购买的数量已达上限·····",
									Toast.LENGTH_SHORT).show();
							return true;
						} else if (canBuy >= 10&&numberChange<0) {
							have.remove(newPosotion);
							ShoppingCart.getInstance().getCart()
									.remove(newPosotion);
//							cartAdapter.notifyDataSetChanged();
//							changePrice();
//							initSelectAll();
							return true;
						}

						 have.get(newPosotion).setProdNum(
						 String.valueOf(buyNumer + numberChange));
						 cartAdapter.notifyDataSetChanged();
						 changePrice();
						 initSelectAll1();
					}

					break;

				}
				return false;
			}
		});

		initGoods();
		changePrice();
		initSelectAll();
		priceItem.setVisibility(View.VISIBLE);
		initBackground();
		// reduce.setText(cart.getProm()[0]);
		// increase.setText(cart.getProm()[1]);
		reduce.setText("你买我就送！！！");
		increase.setText("充一百送一百");
	}

	private int x = 0;
	private int y = 0;
	private int moveX = 0;
	private int moveY = 0;
	private int position;
	private List<CartItem> have;
	private List<CartItem> havent;

	private void initGoods() {
		have = new ArrayList<CartItem>();
		havent = new ArrayList<CartItem>();
		for (CartItem item : cartItems) {
			// if (item.getProduct().getNumber() > 0) {
			// have.add(item);
			// } else {
			// havent.add(item);
			// }
			have.add(item);
		}
	}

	private void changePrice() {
		int totalPrice = 0;
		for (CartItem cartItem : have) {
			if (cartItem.isCheck()) {
				float price = cartItem.getProduct().getPrice();
				int prodNum = Integer.parseInt(cartItem.getProdNum());
				totalPrice += price * prodNum;
			}
		}

		countPrice.setText("总价：" + totalPrice);
		countPoint.setText("积分：" + totalPrice);
	}

	private void initSelectAll1(){
		int length = ShoppingCart.getInstance().getCart().size();
		if(length<=0){
			TitleManager.getinstance().changeTitleDelete(false);
			priceItem.setVisibility(View.GONE);
			noData.setVisibility(View.VISIBLE);
			cartList.setVisibility(View.GONE);
		}else{
			TitleManager.getinstance().changeTitleDelete(true);
			priceItem.setVisibility(View.VISIBLE);
			noData.setVisibility(View.GONE);
			cartList.setVisibility(View.VISIBLE);
			payBtn.setText("结算(" + length + ")");
		}
	}
	
	private void initSelectAll() {
		int count = 0;
		for (CartItem cartItem : have) {
			if (cartItem.isCheck()) {
				count++;
			}
		}
		if (count > 0) {
			selectAll.setChecked(true);
			payBtn.setEnabled(true);
			payBtn.setText("结算(" + count + ")");
			TitleManager.getinstance().changeTitleDelete(true);
		} else {
			selectAll.setChecked(false);
			payBtn.setEnabled(false);
			TitleManager.getinstance().changeTitleDelete(false);
			payBtn.setText("结算");
		}
		int i = 0;
		for (CartItem cartItem : cartItems) {
			if (cartItem.isCheck()) {
				i++;
			}
		}
		if (i > 0) {

			TitleManager.getinstance().changeTitleDelete(true);
		} else {

			TitleManager.getinstance().changeTitleDelete(false);
		}
	}

	@Override
	protected int getID() {
		return ConstantValue.VIEW_SHOPPINGCART;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// 去选购商品
		case R.id.dl_cart_no_data_forward_cuxiao:
			MiddleManager.getInstance().changeView(GoodsEntryView.class, null);
			break;

		case R.id.dl_cart_select_all_button:
			int count = 0;
			List<CartItem> items = cart.getCartItems();
			for (CartItem cartItem : items) {
				if (cartItem.isCheck()) {
					count++;
				}
			}
			if (count == items.size()) {
				for (CartItem cartItem : items) {
					cartItem.setCheck(false);
				}
			} else {
				for (CartItem cartItem : items) {
					selectAll.setChecked(true);
					cartItem.setCheck(true);
				}
			}
			initSelectAll();
			cartAdapter.notifyDataSetChanged();
			changePrice();

			break;
		case R.id.dl_cart_settle_accounts_but:
			if (GloableParameters.USER_ID <= 0) {
				MiddleManager.getInstance().changeView(MyCountView.class, null);
				Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
				return;
			}

			MiddleManager.getInstance().changeView(PaymentView.class, null);
			break;
		}
	}

	private class CartAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return have.size();
			// return have.size() + 1 + havent.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			final CartViewHolder holder;
			if (convertView == null || !(convertView instanceof LinearLayout)) {
				holder = new CartViewHolder();
				convertView = View.inflate(context,
						R.layout.dl_shopping_cart_product_item, null);

				holder.tbCheck = (ToggleButton) convertView
						.findViewById(R.id.dl_cart_select_item_button);
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.dl_shopping_cart_item_icon_iv);
				holder.tvTitle = (TextView) convertView
						.findViewById(R.id.dl_shopping_cart_item_title_tv);
				holder.tvNumber = (TextView) convertView
						.findViewById(R.id.dl_shopping_cart_item_number_tv);
				holder.tvSize = (TextView) convertView
						.findViewById(R.id.dl_shopping_cart_item_size_tv);
				holder.tvColor = (TextView) convertView
						.findViewById(R.id.dl_shopping_cart_item_color_tv);
				holder.price = (TextView) convertView
						.findViewById(R.id.dl_shopping_cart_item_price_tv);
				holder.itemPrice = (TextView) convertView
						.findViewById(R.id.dl_shopping_cart_item_itemprice_tv);
				convertView.setTag(holder);
			} else {
				holder = (CartViewHolder) convertView.getTag();
			}

			final CartItem item;
			if (position < have.size()) {
				item = have.get(position);
				convertView
						.setBackgroundResource(R.drawable.dd_cart_shopping_have);

			} else if (position == have.size()) {

				return noGoods;
			} else {
				convertView
						.setBackgroundResource(R.drawable.dd_cart_shopping_havent);
				int location = position - have.size() - 1;
				item = havent.get(location);
			}

			holder.tbCheck.setChecked(item.isCheck());
			holder.tbCheck.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					item.setCheck(holder.tbCheck.isChecked());
					initSelectAll();
					changePrice();
				}
			});

			imageLoader.displayImage(ConstantValue.URI
					+ item.getProduct().getPic(), holder.ivIcon);

			holder.tvTitle.setText(item.getProduct().getName());

			holder.tvNumber.setText(item.getProdNum());

			// int index =
			// Integer.parseInt(item.getProduct().getProduct_propertys().get("大小"))
			// - 4;
			holder.tvSize.setText("M");
			// int index1 = Integer.parseInt(item.getProduct()
			// .getProduct_propertys().get("颜色")) - 1;
			holder.tvColor.setText("红色");

			float price = item.getProduct().getPrice();
			int number = Integer.parseInt(item.getProdNum());
			holder.price.setText(price + "");

			holder.itemPrice.setText(price * number + "");

			return convertView;
		}
	}

	private static class CartViewHolder {
		private ToggleButton tbCheck;
		private ImageView ivIcon;
		private TextView tvTitle;
		private TextView tvNumber;
		private TextView tvSize;
		private TextView tvColor;
		private TextView price;
		private TextView itemPrice;
	}

	@Override
	public void deleteProduct() {
		if (cart == null) {
			return;
		}

		List<CartItem> keep = new ArrayList<CartItem>();
		for (CartItem item : have) {
			if (!item.isCheck()) {
				keep.add(item);
			}
		}
		for (CartItem item : havent) {
			if (!item.isCheck()) {
				keep.add(item);
			}
		}
		cartItems = keep;
		cart.setCartItems(cartItems);
		initGoods();

		cartAdapter.notifyDataSetChanged();
		initSelectAll();
		changePrice();
		initBackground();
		if (havent.size() < 1) {
			nogoodsRl.setVisibility(View.GONE);
		}

		List<CartItem> list = cart.getCartItems();
		List<Product> pro = new ArrayList<Product>();
		for (CartItem item : list) {
			pro.add(item.getProduct());
		}

		ShoppingCart.getInstance().setCart(pro);
	}

	private void initBackground() {
		if (cartItems.size() == 0) {
			priceItem.setVisibility(View.GONE);
			noData.setVisibility(View.VISIBLE);
			cartList.setVisibility(View.GONE);
		} else {
			priceItem.setVisibility(View.VISIBLE);
			noData.setVisibility(View.GONE);
			cartList.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void goBack() {
	}
}
