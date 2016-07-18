package com.shopping.redboy.engine.Impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.domain.UserInfo;
import com.shopping.redboy.engine.RegistEngine;
import com.shopping.redboy.util.HttpClientUtil;

public class RegistEngineImpl implements RegistEngine {

	@Override
	public int getId(UserInfo user) {
		HttpClientUtil clientUtil = new HttpClientUtil();
		Map<String, String> params = new HashMap<String, String>();
		if(user != null){
			params.put("username", user.getUsername());
			params.put("password", user.getPassword());
		}

		String json = clientUtil.sendPost("/register",
				params);

		int result = 0;
		try {
			JSONObject object = new JSONObject(json);
			
			if (checkResponse(object)) {
				String str = object.getString("userinfo");
				JSONObject object2 = new JSONObject(str);
				result = object2.getInt("userId");
			}else{
				return -1;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 判断服务端的返回值
	 * @param object
	 * @return
	 */
	private boolean checkResponse(JSONObject object) {
		try {
			String response = object.getString("response");
			if("error".equals(response)){
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}

}
