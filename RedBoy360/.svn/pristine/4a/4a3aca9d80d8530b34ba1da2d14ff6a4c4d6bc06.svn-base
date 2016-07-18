package com.shopping.redboy.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Order;
import com.shopping.redboy.domain.OrderProd;
import com.shopping.redboy.engine.OrderListEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.myBaseAdapter;
import com.shopping.redboy.view.OrderDetailView.ViewHolder;

@ResID(id = R.layout.my_order_activity)
@Category(id = R.id.imgHome, title = "我的订单", leftbutton = "账户中心", rightbutton = "")
public class MyOrderView extends BaseView implements ButtonClickListener {
	// 近一个月订单
	@ResID(id = R.id.my_order_month)
	private TextView my_order_month;
	// 所有订单
	@ResID(id = R.id.my_order_all)
	private TextView my_order_all;
	// 已取消订单
	@ResID(id = R.id.my_order_notsend)
	private TextView my_order_notsend;
	// 订单列表
	@ResID(id = R.id.my_order_list)
	private ListView my_order_list;
	// 无订单显示
	@ResID(id = R.id.my_order_null_text)
	private TextView my_order_null_text;
	//进度条
	@ResID(id=R.id.pb_load)
	private ProgressBar pb_load;

	private List<Order> list;
	private List<Order> listC;
	private MyAdapter adapter;
	private  MyAdapter adapterCancel;

	public MyOrderView(Context context) {
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
				my_order_list.setVisibility(View.INVISIBLE);
			}			
			@Override
			public void onPostExecute() {
				adapter = new MyAdapter(list, context, ViewHolder.class);	
				my_order_list.setAdapter(adapter);
				adapterCancel = new MyAdapter(listC, context, ViewHolder.class);
				pb_load.setVisibility(View.INVISIBLE);
				my_order_list.setVisibility(View.VISIBLE);
			}
			@Override
			public void doInBackground() {
				OrderListEngine orderListEngine = BeanFactory.getImpl(OrderListEngine.class);
				list = orderListEngine.getOrderInfo();
				listC=new ArrayList<Order>();
				for(Order order: list){
					if("已处理".equals(order.getStatus())){
						listC.add(order);
					}
				}
			}
		}.execute();
	}


	/**
	 * 进入用户账户
	 */
	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().changeView(AccountView.class);
	}

	@Override
	public void onRightButtonClicked() {

	}

	@Override
	protected void setListener() {
		my_order_month.setOnClickListener(this);// 近一个月订单
		my_order_all.setOnClickListener(this);// 全部订单
		my_order_notsend.setOnClickListener(this);// 已取消订单
		my_order_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view, int position,
					long arg3) {
				UIManager.getInstance().changeView(OrderDetailView.class);
			}
		});
	}

	/**
	 * 筛选条件按钮的点击
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_order_month:
			my_order_month
					.setBackgroundResource(R.drawable.segment_selected_1_bg);
			my_order_month.setTextColor(Color.WHITE);
			my_order_all.setBackgroundResource(R.drawable.segment_normal_2_bg);
			my_order_all.setTextColor(Color.BLACK);
			my_order_notsend
					.setBackgroundResource(R.drawable.segment_normal_3_bg);
			my_order_notsend.setTextColor(Color.BLACK);
			
			my_order_list.setVisibility(View.GONE);
			my_order_null_text.setVisibility(View.VISIBLE);			
			break;
		case R.id.my_order_all:
			my_order_month
					.setBackgroundResource(R.drawable.segment_normal_1_bg);
			my_order_month.setTextColor(Color.BLACK);
			my_order_all
					.setBackgroundResource(R.drawable.segment_selected_2_bg);
			my_order_all.setTextColor(Color.WHITE);
			my_order_notsend
					.setBackgroundResource(R.drawable.segment_normal_3_bg);
			my_order_notsend.setTextColor(Color.BLACK);
			
			my_order_list.setAdapter(adapter);
			
			if (list.size() < 1 || list == null) {
				my_order_list.setVisibility(View.GONE);
				my_order_null_text.setVisibility(View.VISIBLE);
			} else {
				my_order_list.setVisibility(View.VISIBLE);
				my_order_null_text.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
			}
			
			break;
		case R.id.my_order_notsend:
			my_order_month
					.setBackgroundResource(R.drawable.segment_normal_1_bg);
			my_order_month.setTextColor(Color.BLACK);
			my_order_all.setBackgroundResource(R.drawable.segment_normal_2_bg);
			my_order_all.setTextColor(Color.BLACK);
			my_order_notsend
					.setBackgroundResource(R.drawable.segment_selected_3_bg);
			my_order_notsend.setTextColor(Color.WHITE);
			
			my_order_list.setAdapter(adapterCancel);
		
			if (list.size() < 1 || list == null) {
				my_order_list.setVisibility(View.GONE);
				my_order_null_text.setVisibility(View.VISIBLE);
			} else {
				my_order_list.setVisibility(View.VISIBLE);
				my_order_null_text.setVisibility(View.GONE);
				adapterCancel.notifyDataSetChanged();
			}
			break;
		}
	}

	@ResID(id=R.layout.my_order_listitem)
	public static class ViewHolder {
		// 订单编号
		@ResID(id=R.id.orderId_text)
		private TextView orderId_text;
		// 价格
		@ResID(id=R.id.orderPrice_text)
		private TextView orderPrice_text;
		// 订单生成时间
		@ResID(id=R.id.orderTime_text)
		private TextView orderTime_text;
		// 状态
		@ResID(id=R.id.orderState_text)
		private TextView orderState_text;
	}
	
	private class MyAdapter extends myBaseAdapter<Order, ViewHolder> {

		public MyAdapter(List<Order> list, Context context,
				Class<ViewHolder> clazz) {
			super(list, context, clazz);
		}
		@Override
		public void initholder(ViewHolder holder, Order order) {
			holder.orderId_text.setText(order.getOrderId());
			holder.orderPrice_text.setText(order.getPrice() + "元");
			holder.orderTime_text.setText(order.getTime());
			holder.orderState_text.setText(order.getStatus());
		}

	}
//	private class MyAdapterC extends myBaseAdapter<Order, ViewHolder> {
//		
//		public MyAdapterC(List<Order> list, Context context,
//				Class<ViewHolder> clazz) {
//			super(list, context, clazz);
//		}
//		@Override
//		public void initholder(ViewHolder holder, Order order) {
//			holder.orderId_text.setText(order.getOrderId());
//			holder.orderPrice_text.setText(order.getPrice() + "元");
//			holder.orderTime_text.setText(order.getTime());
//			holder.orderState_text.setText(order.getStatus());				
//		
//		}
//		
//	}
}
