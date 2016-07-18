package com.itheima.redbaby.engine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.ClassifyFist;
import com.itheima.redbaby.bean.CommodityClassify;
import com.itheima.redbaby.engine.FistLevelClassifyEngine;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 商品一级分类的 实现类
 * 
 * @author Crist
 * 
 */
public class FistLevelClassifyEngineImpl extends BaseEngine implements FistLevelClassifyEngine {

	@Override
	public List<ClassifyFist> getFistClassify() {
		String fistClassify = httpClient.sendGet(ConstantValue.URI + "/x_fenlei1.php");
		CommodityClassify parseObject = JSON.parseObject(fistClassify, CommodityClassify.class);
		if (parseObject != null) {
			List<ClassifyFist> category = parseObject.getCategory();
			return category;
		} else {

			return null;
		}
	}

	@Override
	public List<ClassifyFist> getSecondClassify(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		String fistClassify = httpClient.sendPost(ConstantValue.URI + "/x_fenlei2.php", map);
		CommodityClassify parseObject = JSON.parseObject(fistClassify, CommodityClassify.class);
		if (parseObject != null) {
			List<ClassifyFist> category = parseObject.getCategory();
			return category;
		} else {
			return null;
		}
	}

}
