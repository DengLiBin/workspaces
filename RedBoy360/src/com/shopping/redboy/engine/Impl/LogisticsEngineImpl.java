package com.shopping.redboy.engine.Impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.shopping.redboy.domain.Logistics;
import com.shopping.redboy.engine.LogisticsEngine;
import com.shopping.redboy.util.HttpClientUtil;

public class LogisticsEngineImpl implements LogisticsEngine {

	@Override
	public Logistics getLogistic() {

		String mapping = "/logistics";
		try {
			String result = new HttpClientUtil().SendGet(mapping, null);
			JSONObject json = new JSONObject(result);
			String obj = json.getString("logistics");
			JSONObject value = new JSONObject(obj);
			Logistics logistics = new Logistics();
			logistics.setExpressway(value.getString("expressway"));
			logistics.setLogisticscorp(value.getString("logisticscorp"));
			logistics.setLogisticsid(value.getString("logisticsid"));
			List<String> listInfo = new ArrayList<String>();
			JSONArray array = new JSONArray(value.getString("list"));
			for (int i = 0; i < array.length(); i++) {
				listInfo.add(array.getString(i));
			}
			logistics.setLogisticsInfo(listInfo);
			return logistics;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
