package com.itheima.redbaby.engine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.GloableParameters;
import com.itheima.redbaby.bean.FavoriteInfo;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.FavoriteInfoEngine;
/**
 * 收藏夹的业务实现类
 * @author Administrator
 *
 */
public class FavoriteInfoEngineImpl extends BaseEngine implements
		FavoriteInfoEngine {

	@Override
	public Map<String, List<Product>> getFavoriteInfo() {
		Map<String, String> mp = new HashMap<String, String>();
		//TODO  不知道传什么数据过去
		int userID=GloableParameters.USER_ID;
		mp.put("UserId",String.valueOf(userID));
		String favoriteInfo = httpClient.sendPost(ConstantValue.URI +"x_favorites.php", mp);
		FavoriteInfo Favorite = JSONObject.parseObject(favoriteInfo,
				FavoriteInfo.class);
		if(favoriteInfo==null){
			return null;
		}
		
		List<Product> ProductInfo = Favorite.getFavoriteInfo();
		
		String list_count = Favorite.getList_count();
		Map<String, List<Product>> map = new HashMap<String, List<Product>>();
		map.put(list_count, ProductInfo);
		return map;
	}

}
