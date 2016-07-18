package com.shopping.redboy.engine.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSON;
import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.engine.FaoritesEngine;

public class FavoritesServiceImpl implements FaoritesEngine {

	@Override
	public List<Product> getServiceProducts(int offset, int maxnumber) {
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				ConstantValue.URL+"/favorites?page=" + offset
						+ "&pageNum" + maxnumber);
		System.out.println("列表"+httpGet);
		try {
			HttpResponse execute = defaultHttpClient.execute(httpGet);
			int code = execute.getStatusLine().getStatusCode();
			List<Product> product = null;
			if (code == 200) {
				//获取服务器响应字符串
				JSONObject localJSONObject = new JSONObject(
						EntityUtils.toString(execute.getEntity()));
				System.out.println("字符串信息"+localJSONObject);
				boolean flag = checkJson(localJSONObject);
				
				if (flag) {
					product  = new ArrayList<Product>();
					List localList = JSON.parseArray(
							localJSONObject.getString("productlist"),
							Product.class);
					product = localList;
					System.out.println("我草"+product);
				}
			}
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean checkJson(JSONObject localJSONObject) {
		try {
			boolean bool = "erro".equals(localJSONObject.getString("response"));
			if (!bool) 
				return true;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}
