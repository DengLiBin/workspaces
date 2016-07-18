package com.shopping.redboy.view;

import java.util.Map;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.SubmitTally;

/**
 * 提交结算
 * 
 * @author Admin
 * 
 */
@ResID(id = R.layout.payment_center_activity)
@Category(id = R.id.imgHome, title = "结算中心", leftbutton = "购物车", rightbutton = "订单提交")
public class SubmitTallyView extends BaseView implements ButtonClickListener {
	@ResID(id = R.id.payment_invoice_rel)
	private RelativeLayout payment_invoice_rel;// 填写发票
	@ResID(id = R.id.ordr_submit_bottom_text)
	private TextView ordr_submit_bottom_text;// 提交订单
	@ResID(id = R.id.payment_payType_rel)
	private RelativeLayout payment_payType_rel;// 选择支付方式
	@ResID(id = R.id.payment_address_rel)
	private RelativeLayout payment_address_rel;// 添加收货地址
	@ResID(id = R.id.payment_remark_rel)
	private RelativeLayout payment_remark_rel;// 请留言
	@ResID(id = R.id.payment_sendTime_rel)
	private RelativeLayout payment_sendTime_rel;// 选择送货时间

	@ResID(id = R.id.payment_address_text)
	private TextView payment_address_text; // 送货地址
	@ResID(id = R.id.payment_payHint_text)
	private TextView payment_payHint_text; // 支付方式
	@ResID(id = R.id.payment_sendTimeHint_text)
	private TextView payment_sendTimeHint_text; // 送货时间
	@ResID(id = R.id.payment_invoiceHint_text)
	private TextView payment_invoiceHint_text; // 填写发票
	@ResID(id = R.id.payment_remarkHint_text)
	private TextView payment_remarkHint_text; // 留言信息
	
	@ResID(id=R.id.myscrollview)
	private ScrollView myscrollview;
	
	@ResID(id=R.id.payment_product_list)
	private ListView payment_product_list;//展示商品
	
	@ResID(id=R.id.Clothes_totall_price)//单价
	private TextView Clothes_totall_price;
	@ResID(id=R.id.Clothes_number)//数量
	private TextView Clothes_number;
	
	@ResID(id=R.id.shopcar_total_buycount_text)//设置数量
	private TextView shopcar_total_buycount_text;
	@ResID(id=R.id.shopcar_total_money_text)
	private TextView shopcar_total_money_text;//设置金额总计
	
	@ResID(id=R.id.payment_orderPrice_text)
	private TextView payment_orderPrice_text;//您共需为订单支付
	
	@ResID(id=R.id.shopcar_total_bonus_text)
	private TextView shopcar_total_bonus_text;//赠送积分
	//商品信息
	private static int[] imgs={R.drawable.tianmao,R.drawable.tmall,R.drawable.anycall,R.drawable.dell,R.drawable.tsinghua};
	private static String[] numbers={"2","5","8","9","67"};
	private static String[] prices={"23.2","34.4","87.9","10.9","12.4"};
	private static String[] names={"营养快线","可口可乐","加多宝","健力宝","牛奶"};
	private double moneyToall;//金额总计
	
	
	public SubmitTallyView(Context context) {
		super(context);
	}

	@Override
	protected void setListener() {
		/**
		 * 添加收货地址
		 */
		payment_address_rel.setOnClickListener(this);
		/**
		 * 选择送货时间
		 */

		payment_sendTime_rel.setOnClickListener(this);
		/**
		 * 支付方式
		 */
		payment_payType_rel.setOnClickListener(this);
		
		/**
		 * 请留言
		 */
		payment_remark_rel.setOnClickListener(this);
		/**
		 * 提交成功
		 */
		ordr_submit_bottom_text.setOnClickListener(this);
		
		/**
		 * 填写发票
		 */
		payment_invoice_rel.setOnClickListener(this);
		
		/**
		 * 事件分发@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		 */
		payment_product_list.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				myscrollview.requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
	}

	@Override
	public void onLeftButtonClicked() {
		Toast.makeText(context, "返回购物车", 0).show();
	}

