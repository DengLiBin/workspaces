package com.itheima.redbaby.engine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.TopicEngine;

public class TopicEngineImpl extends BaseEngine implements TopicEngine{

	@Override
	public List<Product> getNewProduct(int pageNum, int offset) {
		try {
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("pageNum", String.valueOf(pageNum));
//			params.put("offset", String.valueOf(offset));
			String json = accessNet("http://192.168.1.70:8080/android/newproduct.js", null, "productlist");
			return JSON.parseArray(json, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getHotProduct(int pageNum, int offset) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageNum", String.valueOf(pageNum));
			params.put("offset", String.valueOf(offset));
			String json = accessNet("http://192.168.1.70:8080/android/newproduct.js", params, "productlist");
			return JSON.parseArray(json, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getBrandItem(Integer pageNum, Integer offset,
			Integer brandId) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageNum", String.valueOf(pageNum));
			params.put("offset", String.valueOf(offset));
			params.put("id", String.valueOf(brandId));
			String json = accessNet(ConstantValue.BRANDITEMURI, params, "productlist");
			return JSON.parseArray(json, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
