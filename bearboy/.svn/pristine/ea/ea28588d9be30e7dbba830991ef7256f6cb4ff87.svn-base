package com.itheima.redbaby.engine.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.order.Logistic;
import com.itheima.redbaby.engine.LogisticEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class LogisticEngineimpl extends BaseEngine implements LogisticEngine {

	@Override
	public Logistic getLogisticMsg(String userid) {
		HttpClientUtil clientUtil = new HttpClientUtil();
		String get = clientUtil.sendGet(ConstantValue.URI + "/x_w_logistics.php");// TODO
		return JSON.parseObject(get, Logistic.class);
	}

}
