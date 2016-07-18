package com.itheima.redbaby.engine.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.order.OrderDetail;
import com.itheima.redbaby.engine.OrderDetailEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class OrderDetailEngineImpl extends BaseEngine implements OrderDetailEngine {

	@Override
	public OrderDetail getOrderDetailMsg(String orderid) {
		HttpClientUtil clientUtil = new HttpClientUtil();
//		String get = clientUtil.sendGet(ConstantValue.URI + "/orderdetail?orderid=" + orderid);
		String get = clientUtil.sendGet(ConstantValue.URI + "/x_w_orderdetail.php");// TODO
		return JSON.parseObject(get, OrderDetail.class);
	}
	
}
