package com.shopping.redboy.engine.Impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.redboy.domain.Product;
import com.shopping.redboy.engine.SearchEngine;
import com.shopping.redboy.util.HttpClientUtil;
import com.shopping.redboy.util.JSONUtil;
import com.shopping.redboy.util.NetUtil;

public class SearchEngineImpl implements SearchEngine {

	@Override
	public List<String> getKeywords() {
		try {
			String result = new HttpClientUtil().SendGet("/search/recommend", null);
			JSONObject json = new JSONObject(result);
			Object object = json.get("search_keywords");
			JSONArray array = new JSONArray(object.toString());
			List<String> list = new ArrayList<String>();
			for(int i = 0; i < array.length(); i++){
				list.add(array.getString(i));
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getProducts() {
		try {
			String result = new HttpClientUtil().SendGet("/productlist", null);
			List<Product> list = JSONUtil.getList_New(result.toString(), Product.class, "productlist");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
