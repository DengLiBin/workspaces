package com.itheima.redbaby.engine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.UserInfo;
import com.itheima.redbaby.engine.LoginEngine;
import com.itheima.redbaby.net.HttpClientUtil;

public class LoginServiceImpl extends BaseEngine implements LoginEngine {
	
	@Override
	public UserInfo getServiceUserInfo(String username,String password) {
		List<UserInfo> userInfoList;
		
		HttpClientUtil util = new HttpClientUtil();
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("username", username);
//		params.put("password", password);
//		String json = util.sendPost(ConstantValue.URI + ConstantValue.LOGIN, params);
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		try {
			String json = accessNet(ConstantValue.URI + ConstantValue.LOGIN, params, "userinfo");
			return JSON.parseObject(json, UserInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		/*try {
			JSONObject object = new JSONObject(json);
			
			if(checkJson(object)){
				String result = (String) object.get("userinfo");
				//String userInfoStr = object.getString("userinfo");
				//userInfoList = JSON.parseArray(UserInfo, UserInfo.class);
				
				return JSON.parseObject(result, UserInfo.class);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private boolean checkJson(JSONObject object) {
		try {
			String response = object.getString("response");
			if (!"error".equals(response)) {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}*/
	}
}

