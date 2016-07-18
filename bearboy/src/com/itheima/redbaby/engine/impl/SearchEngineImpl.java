package com.itheima.redbaby.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.goodsentry.GoodsTabulation;
import com.itheima.redbaby.entity.SearchEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class SearchEngineImpl extends BaseEngine implements SearchEngine {

	private List<Product> goodsTabulation;

	@Override
	public List<String> getRecommend() {
		// TODO Auto-generated method stub
		String uri = "http://192.168.1.59:8078/x_search_product.php";
		String result = httpClient.sendGet(uri);

		return null;
	}

	@Override
	public List<Product> search(String keyword, int page, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> getGoodsTabulationList(String keyname) {
		goodsTabulation = new ArrayList<Product>();
		HttpClientUtil hc = new HttpClientUtil();
		Map<String, String> map = new HashMap<String, String>();
//		map.put("keyword", "1");
		String uri = "http://192.168.1.20/Jerry_json.html";
		String sendGet = hc.sendGet(uri);
		System.out.println(sendGet);
		GoodsTabulation parseObject = JSON.parseObject(sendGet,
				GoodsTabulation.class);
		for (Product product : parseObject.getProductlist()) {
			if (product.getName().contains(keyname)) {
				goodsTabulation.add(product);
			}
		}
		return goodsTabulation;
	}
//HTTP://192.168.1.59:8078/
	public List<Product> getGoodsMethod(String keyname) {
		goodsTabulation = new ArrayList<Product>();
		HttpClientUtil hc = new HttpClientUtil();
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyname);
		String uri = "http://192.168.1.70:8078/x_search_product.php";
//		String uri = "http://192.168.1.20/Jerry_json.html";
		String sendPost = hc.sendPost(uri,map);
		System.out.println(sendPost);
		GoodsTabulation parseObject = null;
		try {
			 parseObject = JSON.parseObject(sendPost,
					GoodsTabulation.class);
		} catch (Exception e) {
			return null;
		}
		
		for (Product product : parseObject.getProductlist()) {
			if (product.getName().contains(keyname)) {
				goodsTabulation.add(product);
			}
		}
		return goodsTabulation;
	}
}
