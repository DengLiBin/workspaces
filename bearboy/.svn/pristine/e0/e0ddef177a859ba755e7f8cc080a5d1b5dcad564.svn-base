package com.itheima.redbaby.engine.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.goodsentry.GoodsTabulation;
import com.itheima.redbaby.bean.goodsentry.GoodsXiangQing;
import com.itheima.redbaby.engine.GoodsListEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class GoodsListEngineImpl implements GoodsListEngine {

	@Override
	public GoodsTabulation getGoodsList(String cId, String page, String pageNum, String srot) {
		HttpClientUtil clientUtil = new HttpClientUtil();
		Map<String, String> params = new HashMap<String, String>();
		params.put("cId", cId);
		params.put("page", page);
		params.put("pageNum", pageNum);
		params.put("orderby", srot);
		String uri = ConstantValue.URI + "/x_search_product_list.php";
		try {
			String get = clientUtil.sendPost(uri, params);
			if (get != null) {
				return JSON.parseObject(get, GoodsTabulation.class);
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Product getProduct(String id) {
		HttpClientUtil clientUtil = new HttpClientUtil();
		Map<String, String> params = new HashMap<String, String>();
		params.put("pId", id);
		String uri = ConstantValue.URI + "/x_product_detail.php";
		String get = clientUtil.sendPost(uri, params);
		try {
			if (get != null) {
				GoodsXiangQing object = JSON.parseObject(get, GoodsXiangQing.class);
				return object.getProduct().get(0);
			}
		} catch (Exception e) {
		}
		return null;
	}

}
