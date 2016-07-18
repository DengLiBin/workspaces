package com.shopping.redboy.view;

import java.util.List;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.SorderInfo;
import com.shopping.redboy.engine.Impl.SubmitEngieImpl;
import com.shopping.redboy.util.MyAsynTask;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
@ResID(id=R.layout.order_submit_ok_activity)
@Category(id=R.id.imgHome,title="提交成功",leftbutton="",rightbutton="")
public class SsuccessView extends BaseView {

	private String orderId;//订单号
	private double price;//价格
	private int type;//支付类型
	
	@ResID(id=R.id.orderid_value_text)
	private TextView orderid_value_text;
	@ResID(id=R.id.paymoney_value_text)
	private TextView paymoney_value_text;
	@ResID(id=R.id.paytype_value_text)
	private TextView paytype_value_text;
	
	@ResID(id=R.id.help_number)
	private LinearLayout help_number;//电话帮助
	
	@ResID(id=R.id.to_order_detail_text)
	private TextView to_order_detail_text;//查看订单
	
	@ResID(id=R.id.continue_shoping_text)
	private TextView continue_shoping_text;//继续购物
	
	List<SorderInfo> orderInfoList;
	public SsuccessView(Context context) {
		super(context);
	}

	@Override
	protected void setListener() {
		help_number.setOnClickListener(this);//电话帮助
		to_order_detail_text.setOnClickListener(this);//查看订单
		to_order_detail_text.setOnClickListener(this);//继续购物
	}

	@Override
	protected void init() {
		new  MyAsynTask() {
			
			@Override
			public void onPreExecute() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPostExecute() {
				// TODO Auto-generated method stub
				for(int i=0;i<orderInfoList.size();i++){
					SorderInfo info=orderInfoList.get(i);
					orderId = info.getOrderid();
					price = info.getPrice();
					type = info.getPaymenttype();
				}
				orderid_value_text.setText(orderId);
				paymoney_value_text.setText(price+"元");
				paytype_value_text.setText(type+"");
			}
			
			@Override
			public void doInBackground() {
				// TODO Auto-generated method stub
				SubmitEngieImpl impl=new SubmitEngieImpl();
				orderInfoList=impl.getServiceSorderInfoList();
				
			}
		}.execute();
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.help_number:
			Intent intent=new Intent();
			intent.setAction(intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+"110"));//固定格式，是一种规定
			context.startActivity(intent);
			break;
		case R.id.to_order_detail_text://查看订单
			UIManager.getInstance().changeView(OrderDetailView.class);
			break;
		case R.id.continue_shoping_text://查看订单
			//UIManager.getInstance().changeView(OrderDetailView.class);
			Toast.makeText(context, "返回购物车", 0).show();
			break;

		default:
			break;
		}
	}

}
