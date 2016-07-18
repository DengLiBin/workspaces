package com.itheima.redbaby.engine.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.order.AddressBean;
import com.itheima.redbaby.bean.order.AddressInfo;
import com.itheima.redbaby.engine.AddressEngine;
import com.itheima.redbaby.net.HttpClientUtil;
import com.itheima.redbaby.net.NetUtil;
import com.itheima.redbaby.utils.PromptManager;

public class AddressEngineImpl  extends BaseEngine implements AddressEngine {
	private  Map<String,String> cityMap;
	/**
	 *获取地址列表
	 */
	@Override
	public List<AddressInfo> getAddress() {
		try {
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			String jsonString = httpClientUtil.sendGet(ConstantValue.URI+"x_addresslist.php");//参数还没修改
			AddressBean obj=JSON.parseObject(jsonString, AddressBean.class);
			 List<AddressInfo> addresslist = obj.getAddresslist();
			return obj.getAddresslist();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 三级地址服务器交互
	 */
	@Override
	public  Map<String ,String> getAddressCity(Context context,String cityID) {
		
		if(NetUtil.checkNetWork(context)){
			Map<String, String> map = new HashMap<String, String>();
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			//获取服务器返回的字符串http://192.168.1.70:8078/x_addressarea.php?id=0
			
			String cityString = httpClientUtil.sendGet(ConstantValue.URI +"/x_addressarea.php?id="+cityID);//
			//解析,并添加到Map里面
			cityMap = new LinkedHashMap<String, String>();

			try {
				JSONObject result = new JSONObject(cityString);
				
				JSONArray array = result.getJSONArray("addressarea");
				for(int i = 0; i < array.length();i++){
					JSONObject jsonObject = array.getJSONObject(i);
					
					String key = jsonObject.getString("id");
					String value = jsonObject.getString("name");
//					cityMap.put(jsonObject.getString("id"),jsonObject.getString("name"));
					cityMap.put(key,value);
					
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return cityMap;
		}else {
			PromptManager.showNoNetWork(context);
		}
		return null;
	}
	
	@Override
	public String serveAddress(Map<String, String>[] serveAddressMap) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String cityString = httpClientUtil.sendPost(ConstantValue.URI +"/x_addresssave.php", serveAddressMap[0]);
		return cityString;
	}

	@Override
	public String deleteAddress(String id) {
//		String delete = httpClientUtil.sendGet(ConstantValue.URI +"?id="+id);//
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		httpClient.sendPost(ConstantValue.URI + "/x_addressdelete.php", params);
		return null;
	}


}
