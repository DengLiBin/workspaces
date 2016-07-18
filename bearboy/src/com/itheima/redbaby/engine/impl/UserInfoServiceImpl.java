package com.itheima.redbaby.engine.impl;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.UserInfo;
import com.itheima.redbaby.engine.UserInfoEngine;

public class UserInfoServiceImpl implements UserInfoEngine {

	@Override
	public List<UserInfo> getServiceProdects() {
		List<UserInfo> result;

		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				ConstantValue.URI+ConstantValue.USERINFO);

		HttpResponse httpResponse;

		try {
			httpResponse = client.execute(httpGet);
			int s = httpResponse.getStatusLine().getStatusCode();
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String json = EntityUtils.toString(httpResponse.getEntity());

				JSONObject object = new JSONObject(json);

				if (checkJson(object)) {
					String userinfoStr = object.getString("userinfo");

					// String text = ...; // [{ ... }, { ... }]
					// List<User> users = JSON.parseArray(text, User.class);

					result = JSON.parseArray(userinfoStr, UserInfo.class);

					return result;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean checkJson(JSONObject object) {
		/**
		 * { "response": "error", "error": { "text": "用户名不存在" } }
		 */
		try {
			String response = object.getString("response");
			if (!"error".equals(response)) {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

}