	@Override
	public void onRightButtonClicked() {
		Toast.makeText(context, "提交订单", 0).show();
		UIManager.getInstance().changeView(SsuccessView.class);//订单提交
	}

	@Override
	protected void init() {
		myListSubmitAdapter adapter=new myListSubmitAdapter();
		payment_product_list.setAdapter(adapter);
		
	/*	if(Clothes_number!=null && Clothes_totall_price!=null){
			return;
		}*/
			for(int i=0;i<imgs.length;i++){
				double num=Integer.parseInt(numbers[i]);
				double toallPrice=Double.parseDouble(prices[i]);
				moneyToall+= num*toallPrice;
			}
			int lengh=imgs.length;
			shopcar_total_buycount_text.setText(""+lengh);
			shopcar_total_money_text.setText("$"+moneyToall);
			payment_orderPrice_text.setText("您共需为订单支付：$"+moneyToall);
		}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.payment_address_rel:
			UIManager.getInstance().changeView(SaddressInfoView.class);//地址
			break;
		case R.id.payment_sendTime_rel:
			UIManager.getInstance().changeView(SsendTimeView.class);//发送时间
			break;
		case R.id.payment_payType_rel:
			UIManager.getInstance().changeView(SpaytypeView.class);//支付方式
			break;
		case R.id.payment_remark_rel:
			UIManager.getInstance().changeView(SremarkView.class);//留言
			break;
		case R.id.ordr_submit_bottom_text:
			UIManager.getInstance().changeView(SsuccessView.class);//提交
			break;
		case R.id.payment_invoice_rel:
			UIManager.getInstance().changeView(SinvoceView.class);//发票
			break;

		default:
			break;
		}
	}	

	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Map<String, Object> map=UIManager.getInstance().getMap();
		SubmitTally submit=(SubmitTally) map.get("submit");
			if(submit==null){
				return;
			}
			payment_address_text.setText("姓名："+submit.getName()+"\n电话："+submit.getPhone()+"\n地址："+submit.getAddress()+"\n邮箱："+submit.getEmail()+"\n");
			payment_payHint_text.setText("支付方式："+"\n"+submit.getPaytype());
			payment_sendTimeHint_text.setText("送货时间"+"\n"+submit.getSendtime());
			payment_invoiceHint_text.setText("发票抬头"+"\n"+submit.getInvoicetitle()+"\n"+"发票内容"+"\n"+submit.getInvoiceedit());
			payment_remarkHint_text.setText("留言"+"\n"+submit.getRemark());
	}
	
	class myListSubmitAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgs.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder = null;
			if(convertView!=null){
				view=convertView;
				holder=(ViewHolder) convertView.getTag();
			}else{
				view=View.inflate(context, R.layout.submit_list_items, null);
				holder = new ViewHolder();
				holder.Clothes_Name=(TextView) view.findViewById(R.id.Clothes_Name);
				holder.Clothes_color=(TextView) view.findViewById(R.id.Clothes_color);
				holder.Clothes_imgIcon=(ImageView) view.findViewById(R.id.Clothes_imgIcon);
				holder.Clothes_measure=(TextView) view.findViewById(R.id.Clothes_measure);
				holder.Clothes_Price=(TextView) view.findViewById(R.id.Clothes_Price);
				holder.Clothes_number=(TextView) view.findViewById(R.id.Clothes_number);
				holder.Clothes_totall_price=(TextView) view.findViewById(R.id.Clothes_totall_price);
				view.setTag(holder);
			}
			holder.Clothes_imgIcon.setImageResource(imgs[position]);//图片
			holder.Clothes_Name.setText(names[position]);//商品名
			holder.Clothes_color.setText("无");//颜色
			holder.Clothes_measure.setText("500ml");//尺寸
			holder.Clothes_Price.setText(prices[position]);//价格
			holder.Clothes_number.setText(numbers[position]);//数量
			holder.Clothes_totall_price.setText(prices[position]);//小计
			return view;
		}
		
		class ViewHolder{
			ImageView Clothes_imgIcon;
			TextView Clothes_Name;
			TextView Clothes_color;
			TextView Clothes_measure;
			TextView Clothes_Price;
			TextView Clothes_number;
			TextView Clothes_totall_price;
		}
	}
	
	
}
