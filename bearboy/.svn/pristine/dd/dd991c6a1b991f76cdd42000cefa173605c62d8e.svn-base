package com.itheima.redbaby.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemSelectedListener;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Invoice;
import com.itheima.redbaby.bean.PaymentInfo;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.CartShoppingDelteProduct;
import com.itheima.redbaby.ui.manager.MiddleManager;

public class PaymentSelectView extends BaseView implements OnClickListener,CartShoppingDelteProduct {

	private LinearLayout PayMoneyMethod;
	private RelativeLayout payMoneyRel;
	private ImageView payMoneyImg;
	private RelativeLayout payPosRel;
	private ImageView payPosImg;
	private RelativeLayout payAlipayRel;
	private ImageView payAlipayImg;

	private LinearLayout invoceType;
	private ToggleButton invoicetypeTb;
	private EditText invoicetypeTitle;
	private Spinner invoicetypecontent;

	private LinearLayout sendTime;
	private RelativeLayout sendAllDay;
	private ImageView sendAllDayImg;
	private RelativeLayout sendHoliday;
	private ImageView sendHolidayImg;
	private RelativeLayout sendWorkday;
	private ImageView sendWorkdayImg;
	

	private Button save;
	private String[] invoceTypeStr = { "图书", "音响", "游戏", "软件", "资料" };

	private PaymentInfo paymentInfo;

