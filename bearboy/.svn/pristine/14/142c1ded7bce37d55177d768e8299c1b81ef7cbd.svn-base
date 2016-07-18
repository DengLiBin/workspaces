package com.itheima.redbaby.engine.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.order.OrderList;
import com.itheima.redbaby.engine.OrderEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class OrderEngineImpl extends BaseEngine implements OrderEngine {

	private String get;

	@Override
	public OrderList getOrderMsg(String type, String page, String pageNum, String userId) {
		// TODO
		HttpClientUtil clientUtil = new HttpClientUtil();

		if ("1".equals(type)) {

			get = clientUtil.sendGet(ConstantValue.URI + "/x_w_orderlist.php");
		} else if ("2".equals(type)) {
			get = clientUtil.sendGet(ConstantValue.URI + "/x_w_orderlist1.php");
		} else if ("3".equals(type)) {
			get = clientUtil.sendGet(ConstantValue.URI + "/x_w_orderlist2.php");

		}
//		String get = clientUtil.sendGet("http://192.168.1.50:8080/myserver/1.js");
//		String get = getTxt();
		return JSON.parseObject(get, OrderList.class);
	}

//	type	1/2/3
//	page	第几页
//	pageNum	每页个数
//	userId	用户id

}
