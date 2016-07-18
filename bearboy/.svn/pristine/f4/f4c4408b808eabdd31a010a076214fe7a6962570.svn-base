package com.itheima.redbaby.engine.impl;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.Promotion;
import com.itheima.redbaby.engine.PromotionEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class PromotionEngineImpl extends BaseEngine implements PromotionEngine {
	
	public PromotionEngineImpl() {
	}
	
	@Override
	public List<Promotion> getPromotionList() {
		try {
			String topicJson = accessNet(ConstantValue.URI + ConstantValue.PromotionURI, null, "topic ");
			return JSON.parseArray(topicJson, Promotion.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Product> getPromotionProduct(Integer pageNum, Integer offset,
			Integer id) {
		try {
			String topicJson = accessNet(ConstantValue.URI + ConstantValue.PROMOTIONPRODUCTURI, null, "productlist");
			return JSON.parseArray(topicJson, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
}
