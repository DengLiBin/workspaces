package com.itheima.redbaby.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.order.LogisticItem;
import com.itheima.redbaby.ui.manager.BaseView;

public class LogisticView extends BaseView {

	private TextView tv_sendtype;
	private TextView tv_id;
	private TextView tv_company;
	private TextView tv_number;
	private ListView lv_list;
	private LogisticItem item;
	private List<String> list;

	public LogisticView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_logistic_msg, null);
	}

	@Override
	protected void findViewById() {
		tv_sendtype = (TextView) middleView.findViewById(R.id.tv_sendtype);
		tv_id = (TextView) middleView.findViewById(R.id.tv_l_id);
		tv_company = (TextView) middleView.findViewById(R.id.tv_l_company);
		tv_number = (TextView) middleView.findViewById(R.id.tv_l_number);
		lv_list = (ListView) middleView.findViewById(R.id.lv_content);

	}

	@Override
	protected void setListener() {

	}

	@Override
	public void onResume() {
		item = (LogisticItem) bundle.getSerializable("logistics");
		list = item.getList();

		MyAdapter adapter = new MyAdapter();
		lv_list.setAdapter(adapter);
	}

	@Override
	protected void processEngine() {

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
			TextView tv = new TextView(context);
			tv.setText(list.get(position));
			return tv;
		}

	}

}
