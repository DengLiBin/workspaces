package com.itheima.redbaby.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Invoice;
import com.itheima.redbaby.bean.PaymentInfo;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.order.AddressInfo;
import com.itheima.redbaby.bean.order.Order;
import com.itheima.redbaby.engine.PaymentEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.CartShoppingDelteProduct;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.ui.manager.ShoppingCart;
import com.itheima.redbaby.utils.BeanFactory;

public class PaymentView extends BaseView implements OnClickListener,
		CartShoppingDelteProduct {

	private ListView productList;
	private TextView newTotalPrice;
	private Button submit;

	private TextView address;
	private TextView payMethod;
	private TextView sendTime;
	private TextView invoiceTv;
	private TextView reduce;
	private TextView increase;

	private RelativeLayout paymentAddressSelect;
	private RelativeLayout paymentPayTypeSelect;
	private RelativeLayout paymentSendTimeSelect;
	private RelativeLayout paymentInvoiceSelect;
	private RelativeLayout paymentRemak;

	private TextView productNumber;
	private TextView productPoint;
	private TextView productPromCut;
	private TextView freight;

	private View listViewHead;
	private View listViewFoot;

	private String[] payMehodsStrs = { "货到付款-现金支付", "货到付款-pos机支", "支付宝-待定" };
	private String[] sendTimeStrs = { "只工作日送货", "双休日、假日送货", "工作日、双休日及假日均可送货" };

	private String[] colors = new String[] { "红色", "蓝色", "绿色" };
	private String[] daxiao = new String[] { "L", "K", "M" };

	private List<Product> products;
	private PaymentInfo paymentInfo;
	private Invoice invoice;
	private Order orderSubmit;

	private LinearLayout paymentSubmitOK;
	private TextView orderID;
	private TextView payMoney;
	private TextView payType;
	private TextView continueShopping;
	private TextView orderDetail;

	private LinearLayout payment;
	private LinearLayout paymentBottom;
	private boolean payFirst = true;
	private int total = 0;

	public PaymentView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_payment_center_layout,
				null);
		listViewHead = View.inflate(context, R.layout.dl_payment_center_head,
				null);
		listViewFoot = View.inflate(context, R.layout.dl_payment_center_foot,
				null);

	}

	@Override
	protected void findViewById() {
		productList = (ListView) middleView
				.findViewById(R.id.payment_product_list);
		newTotalPrice = (TextView) middleView
				.findViewById(R.id.payment_product_new_total_price);
		submit = (Button) middleView.findViewById(R.id.submit_order);

		address = (TextView) listViewHead.findViewById(R.id.payment_address);
		payMethod = (TextView) listViewHead
				.findViewById(R.id.payment_pay_method);
		sendTime = (TextView) listViewHead.findViewById(R.id.payment_sendTime);
		invoiceTv = (TextView) listViewHead.findViewById(R.id.payment_invoice);
		reduce = (TextView) listViewHead.findViewById(R.id.prom_reduce);
		increase = (TextView) listViewHead.findViewById(R.id.prom_increase);

		paymentAddressSelect = (RelativeLayout) listViewHead
				.findViewById(R.id.payment_address_rel);
		paymentPayTypeSelect = (RelativeLayout) listViewHead
				.findViewById(R.id.payment_payType_rel);
		paymentSendTimeSelect = (RelativeLayout) listViewHead
				.findViewById(R.id.payment_sendTime_rel);
		paymentInvoiceSelect = (RelativeLayout) listViewHead
				.findViewById(R.id.payment_invoice_rel);
		paymentRemak = (RelativeLayout) listViewHead
				.findViewById(R.id.payment_remark_rel);

		productNumber = (TextView) listViewFoot
				.findViewById(R.id.payment_product_number);
		productPoint = (TextView) listViewFoot
				.findViewById(R.id.payment_product_point);
		productPromCut = (TextView) listViewFoot
				.findViewById(R.id.payment_product_promCut);
		freight = (TextView) listViewFoot
				.findViewById(R.id.payment_product_freight);

		paymentSubmitOK = (LinearLayout) middleView
				.findViewById(R.id.payment_submit_ok);
		orderID = (TextView) middleView.findViewById(R.id.orderid_value_tv);
		payMoney = (TextView) middleView.findViewById(R.id.paymoney_value_tv);
		payType = (TextView) middleView.findViewById(R.id.paytype_value_tv);
		continueShopping = (TextView) middleView
				.findViewById(R.id.continue_shoping);
		orderDetail = (TextView) middleView.findViewById(R.id.to_order_detail);

		payment = (LinearLayout) middleView.findViewById(R.id.payment);
		paymentBottom = (LinearLayout) middleView
				.findViewById(R.id.payment_bottom);

	}

	@Override
	public void onResume() {
		payment.setVisibility(View.VISIBLE);
		paymentBottom.setVisibility(View.VISIBLE);
		paymentSubmitOK.setVisibility(View.GONE);

		if (bundle.getString("from") != null
				&& bundle.getString("from").equals("PaymentSelectView")) {
			invoice = (Invoice) bundle.getSerializable("Invoice");
			paymentInfo = (PaymentInfo) bundle.getSerializable("PaymentInfo");
			initData();
			invoiceTv.setText("发票类型：" + invoice.getType() + "\r\n发票抬头:"
					+ invoice.getTitle() + "\r\n发票内容：" + invoice.getContent());
		}
		
		if(bundle.getSerializable("address")!= null ){
			AddressInfo addressInfo = (AddressInfo) bundle.getSerializable("address");
			String addressStr = "姓名:\r\t" + addressInfo.getName() + "\r\n地址:\r\t"
					+ addressInfo.getAreaId()
					+ addressInfo.getAreaDetail();
			address.setText(addressStr);
		}
	}

	@Override
	protected void setListener() {
		submit.setOnClickListener(this);
		paymentAddressSelect.setOnClickListener(this);
		paymentPayTypeSelect.setOnClickListener(this);
		paymentSendTimeSelect.setOnClickListener(this);
		paymentInvoiceSelect.setOnClickListener(this);
		paymentRemak.setOnClickListener(this);
		continueShopping.setOnClickListener(this);
		orderDetail.setOnClickListener(this);
	}

	@Override
	protected void processEngine() {
		new MyHttpTask<Void>() {

			@Override
			protected PaymentInfo doInBackground(Void... params) {
//				PaymentEngine engin = BeanFactory.getFactory().getInstance(
//						PaymentEngine.class);
//				PaymentInfo info = engin.getPaymentInfo(context);
//				return info;
				
				PaymentInfo info = new PaymentInfo();
				info.setDelivery_info("2");
				info.setPayment_info("1");
				
				List<Product> prods = ShoppingCart.getInstance().getCart();
				
				
				for(Product product : prods){
					float price = product.getPrice();
					
					total = (int) (total + price);
				}
				total = total-20;
				info.setProductlist(ShoppingCart.getInstance().getCart());
				info.setCheckout_prom(new String[]{"你买我就送！！！","充一百送一百"});
				Map<String,String> checkout_addup = new HashMap<String, String>();
				checkout_addup.put("totalCount", ShoppingCart.getInstance().getCart().size()+"");
				checkout_addup.put("freight", "10");
				checkout_addup.put("totalPrice", total+"");
				checkout_addup.put("totalPoint", total+"");
				checkout_addup.put("freight", "10");
				checkout_addup.put("promCut", "20");
				info.setCheckout_addup(checkout_addup);
				AddressInfo addressInfo = new AddressInfo();
				addressInfo.setId("111");
				addressInfo.setName("肖文");
				addressInfo.setAreaId("北京市海淀区");
				addressInfo.setAreaDetail("闵庄路3号");
				
				info.setAddressInfo(addressInfo);
				
				return info;
				
				
			}

			@Override
			protected void onPostExecute(Object result) {
				paymentInfo = (PaymentInfo) result;
				initData();
			}

		}.executeProxy();
		

	}

	private void initData() {
		AddressInfo addressInfo = paymentInfo.getAddressInfo();
		String addressStr = "姓名:\r\t" + addressInfo.getName() + "\r\n地址:\r\t"
				+ addressInfo.getAreaId()
				+ addressInfo.getAreaDetail();
		address.setText(addressStr);
		int method = Integer.parseInt(paymentInfo.getPayment_info());
		payMethod.setText(payMehodsStrs[method - 1]);
		int time = Integer.parseInt(paymentInfo.getDelivery_info());
		sendTime.setText(sendTimeStrs[time - 1]);
		reduce.setText(paymentInfo.getCheckout_prom()[0]);
		increase.setText(paymentInfo.getCheckout_prom()[1]);
		

		productNumber
				.setText(paymentInfo.getCheckout_addup().get("totalCount"));
		productPoint.setText(paymentInfo.getCheckout_addup().get("totalPoint"));
		productPromCut.setText("￥"
				+ paymentInfo.getCheckout_addup().get("promCut"));
		freight.setText("￥" + paymentInfo.getCheckout_addup().get("freight"));
		if(payFirst){
		productList.addHeaderView(listViewHead);
		productList.addFooterView(listViewFoot);
			payFirst = false;
		}
		newTotalPrice.setText("￥"
				+ paymentInfo.getCheckout_addup().get("totalPrice"));

		products = paymentInfo.getProductlist();

		productList.setAdapter(new ProductAdapter());
	}

	@Override
	protected int getID() {
		return ConstantValue.VIEW_PAYMENT;
	}

	private class ProductAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return products.size();
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

			CartViewHolder holder;
			if (convertView == null) {
				holder = new CartViewHolder();
				convertView = View.inflate(context,
						R.layout.dl_payment_center_product_item, null);

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
	
				convertView.setTag(holder);
			} else {
				holder = (CartViewHolder) convertView.getTag();
			}

			imageLoader.displayImage(ConstantValue.URI
					+ products.get(position).getPic(), holder.ivIcon);
			
			holder.tvTitle.setText(products.get(position).getName());

			holder.tvNumber.setText("1");
