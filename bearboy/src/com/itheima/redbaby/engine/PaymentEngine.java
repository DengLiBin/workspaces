package com.itheima.redbaby.engine;

import android.content.Context;

import com.itheima.redbaby.bean.Invoice;
import com.itheima.redbaby.bean.PaymentInfo;
import com.itheima.redbaby.bean.order.Order;

public interface PaymentEngine {
	PaymentInfo getPaymentInfo(Context context);

	Order submitOrder(Context context, PaymentInfo paymentInfo, Invoice invoice);
	
}
