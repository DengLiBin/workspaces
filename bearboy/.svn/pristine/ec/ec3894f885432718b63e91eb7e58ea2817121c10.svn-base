package com.itheima.redbaby.ui;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.order.AddressInfo;
import com.itheima.redbaby.bean.order.Delivery;
import com.itheima.redbaby.bean.order.Invoice;
import com.itheima.redbaby.bean.order.Logistic;
import com.itheima.redbaby.bean.order.LogisticItem;
import com.itheima.redbaby.bean.order.Order;
import com.itheima.redbaby.bean.order.OrderAttribute;
import com.itheima.redbaby.bean.order.OrderDetail;
import com.itheima.redbaby.bean.order.OrderProduct;
import com.itheima.redbaby.bean.order.Payment;
import com.itheima.redbaby.engine.DeleteOrderEngine;
import com.itheima.redbaby.engine.LogisticEngine;
import com.itheima.redbaby.engine.OrderDetailEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 订单详细列表
 * 
 * @author my
 * 
 */
public class OrderContentView extends BaseView {

	private static final String TAG = "OrderContentView";
	/**
	 * 传过来的商品
	 */
	private Order order;
	/**
	 * 商品总额
	 */
	private TextView tv_totalmoney;
	/**
	 * 商品金额
	 */
	private TextView tv_money;
	/**
	 * 返现
	 */
	private TextView tv_backmoney;
	/**
	 * 运费
	 */
	private TextView tv_fir;
	/**
	 * 用户id
	 */
	private TextView tv_name;
	/**
	 * 用户电话
	 */
	private TextView tv_phone;
	/**
	 * 地址
	 */
	private TextView tv_address;
	/**
	 * 支付方式
	 */
	private TextView tv_paytype;
	/**
	 * 配送方式
	 */
	private TextView tv_sendtype;
	/**
	 * 删除订单
	 */
	private Button btn_delete;

	/**
	 * 商品列表
	 */
	private ListView lv_pro_list;
	/**
	 * listView适配器
	 */
	private MyAdapter adapter;

	public OrderContentView(Context context) {
		super(context);

	}

	@Override
	public void onResume() {
		order = (Order) bundle.get("order");

	}

