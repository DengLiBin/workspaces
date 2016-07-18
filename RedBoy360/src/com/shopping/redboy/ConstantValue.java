package com.shopping.redboy;

import java.util.ArrayList;
import java.util.List;

import com.shopping.redboy.domain.Person;

public interface ConstantValue {
	String URL = "http://192.168.1.4/ECServer_D";
	String ENCONDING = "utf-8";
	
	/**
	 * 服务器订单的返回
	 */
	String CHECKOUT = "/checkout";
	/**
	 *	提交结算
	 */
	String ORDERSUBMIT="/ordersumbit";
	/**
	 * 服务器返回的发票信息
	 */
	String INVOICE="/invoice";
	/**
	 * 订单信息返回头
	 */
	String ORDERDETAIL = "orderdetail";
	
	String PAYMONEY = "双休日、节假日均可";

	String POS = "休息日";

	String ALIPAY = "工作日";
	
	List<Person> PERSONS=new ArrayList<Person>();
	
}
