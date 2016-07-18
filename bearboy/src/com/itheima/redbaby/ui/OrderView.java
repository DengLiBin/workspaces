package com.itheima.redbaby.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.bool;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.order.Order;
import com.itheima.redbaby.bean.order.OrderDetail;
import com.itheima.redbaby.bean.order.OrderList;
import com.itheima.redbaby.engine.HomeEngine;
import com.itheima.redbaby.engine.OrderEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;

/**
 * 订单中心
 * 
 * @author my
 * 
 */
public class OrderView extends BaseView {

	private ListView lv_order_list;

	private List<Order> list;

	public OrderView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_order_list, null);

	}

	private TextView tv_lashorder;
	private TextView tv_oldorder;
	private TextView tv_cancelorder;
	private ImageView iv_line1;
	private ImageView iv_line2;
	private ImageView iv_line3;

	@Override
	protected void findViewById() {

		lv_order_list = (ListView) middleView.findViewById(R.id.dl_lv_order_list);
		tv_lashorder = (TextView) middleView.findViewById(R.id.tv_lastedorder);
		tv_oldorder = (TextView) middleView.findViewById(R.id.tv_oldorder);
		tv_cancelorder = (TextView) middleView.findViewById(R.id.tv_cancelorder);

		iv_line1 = (ImageView) middleView.findViewById(R.id.iv_line1);
		iv_line2 = (ImageView) middleView.findViewById(R.id.iv_line2);
		iv_line3 = (ImageView) middleView.findViewById(R.id.iv_line3);
		iv_line1.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置下划线不可见
	 */
	private void setLineInvisible() {
		iv_line1.setVisibility(View.INVISIBLE);
		iv_line2.setVisibility(View.INVISIBLE);
		iv_line3.setVisibility(View.INVISIBLE);
	}

	
	@Override
	protected void setListener() {
		// 最近订单
		tv_lashorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setLineInvisible();
				iv_line1.setVisibility(View.VISIBLE);
				prepareListData("1", "1", "1", "1");
			}
		});

		tv_oldorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setLineInvisible();
				iv_line2.setVisibility(View.VISIBLE);
				prepareListData("2", "1", "1", "1");
			}
		});
		tv_cancelorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setLineInvisible();
				iv_line3.setVisibility(View.VISIBLE);
				prepareListData("3", "1", "1", "1");
			}
		});
		lv_order_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO
				Order order = list.get(position);
				getBundle().putSerializable("order", order);
				MiddleManager.getInstance().changeView(OrderContentView.class, getBundle());
			}
		});
	}

	@Override
	protected void processEngine() {
		list = new ArrayList<Order>();
		prepareListData("1", "1", "10", "123");

	}

	/**
	 * 向list中填充数据
	 */

	private void prepareListData(String type, String page, String pageNum, String userId) {
		// TODO
		new MyHttpTask<String>() {

			@Override
			protected Object doInBackground(String... params) {
				OrderEngine OrderEngine = BeanFactory.getFactory().getInstance(OrderEngine.class);

				return OrderEngine.getOrderMsg(params[0], params[1], params[2], params[3]);
			}

			protected void onPostExecute(Object result) {
				if(result!=null){
					OrderList resultlist = (OrderList) result;
					List<Order> orderlist = resultlist.getOrderlist();
					list.clear();
					// 填充数据
					for (Order order : orderlist) {
						list.add(order);
					}
					MyAdapter adapter = new MyAdapter();
					lv_order_list.setAdapter(adapter);
				}
				
			};
		}.executeProxy(type, page, pageNum, userId);

	}

	@Override
	protected int getID() {
		return ConstantValue.VIEW_ORDER_LIST;
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
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
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.dl_order_list_item, null);
				holder.tv_id = (TextView) convertView.findViewById(R.id.dl_tv_order_id_entry);
				holder.tv_flag = (TextView) convertView.findViewById(R.id.dl_tv_order_flag_entry);
				holder.tv_time = (TextView) convertView.findViewById(R.id.dl_tv_order_time_entry);
				holder.tv_money = (TextView) convertView.findViewById(R.id.dl_tv_order_price_entry);
				holder.tv_status = (TextView) convertView.findViewById(R.id.dl_tv_order_status_entry);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv_id.setText(list.get(position).getOrderid());
			holder.tv_money.setText(String.valueOf(list.get(position).getPrice()));
			holder.tv_status.setText(list.get(position).getStatus());
			holder.tv_flag.setText(String.valueOf(list.get(position).getFlag()));
			holder.tv_time.setText(list.get(position).getTime());
			return convertView;
		}

		class ViewHolder {
			TextView tv_id;
			TextView tv_status;
			TextView tv_time;
			TextView tv_money;
			TextView tv_flag;

		}
	}

}
