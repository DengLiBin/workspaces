package com.shopping.redboy.engine.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.domain.ProductCategory;
import com.shopping.redboy.domain.ProductComment;
import com.shopping.redboy.domain.ProductProperties;
import com.shopping.redboy.domain.ProductProperties.shaixuanValue;
import com.shopping.redboy.engine.CategoryDetailEngine;
import com.shopping.redboy.util.HttpClientUtil;

public class CategoryDetailEngineImpl implements CategoryDetailEngine{
	/**
	 * 请求分类详情的mapping
	 */
	private String categoryMapping = "/category";
	/**
	 * 向服务器发送的请求的参数map
	 */
	private Map<String , String> map;
	
	@Override
	public List<ProductCategory> getCategoryInfo() {
		map = new HashMap<String, String>();
		map.put("version", "1.1");
		HttpClientUtil client = new HttpClientUtil();
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		String json = client.SendGet(categoryMapping, map);
		try {
			JSONObject jsonObj = new JSONObject(json);
			String response = (String)jsonObj.get("response");
			if(!"category".equals(response)){
				return null;
			}
			JSONArray jsonArray = jsonObj.getJSONArray("category");
			for(int i = 0 ; i< jsonArray.length() ; i++){
				ProductCategory category = new ProductCategory();
				JSONObject obj = (JSONObject) jsonArray.get(i);
				category.setId(obj.getLong("id"));
				category.setIsleafnode(obj.getBoolean("isleafnode"));
				category.setName(obj.getString("name"));
				category.setParentId(obj.getLong("parent_id"));
				category.setPic(obj.getString("pic"));
				category.setTag(obj.getString("tag"));
				list.add(category);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 请求商品列表的mapping
	 */
	private String productListMapping = "/productlist";
	@Override
	public List<Product> getProductList(Map<String, String> params) {
		params = new HashMap<String, String>();
		params.put("bendan", "123");
		HttpClientUtil client = new HttpClientUtil();
		String jsonStr = client.SendGet(productListMapping, params);
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			String response = jsonObj.getString("response");
			if(!"category_productlist".equals(response))
				return null;
			String productlist = jsonObj.getString("productlist");
			List<Product> list = JSON.parseArray(productlist, Product.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<ProductProperties> getShaixuanValue() {
		Map<String , String> params = new HashMap<String, String>();
		params.put("bendan", "123");
		HttpClientUtil client = new HttpClientUtil();
		String jsonStr = client.SendGet(productListMapping, params);
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			String response = jsonObj.getString("response");
			if(!"category_productlist".equals(response))
				return null;
			JSONArray jsonArray = jsonObj.getJSONArray("category_productlist");
			List<ProductProperties> list = new ArrayList<ProductProperties>();
			for(int i = 0 ; i<jsonArray.length() ; i++){
				ProductProperties properties = new ProductProperties();
				JSONObject obj = (JSONObject) jsonArray.get(i);
				properties.setKey(obj.getString("key"));
				JSONArray array = obj.getJSONArray("value");
				List<shaixuanValue> valueList = properties.getValueList();
				for(int j = 0 ;j<array.length() ; j++){
					shaixuanValue value = properties.new shaixuanValue();
					value.setId(((JSONObject)array.get(j)).getString("id"));
					value.setName(((JSONObject)array.get(j)).getString("name"));
					valueList.add(value);
				}
				list.add(properties);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String productDetail = "/product";
	@Override
	public Product getProductDetail() {
		Map<String , String> params = new HashMap<String, String>();
		params.put("bendan", "123");
		HttpClientUtil client = new HttpClientUtil();
		String jsonStr = client.SendGet(productDetail, params);
		Product product = new Product();
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			String response = jsonObj.getString("response");
			if(!"product".equals(response)){
				return null;
			}
//			String available = jsonObj.getString("available");
			product.setAvailable("YES");//设置是否可售
			String[] bigPic = new String[2];
//			JSONArray jsonBigPic = jsonObj.getJSONArray("bigPic");
			bigPic[0] = "http://192.168.1.4/ECServer_D/images/image3.png";
			bigPic[1] = "http://192.168.1.4/ECServer_D/images/image4.png";
			product.setBigPic(bigPic); //设置大图
			product.setBuyLimit(10);//设置购买商品上限
			product.setComment_count(520);//设置评论数量
			product.setId(101);//设置商品id
			product.setInventoryArea("喜马拉雅山脉地区");//设置存货地区
			product.setLeftTime(2000);//设置限时抢购剩余时间
			product.setLimitPrice(300);//设置限时抢购价
			product.setMarketprice(400);//设置市场价
			product.setName("什么什么奶粉");//设置商品名字
//			JSONArray jsonPicArray = jsonObj.getJSONArray("pic");
			String[] pics = new String[2];
			pics[0] ="http://192.168.1.4/ECServer_D/images/image1.png";
			pics[1] = "http://192.168.1.4/ECServer_D/images/image2.png";
			product.setPics(pics);//设置商品小图片
			product.setPrice(398);//设置会员价格
			Random random = new Random();
//			product = JSON.parseObject(jsonStr, Product.class);
			product.setScore(random.nextInt(3));//设置商品评分
			product.setNumber(random.nextInt(1000));
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String productComment = "/product/comment";
	@Override
	public List<ProductComment> getProductComment() {
		Map<String , String> params = new HashMap<String, String>();
		params.put("bendan", "123");
		HttpClientUtil client = new HttpClientUtil();
		String jsonStr = client.SendGet(productComment, params);
		List<ProductComment> list = new ArrayList<ProductComment>();
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			String comments = jsonObj.getString("comment");
			list = JSON.parseArray(comments, ProductComment.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	

}
