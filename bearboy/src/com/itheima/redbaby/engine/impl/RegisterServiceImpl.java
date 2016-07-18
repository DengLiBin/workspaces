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
import com.itheima.redbaby.engine.RegisterEngine;
import com.itheima.redbaby.net.HttpClientUtil;

/**
 * 注册服务类
 * @author zl
 *
 */

public class RegisterServiceImpl implements RegisterEngine {

	/**
	 * 从服务器获得数据
	 */
	@Override
	public UserInfo getServiceUserInfo(String username, String password) {
		List<UserInfo> userInfoList = null;
		HttpClientUtil util = new HttpClientUtil();

		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		
		String json = util.sendPost(ConstantValue.URI+ConstantValue.REGISTER, params);
		
		try {
			JSONObject object = new JSONObject(json);
			
			if(checkJson(object)){
				String userInfoStr = object.getString("userinfo");
				return JSON.parseObject(userInfoStr, UserInfo.class);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 检查json是否响应error
	 * @param object
	 * @return
	 */
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
	}
}
