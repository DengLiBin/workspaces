package com.itheima.redbaby.engine.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.home.HomeBean;
import com.itheima.redbaby.engine.HomeEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class HomeEngineImpl extends BaseEngine implements HomeEngine {

	@Override
	public HomeBean getHomeMsg() {
		HttpClientUtil clientUtil = new HttpClientUtil();
		String get = clientUtil.sendGet(ConstantValue.URI + "/X_home.php");
		return JSON.parseObject(get, HomeBean.class);
	}

}
