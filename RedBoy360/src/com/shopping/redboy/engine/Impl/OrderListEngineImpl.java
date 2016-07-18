package com.shopping.redboy.engine.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.shopping.redboy.domain.Order;
import com.shopping.redboy.engine.OrderListEngine;
import com.shopping.redboy.util.HttpClientUtil;

public class OrderListEngineImpl implements OrderListEngine {

	@Override
	public List<Order> getOrderInfo() {
		String mapping = "/orderlist";
		Map<String, String> params=new HashMap<String, String>();
		params.put("type", "1");
		List<Order> list = new ArrayList<Order>();
		try {
			HttpClientUtil httpclient = new HttpClientUtil();
			String result = httpclient.SendGet(mapping, params);
			JSONObject json = new JSONObject(result);
			String value = json.getString("response");
			String listview = json.getString("orderlist");

			JSONArray jsonArray = new JSONArray(listview);
			for (int i = 0; i < jsonArray.length(); i++) {
				Order order = new Order();
				JSONObject obj = (JSONObject) jsonArray.get(i);
				order.setOrderId(obj.getString("orderid"));
				order.setStatus(obj.getString("status"));
				order.setTime(obj.getString("time"));
				order.setPrice(obj.getDouble("price"));
				order.setFlag(obj.getInt("flag"));
				list.add(order);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
