package com.itheima.redbaby.engine.impl;

import java.util.Map;

import org.json.JSONObject;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.net.HttpClientUtil;

public abstract class BaseEngine {
	protected HttpClientUtil httpClient;

	public BaseEngine() {
		httpClient = new HttpClientUtil();
	}

	/**
	 * 返回net层获取数据
	 * 
	 * @param uri
	 *            要访问的地址
	 * @param params
	 *            返回需要的参数
	 * @param resultKey
	 *            访问结果的key值
	 * @return 返回访问的结果json
	 * @throws Exception
	 */
	protected String accessNet(String uri, Map<String, String> params, String resultKey) throws Exception {
		JSONObject jsonObject = null;
		if (params != null && params.size() > 0) {
			String result = httpClient.sendPost(uri, params);
			jsonObject = new JSONObject(result);
		} else {
			String result = httpClient.sendGet(uri);
			jsonObject = new JSONObject(result);
		}

		if (jsonObject != null) {
			String errorInfo = checkErrorInfo(jsonObject);
			// 返回正确数据
			if (errorInfo == null) {
				return jsonObject.getString(resultKey);
			} else {// 返回错误数据
				return errorInfo;
			}
		}
		return null;
	}

	/**
	 * 检查当前返回的json数据
	 * 
	 * @param jsonObject
	 *            返回的json
	 * @return 错误信息,如果结果为null则返回正确的数据
	 */
	protected String checkErrorInfo(JSONObject jsonObject) {
		if (jsonObject != null) {
			try {
				if (ConstantValue.SERVERERROR.equals(jsonObject.getString("response"))) {
					return jsonObject.getString("text");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
