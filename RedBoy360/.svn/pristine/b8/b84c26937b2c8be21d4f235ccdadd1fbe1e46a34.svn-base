package com.shopping.redboy.engine.Impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.redboy.domain.UserInfo;
import com.shopping.redboy.engine.AccountEngine;
import com.shopping.redboy.util.HttpClientUtil;

public class AccountEngineImpl implements AccountEngine {

	@Override
	public UserInfo getUser() {
		HttpClientUtil clientUtil = new HttpClientUtil();
		Map<String, String> params = new HashMap<String, String>();
		String json = clientUtil.SendGet("/userinfo", params);
		UserInfo user = new UserInfo();
		try {
			JSONObject object = new JSONObject(json);
			if (checkResponse(object)) {
				String json2 = object.getString("userinfo");
				JSONObject object2 = new JSONObject(json2);
				user.setBonus(object2.getInt("bonus"));
				user.setLevel(object2.getString("level"));
				user.setUserId(object2.getInt("userId"));
				user.setUsersession(object2.getString("usersession"));
				user.setOrdercount(object2.getString("ordercount"));
				user.setFavoritescount("favoritescount");
			}else{
				return null;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 判断服务端的返回值
	 * 
	 * @param object
	 * @return
	 */
	private boolean checkResponse(JSONObject object) {
		try {
			String response = object.getString("response");
			if ("error".equals(response)) {
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}

}
