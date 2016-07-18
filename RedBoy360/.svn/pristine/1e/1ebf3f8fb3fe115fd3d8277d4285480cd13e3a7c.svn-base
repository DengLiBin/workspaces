package com.shopping.redboy.view;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Address;
import com.shopping.redboy.domain.Invoice;
import com.shopping.redboy.domain.Order;
import com.shopping.redboy.domain.OrderDetail;
import com.shopping.redboy.domain.OrderProd;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.engine.OrderDetailEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.PromptManager;
import com.shopping.redboy.util.myBaseAdapter;
import com.shopping.redboy.view.categoryDetail.ProductDetailView;

/**
 * 订单详情
 * 
 * @Description TODO
 * @author liang
 * @date 2014-4-17 下午9:43:33
 */

@ResID(id = R.layout.my_order_detail_activity)
@Category(id = R.id.imgHome, title = "订单详情", leftbutton = "我的订单", rightbutton = "")
public class OrderDetailView extends BaseView implements ButtonClickListener {
	@ResID(id=R.id.scroll)
	private ScrollView scroll;
	/*
	 * 物流查询
	 */
	@ResID(id = R.id.order_logistics_rel)
	private RelativeLayout order_logistics_rel;

	/*
	 * 订单收货信息
	 */
	// 订单号
	@ResID(id = R.id.order_number)
	private TextView order_number;
	// 收件人姓名
	@ResID(id = R.id.address_info_name)
	private TextView address_info_name;
	// 联系电话
	@ResID(id = R.id.address_info_phone)
	private TextView address_info_phone;
	// 收件人地址
	@ResID(id = R.id.address_info_address)
	private TextView address_info_address;

	/*
	 * 订单详情
	 */
	// 订单状态
	@ResID(id = R.id.order_info_status)
	private TextView order_info_status;
	// 支付方式
	@ResID(id = R.id.payment_info)
	private TextView payment_info;
	// 订单生成时间
	@ResID(id = R.id.order_info_time)
	private TextView order_info_time;
	// 发货时间
	@ResID(id = R.id.deliver_time)
	private TextView deliver_time;
	// 是否开具发票
	@ResID(id = R.id.invoice_info)
	private TextView invoice_info;
	// 发票抬头
	@ResID(id = R.id.invoice_info_title)
	private TextView invoice_info_title;
	// 送货要求
	@ResID(id = R.id.delivery_info)
	private TextView delivery_info;

	/*
	 * 商品清单
	 */
	@ResID(id = R.id.listdetail)
	private ListView listdetail;

	/*
	 * 总计
	 */
	// 商品数量总计
	@ResID(id = R.id.total_count)
	private TextView total_count;
	// 商品金额总计
	@ResID(id = R.id.total_price)
	private TextView total_price;
	// 商品积分总计
	@ResID(id = R.id.total_point)
	private TextView total_point;
	// 运费
	@ResID(id = R.id.freight)
	private TextView freight;
	// 促销减钱
	@ResID(id = R.id.prom_cut)
	private TextView prom_cut;

	/*
	 * 取消订单
	 */
	@ResID(id = R.id.textCancelOrder)
	private TextView textCancelOrder;
	
	//進度條
	@ResID(id=R.id.pb_load)
	private ProgressBar pb_load;

	private OrderDetail orderDetail;

	 private Order orderInfo;
	 private Address addressInfo;
	 private Invoice invoiceInfo;
	 private Map<String,String> checkoutMap;
	 private List<OrderProd> prodList;
	 private MyAdapter adapter;
	 
	public OrderDetailView(Context context) {
		super(context);
	}

	@Override
	protected void init() {		 
	}
	