	/**
	 * 将order的详细信息放入layout中
	 */

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_order_content_item, null);

	}

	@Override
	protected void findViewById() {
		tv_totalmoney = (TextView) middleView.findViewById(R.id.dl_tv_order_content_totalmoney);
		tv_money = (TextView) middleView.findViewById(R.id.dl_tv_order_content_money);
		tv_backmoney = (TextView) middleView.findViewById(R.id.dl_tv_order_content_backmoney);
		tv_fir = (TextView) middleView.findViewById(R.id.dl_tv_order_content_fir);
		tv_name = (TextView) middleView.findViewById(R.id.dl_tv_order_content_name);
		tv_phone = (TextView) middleView.findViewById(R.id.dl_tv_order_content_phone);
		tv_address = (TextView) middleView.findViewById(R.id.dl_tv_content_address);
		tv_paytype = (TextView) middleView.findViewById(R.id.dl_tv_content_paytype);
		tv_sendtype = (TextView) middleView.findViewById(R.id.dl_tv_order_content_sendtype);
		btn_delete = (Button) middleView.findViewById(R.id.dl_btn_delete_order);

		tv_statue = (TextView) middleView.findViewById(R.id.dl_tv_order_content_status);
		tv_orderid = (TextView) middleView.findViewById(R.id.dl_tv_order_content_orderid);
		tv_send_day = (TextView) middleView.findViewById(R.id.dl_tv_content_send_day);
		lv_pro_list = (ListView) middleView.findViewById(R.id.dl_lv_pro_list);
		tv_invoice_title = (TextView) middleView.findViewById(R.id.dd_tv_invoice_title);
		tv_invoice_content = (TextView) middleView.findViewById(R.id.dd_tv_content_invoice);
		btn_find = (Button) middleView.findViewById(R.id.dl_btn_order_content_item);

	}

	@Override
	protected void setListener() {
		
		lv_pro_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bundle bunder = new Bundle();
				bunder.putString("id", "4");
				MiddleManager.getInstance().changeView(GoodsDetailsView.class,bunder );
			}
		});

		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 删除订单
				System.out.println("删除订单");
				new MyHttpTask<String>() {

					@Override
					protected Object doInBackground(String... params) {
						DeleteOrderEngine deleteOrderEngine = BeanFactory.getFactory().getInstance(DeleteOrderEngine.class);
						return deleteOrderEngine.deleteOrder(params[0]);
					}

					@Override
					protected void onPostExecute(Object result) {
						if (result != null) {
							PromptManager.showToast(context, "删除成功！");
							//TODO:
							bundle.putSerializable("detail", detail);
							MiddleManager.getInstance().changeView(OrderView.class, bundle);
						}
						super.onPostExecute(result);
					}
				}.executeProxy(detail.getOrder_info().getOrderid());
			}
		});

		btn_find.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("物流查询");
				new MyHttpTask<String>() {

					@Override
					protected Object doInBackground(String... params) {
						LogisticEngine engine = BeanFactory.getFactory().getInstance(LogisticEngine.class);

						return engine.getLogisticMsg(params[0]);
					}

					protected void onPostExecute(Object result) {
						if (result != null) {
							Logistic logistic = (Logistic) result;
							LogisticItem logistics = logistic.getLogistics();
							bundle.putSerializable("logistics", logistics);
							MiddleManager.getInstance().changeView(LogisticView.class, bundle);

						}
					};
				}.executeProxy(detail.getOrder_info().getOrderid());
			}
		});

		lv_pro_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				OrderProduct orderProduct = productList.get(position);
				// TODO
			}
		});
	}

	/**
	 * 
	 */
	private OrderDetail detail;
	/**
	 * 订单状态
	 */
	private TextView tv_statue;
	/**
	 * 订单id
	 */
	private TextView tv_orderid;
	private TextView tv_send_day;
	private TextView tv_invoice_title;
	private TextView tv_invoice_content;
	private List<OrderProduct> productList;

	@Override
	protected void processEngine() {

		new MyHttpTask<String>() {

			@Override
			protected Object doInBackground(String... params) {
				@SuppressWarnings("static-access")
				OrderDetailEngine detailEngine = BeanFactory.getFactory().getInstance(OrderDetailEngine.class);
				return detailEngine.getOrderDetailMsg(params[0]);
			}

			protected void onPostExecute(Object result) {
				detail = (OrderDetail) result;
				// 准备数据

				putData();

			}

		}.executeProxy("123");

	}

	private float price = 0.0f;
	private Button btn_find;

	private void putData() {
		Order order_info = detail.getOrder_info();
		tv_statue.setText("订单状态：" + order_info.getStatus());
		tv_orderid.setText("订单ID:" + order_info.getOrderid());
		if ("已取消".equals(order_info.getStatus())) {
			// 已取消
			btn_delete.setVisibility(View.INVISIBLE);
			btn_delete.setVisibility(View.INVISIBLE);
		} else {
			btn_delete.setVisibility(View.VISIBLE);
			btn_delete.setVisibility(View.VISIBLE);
		}
		AddressInfo address = detail.getAddress_info();
		// 名字
		tv_name.setText(address.getName());
		// 地址
		tv_address.setText("地址：" + address.getAreaId() + " " + address.getAreaDetail() + "");
		Payment payment_info = detail.getPayment_info();
		// 支付方式
		tv_paytype.setText(payment_info.getType());
		// 送货日期
		Delivery delivery_info = detail.getDelivery_info();
		tv_send_day.setText("送货日期：" + delivery_info.getType());
		// 发票抬头
		Invoice invoice_info = detail.getInvoice_info();
		tv_invoice_title.setText("发票抬头：" + invoice_info.getTitle());

		// 发票地址
		tv_invoice_content.setText("发票地址：" + invoice_info.getContent());

		productList = detail.getProductlist();

		adapter = new MyAdapter();
		lv_pro_list.setAdapter(adapter);

		tv_totalmoney.setText(String.valueOf(price));
		tv_money.setText(String.valueOf(price));

	};

	@Override
	protected int getID() {
		return ConstantValue.VIEW_ORDER_LIST;
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return productList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Viewholder viewholder = null;
			if (convertView == null) {
				viewholder = new Viewholder();
				convertView = View.inflate(context, R.layout.dl_order_content_pro_item, null);
				viewholder.tv_pro_name = (TextView) convertView.findViewById(R.id.dl_tv_order_content_pro_name);
				viewholder.tv_pro_number = (TextView) convertView.findViewById(R.id.dl_tv_order_content_pro_number);
				viewholder.tv_pro_price = (TextView) convertView.findViewById(R.id.dl_tv_order_content_pro_price);
				viewholder.tv_pro_attr = (TextView) convertView.findViewById(R.id.dl_tv_order_content_pro_attr);
				viewholder.tv_uplimit = (TextView) convertView.findViewById(R.id.dl_tv_order_content_pro_uplimit);
				viewholder.tv_isgift = (TextView) convertView.findViewById(R.id.dl_tv_order_content_pro_gift);
				viewholder.iv_icon = (ImageView) convertView.findViewById(R.id.dl_iv_order_content_url);
				convertView.setTag(viewholder);
			} else {
				viewholder = (Viewholder) convertView.getTag();
			}
			// 商品名字
			viewholder.tv_pro_name.setText("商品名：" + productList.get(position).getName());
			// 商品数量
			viewholder.tv_pro_number.setText("商品数量：1个");
			// 商品价格
			viewholder.tv_pro_price.setText("商品价格:" + productList.get(position).getPrice());
			price += Float.parseFloat(productList.get(position).getPrice());
			// 商品属性
			List<OrderAttribute> product_property = productList.get(position).getProduct_property();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("商品属性： ");
			for (OrderAttribute orderAttribute : product_property) {
				String key = orderAttribute.getKey();
				String value = orderAttribute.getValue();
				stringBuffer.append(key + ":" + value + " ");

			}
			viewholder.tv_pro_attr.setText(stringBuffer.toString());
			// 上限
			viewholder.tv_uplimit.setText("上限：" + productList.get(position).getUplimit());
			// 是否为赠品
			viewholder.tv_isgift.setText("是否为赠品：" + productList.get(position).getIsgift());
			imageLoader.displayImage(ConstantValue.URI + productList.get(position).getPic(), viewholder.iv_icon, options);
			return convertView;
		}

	}

	class Viewholder {
		TextView tv_pro_name;
		TextView tv_pro_number;
		TextView tv_pro_price;
		TextView tv_pro_attr;
		TextView tv_uplimit;
		TextView tv_isgift;
		ImageView iv_icon;

	}

}
