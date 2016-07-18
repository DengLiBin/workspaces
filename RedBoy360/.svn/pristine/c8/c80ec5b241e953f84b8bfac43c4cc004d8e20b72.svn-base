package com.shopping.redboy.view;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Logistics;
import com.shopping.redboy.engine.LogisticsEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;

/**
 * 物流信息
 * 
 * @Description TODO
 * @author liang
 * @date 2014-4-17 下午9:45:59
 */

@ResID(id = R.layout.logistcs)
@Category(id = R.id.imgHome, title = "物流查询", leftbutton = "订单详情", rightbutton = "")
public class LogisticsView extends BaseView implements ButtonClickListener {
	@ResID(id=R.id.sv_scroll)
	private ScrollView sv_scroll;
	//进度条
	@ResID(id=R.id.pb_load)
	private ProgressBar pb_load;
	// 发货方式
	@ResID(id = R.id.tv_way)
	private TextView tv_way;
	// 物流公司
	@ResID(id = R.id.tv_compeny)
	private TextView tv_compeny;
	// 运单号码
	@ResID(id = R.id.tv_ordernumber)
	private TextView tv_ordernumber;
	// 物流跟踪
	@ResID(id = R.id.tv_logisticsgps)
	private TextView tv_logisticsgps;

	// 物流信息时间
	@ResID(id = R.id.tv_info)
	private TextView tv_info;
	private Logistics logistics;
	private List<String> list;

	public LogisticsView(Context context) {
		super(context);
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().changeView(OrderDetailView.class);
	}

	@Override
	public void onRightButtonClicked() {

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
				sv_scroll.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void onPostExecute() {
				fillData();
				pb_load.setVisibility(View.INVISIBLE);
				sv_scroll.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void doInBackground() {
				LogisticsEngine logisticsEngine = BeanFactory.getImpl(LogisticsEngine.class);
				logistics = logisticsEngine.getLogistic();
			}
		}.execute();
	}

	@Override
	protected void setListener() {

	}

	private void fillData() {
		tv_way.setText(logistics.getExpressway());
		tv_compeny.setText(logistics.getLogisticscorp());
		tv_ordernumber.setText(logistics.getLogisticsid());
		tv_logisticsgps.setText("以下信息由物流公司提供，如有疑问请查询"
				+ logistics.getLogisticscorp() + "官方网站");

		list = logistics.getLogisticsInfo();
		String strs = "";
		for (String str : list) {
			strs = strs + str + "\n\n";
		}
		tv_info.setText(strs);
	}

}