	@Override
	public void onResume() {
		new MyAsynTask() {			
			@Override
			public void onPreExecute() {
				pb_load.setVisibility(View.VISIBLE);
				scroll.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void onPostExecute() {
				
				adapter = new MyAdapter(prodList, context, ViewHolder.class);
				listdetail.setAdapter(adapter);	
				fillData();
				pb_load.setVisibility(View.INVISIBLE);
				scroll.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void doInBackground() {
				OrderDetailEngine engine = BeanFactory.getImpl(OrderDetailEngine.class);
				orderDetail = engine.getOrderDetail();		
				 orderInfo = orderDetail.getOrderInfo();
				 addressInfo = orderDetail.getAddressInfo();
				 invoiceInfo = orderDetail.getInvoiceInfo();
				 checkoutMap=orderDetail.getCheckout_addup();
				 prodList=orderDetail.getProductInfo();
			}
		}.execute();
	}

	 private void fillData() {
		 //订单收货信息
		 order_number.setText(orderInfo.getOrderId());
		 address_info_name.setText(addressInfo.getName());
		 address_info_address.setText(addressInfo.getArea()+addressInfo.getAreadetail());
		 //订单详情
		 order_info_status.setText(orderInfo.getStatus());
		 //支付方式
		 switch (orderDetail.getPayment_info()) {
		 case 1:
			 payment_info.setText("货到付款");
		 break;
		 case 2:
			 payment_info.setText("货到POS机");
		 break;
		 case 3:
			 payment_info.setText("支付宝");
		 break;
		 }
		 //订单生成时间
		 order_info_time.setText(orderInfo.getTime());
		 //发货时间
		 deliver_time.setText(orderInfo.getTime());
		 //是否开具发票
		 invoice_info.setText((invoiceInfo.getTitle()==null||"".equals(invoiceInfo.getTitle()))?"否":"是");
		 //发票抬头
		 invoice_info_title.setText(invoiceInfo.getTitle());
		 //送货要求
		 switch (orderDetail.getDelivery_info()) {
		 case 1:
			 delivery_info.setText("周一至周五送货");
		 break;
		 case 2:
			 delivery_info.setText("休日及公众假期送货");
		 break;
		 case 3:
			 delivery_info.setText("时间不限");
		 break;
		 }
		 
		// 商品数量总计
		 total_count.setText(checkoutMap.get("total_count"));
		// 商品金额总计
		 total_price.setText("￥"+checkoutMap.get("total_price"));
		// 商品积分总计
		 total_point.setText(checkoutMap.get("total_point"));
		// 运费
		 freight.setText("￥"+checkoutMap.get("freight"));
		// 促销减钱
		 prom_cut.setText("￥"+checkoutMap.get("prom_cut"));
	 }

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().changeView(MyOrderView.class);
	}

	@Override
	public void onRightButtonClicked() {

	}

	@Override
	protected void setListener() {
		order_logistics_rel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UIManager.getInstance().changeView(LogisticsView.class);
			}
		});
		
		listdetail.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View item, int position,
					long arg3) {
				//商品详情				
				Product product = new Product();
				OrderProd orderProd = prodList.get(position);
				product.setName(orderProd.getGoodsname());
				product.setId(1001+position);
				product.setMarketprice(orderProd.getPrice()+orderProd.getPrice()*0.2);
				product.setPrice(orderProd.getPrice());
				product.setScore(100);
				product.setNumber(10);
				UIManager.getInstance().getMap().put("productor", product);
				UIManager.getInstance().changeView(ProductDetailView.class);
			}
		});		
		
		listdetail.setOnTouchListener(new OnTouchListener() {
					
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scroll.requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
		
		textCancelOrder.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				UIManager.getInstance().changeView(MyOrderView.class);
				PromptManager.showToast(context, "订单取消成功");				
			}
		});
	}

	@ResID(id = R.layout.order_product_items)
	public static class ViewHolder {
		// 缩略图
		@ResID(id = R.id.prod_pic)
		SmartImageView prod_pic;
		// 商品名称
		@ResID(id = R.id.order_prod_name)
		TextView order_prod_name;
//		// 颜色
//		@ResID(id = R.id.order_prod_color)
//		TextView order_prod_color;
//		// 尺寸
//		@ResID(id = R.id.order_prod_size)
//		TextView order_prod_size;
		// 数量
		@ResID(id = R.id.order_prod_count)
		TextView order_prod_count;
		// 价格
		@ResID(id = R.id.order_prod_price)
		TextView order_prod_price;
		//购买上限		
		@ResID(id=R.id.order_prod_uplimit)
		TextView order_prod_uplimit;
		//是否是礼品
		@ResID(id=R.id.order_prod_isgift)
		TextView order_prod_isgift;
	}

	private class MyAdapter extends myBaseAdapter<OrderProd, ViewHolder> {

		public MyAdapter(List<OrderProd> list, Context context,
				Class<ViewHolder> clazz) {
			super(list, context, clazz);
		}

		@Override
		public void initholder(ViewHolder holder, OrderProd orderProd) {
			holder.order_prod_name.setText(orderProd.getGoodsname());
			holder.order_prod_count.setText(orderProd.getGoodsnum()+"");
			holder.order_prod_isgift.setText(orderProd.getIsgift());
			holder.order_prod_price.setText("￥"+orderProd.getPrice());
			holder.order_prod_uplimit.setText(orderProd.getBuyLimit()+"");
			holder.prod_pic.setImageUrl(orderProd.getGoodsimag());
		}

	}
}