	public PaymentSelectView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_payment_center_select,
				null);

	}

	@Override
	protected void findViewById() {
		PayMoneyMethod = (LinearLayout) middleView
				.findViewById(R.id.pay_money_method);
		payMoneyRel = (RelativeLayout) middleView
				.findViewById(R.id.pay_money_rel);
		payMoneyImg = (ImageView) middleView.findViewById(R.id.pay_money_img);
		payPosRel = (RelativeLayout) middleView.findViewById(R.id.pay_pos_rel);
		payPosImg = (ImageView) middleView.findViewById(R.id.pay_pos_img);
		payAlipayRel = (RelativeLayout) middleView
				.findViewById(R.id.pay_alipay_rel);
		payAlipayImg = (ImageView) middleView.findViewById(R.id.pay_alipay_img);

		invoceType = (LinearLayout) middleView.findViewById(R.id.invoce_type);
		invoicetypeTb = (ToggleButton) middleView
				.findViewById(R.id.invoice_type_tb);
		invoicetypeTitle = (EditText) middleView
				.findViewById(R.id.invoice_title_edit);
		invoicetypecontent = (Spinner) middleView
				.findViewById(R.id.invoice_content_spin);

		sendTime = (LinearLayout) middleView.findViewById(R.id.send_time);
		sendWorkday = (RelativeLayout) middleView
				.findViewById(R.id.send_workday_rel);
		sendWorkdayImg = (ImageView) middleView
				.findViewById(R.id.send_workday_img);
		sendHoliday = (RelativeLayout) middleView
				.findViewById(R.id.send_holiday_rel);
		sendHolidayImg = (ImageView) middleView
				.findViewById(R.id.send_holiday_img);
		sendAllDay = (RelativeLayout) middleView
				.findViewById(R.id.send_allday_rel);
		sendAllDayImg = (ImageView) middleView
				.findViewById(R.id.send_allday_img);
		

		save = (Button) middleView.findViewById(R.id.save);

		initLayout();
	}

	private void initLayout() {
		PayMoneyMethod.setVisibility(View.GONE);
		invoceType.setVisibility(View.GONE);
		sendTime.setVisibility(View.GONE);
		initImage();

	}

	private void initImage() {
		payMoneyImg.setVisibility(View.GONE);
		payPosImg.setVisibility(View.GONE);
		payAlipayImg.setVisibility(View.GONE);

		sendWorkdayImg.setVisibility(View.GONE);
		sendHolidayImg.setVisibility(View.GONE);
		sendAllDayImg.setVisibility(View.GONE);
	}

	@Override
	public void onResume() {
		String from = bundle.getString("from");
		paymentInfo = (PaymentInfo) bundle.getSerializable("PaymentInfo");

		if (from.equals("payType")) {
			initLayout();
			PayMoneyMethod.setVisibility(View.VISIBLE);
			initPayment();

		} else if (from.equals("sendTime")) {
			initLayout();
			sendTime.setVisibility(View.VISIBLE);
			initDelivery();

		} else if (from.equals("invoice")) {
			initLayout();
			setAdapter();
			invoceType.setVisibility(View.VISIBLE);
		}
	}

	private void initDelivery() {
		String delivery_info = paymentInfo.getDelivery_info();
		initImage();
		if ("1".equals(delivery_info)) {
			sendWorkdayImg.setVisibility(View.VISIBLE);
		} else if ("2".equals(delivery_info)) {
			sendHolidayImg.setVisibility(View.VISIBLE);
		} else if ("3".equals(delivery_info)) {
			sendAllDayImg.setVisibility(View.VISIBLE);
		}
	}

	private void initPayment() {

		String payment_info = paymentInfo.getPayment_info();
		initImage();
		if ("1".equals(payment_info)) {
			payMoneyImg.setVisibility(View.VISIBLE);
		} else if ("2".equals(payment_info)) {
			payPosImg.setVisibility(View.VISIBLE);
		} else if ("3".equals(payment_info)) {
			payAlipayImg.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pay_money_rel:
			paymentInfo.setPayment_info("1");
			initPayment();
			break;
		case R.id.pay_pos_rel:
			paymentInfo.setPayment_info("2");
			initPayment();
			break;
		case R.id.pay_alipay_rel:
			paymentInfo.setPayment_info("3");
			initPayment();
			break;

		case R.id.send_workday_rel:
			paymentInfo.setDelivery_info("1");
			initDelivery();
			break;
		case R.id.send_holiday_rel:
			paymentInfo.setDelivery_info("2");
			initDelivery();
			break;
		case R.id.send_allday_rel:
			paymentInfo.setDelivery_info("3");
			initDelivery();
			break;
		case R.id.save:
			Invoice invoice = new Invoice();
			if(invoicetypeTb.isChecked()){
				invoice.setType("个人");
			}else{
				invoice.setType("单位");
			}
			
			invoice.setTitle(invoicetypeTitle.getText().toString());
			invoice.setContent(invoceTypeStr[i]);
			bundle.putString("from", "PaymentSelectView");
			bundle.putSerializable("Invoice", invoice);
			bundle.putSerializable("PaymentInfo", paymentInfo);
			MiddleManager.getInstance().changeView(PaymentView.class, bundle);
			break;

		}

	}

	@Override
	protected void setListener() {

		payMoneyRel.setOnClickListener(this);
		payPosRel.setOnClickListener(this);
		payAlipayRel.setOnClickListener(this);
		invoicetypeTb.setOnClickListener(this);
		sendAllDay.setOnClickListener(this);
		sendHoliday.setOnClickListener(this);
		sendWorkday.setOnClickListener(this);
		save.setOnClickListener(this);

	}

	@Override
	protected void processEngine() {

	}

	@Override
	protected int getID() {
		return ConstantValue.VIEW_PAYMENT_SELECT;
	}

	private void setAdapter() {

		invoicetypecontent
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						i = position;

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
		invoicetypecontent.setAdapter(new SpinnerAdapter() {

			@Override
			public void unregisterDataSetObserver(DataSetObserver observer) {
			}

			@Override
			public void registerDataSetObserver(DataSetObserver observer) {
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean hasStableIds() {
				return false;
			}

			@Override
			public int getViewTypeCount() {
				return invoceTypeStr.length;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = View
						.inflate(
								context,
								R.layout.dl_payment_center_select_invoice_content,
								null);
				TextView tv = (TextView) view.findViewById(R.id.tv);
				ImageView iv = (ImageView) view.findViewById(R.id.iv);
				iv.setImageResource(R.drawable.dd_payment_center_select_invoice_select);
				tv.setText(invoceTypeStr[position]);

				return view;
			}

			@Override
			public int getItemViewType(int position) {
				return 0;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public int getCount() {
				return invoceTypeStr.length;
			}

			@Override
			public View getDropDownView(int position, View convertView,
					ViewGroup parent) {
				View view = View
						.inflate(
								context,
								R.layout.dl_payment_center_select_invoice_content,
								null);
				TextView tv = (TextView) view.findViewById(R.id.tv);
				ImageView iv = (ImageView) view.findViewById(R.id.iv);
				if (i == position)
					iv.setImageResource(R.drawable.dd_payment_center_select_invoice_select);
				else
					iv.setImageResource(R.drawable.dd_payment_center_select_invoice_normal);
				tv.setText(invoceTypeStr[position]);

				return view;
			}
		});
	}

	private int i = 0;

	@Override
	public void deleteProduct() {
	}

	@Override
	public void goBack() {
		MiddleManager.getInstance().changeView(PaymentView.class, null);
	}
}