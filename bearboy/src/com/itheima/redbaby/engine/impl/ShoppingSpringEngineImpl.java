package com.itheima.redbaby.engine.impl;

import java.util.List;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.ShoppingSpringEngine;


public class ShoppingSpringEngineImpl extends BaseEngine implements ShoppingSpringEngine {

	@Override
	public List<Product> getShoppingSpring() {
		try {
			String json = accessNet("http://192.168.1.70:8080/android/shopping.js", null, "productlist");
			return JSON.parseArray(json, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