//			holder.tvNumber.setText(products.get(position).getNumber() + "");

//			holder.tvSize.setText(products.get(position).getProduct_propertys()
//					.get("大小"));
//
//			holder.tvColor.setText(products.get(position)
//					.getProduct_propertys().get("颜色"));
//			int index = Integer.parseInt(products.get(position).getProduct_propertys()
//					.get("大小"))-4;
			holder.tvSize.setText("M");
//			int index1 = Integer.parseInt(products.get(position).getProduct_propertys()
//					.get("颜色"))-1;
			holder.tvColor.setText("红色");

			float price = products.get(position).getPrice();
			holder.price.setText(price + "");

			return convertView;
		}
	}

	private static class CartViewHolder {
		private ImageView ivIcon;
		private TextView tvTitle;
		private TextView tvNumber;
		private TextView tvSize;
		private TextView tvColor;
		private TextView price;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit_order:
			payment.setVisibility(View.GONE);
			paymentBottom.setVisibility(View.GONE);
			paymentSubmitOK.setVisibility(View.VISIBLE);
			submitOrder();
			ShoppingCart.getInstance().getCart().clear();
			break;

		case R.id.payment_address_rel:
			bundle.putString("from", "address");
			MiddleManager.getInstance().changeView(MyAddressList.class, bundle);
			break;

		case R.id.payment_payType_rel:
			bundle.putString("from", "payType");
			bundle.putSerializable("PaymentInfo", paymentInfo);
			MiddleManager.getInstance().changeView(PaymentSelectView.class,
					bundle);
			break;

		case R.id.payment_sendTime_rel:
			bundle.putString("from", "sendTime");
			bundle.putSerializable("PaymentInfo", paymentInfo);
			MiddleManager.getInstance().changeView(PaymentSelectView.class,
					bundle);
			break;

		case R.id.payment_invoice_rel:
			bundle.putString("from", "invoice");
			bundle.putSerializable("PaymentInfo", paymentInfo);
			MiddleManager.getInstance().changeView(PaymentSelectView.class,
					bundle);
			break;

		case R.id.continue_shoping:
			MiddleManager.getInstance().changeView(GoodsEntryView.class, null);
			break;
		case R.id.to_order_detail:
			MiddleManager.getInstance().changeView(OrderContentView.class, null);
			break;

		}
	}

	private void submitOrder() {
		new MyHttpTask<Void>() {

			@Override
			protected Order doInBackground(Void... params) {
//				PaymentEngine engin = BeanFactory.getFactory().getInstance(
//						PaymentEngine.class);
//				Order order = engin.submitOrder(context,paymentInfo,invoice);
//				return order;
				Order order = new Order();
				order.setPrice(String.valueOf(total));
				order.setOrderid("201404211029374845");
				return order;
			}

			@Override
			protected void onPostExecute(Object result) {
				orderSubmit =  (Order) result;
				initOrder();
			}

		}.executeProxy();
	}

	protected void initOrder() {
		orderID.setText(orderSubmit.getOrderid());
		payMoney.setText(orderSubmit.getPrice());
		int payTypeInt = Integer.parseInt(paymentInfo.getPayment_info());
		payType.setText(payMehodsStrs[payTypeInt - 1]);
	}

	/**
	 * 结算中心无用
	 */
	@Override
	public void deleteProduct() {

	}

	/**
	 * 返回 fun 2014-04-18 17:36:12
	 */
	@Override
	public void goBack() {
		MiddleManager.getInstance().changeView(ShoppingView.class, null);
	}
}
