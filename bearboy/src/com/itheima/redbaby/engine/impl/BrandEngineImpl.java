package com.itheima.redbaby.engine.impl;

import java.util.List;


import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.Brand;
import com.itheima.redbaby.engine.BrandEngine;

public class BrandEngineImpl extends BaseEngine implements BrandEngine {

	@Override
	public List<Brand> getBrand() {
		try {
			String json = accessNet(ConstantValue.URI + ConstantValue.BRAND, null, "brand ");
			return JSON.parseArray(json, Brand.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
