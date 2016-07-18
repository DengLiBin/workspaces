package com.shopping.redboy.engine.Impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;

import com.shopping.redboy.domain.Brand;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.domain.Topic;
import com.shopping.redboy.engine.TopicAndBrandEngine;
import com.shopping.redboy.util.BitmapUtil;
import com.shopping.redboy.util.HttpClientUtil;
import com.shopping.redboy.util.JSONUtil;
import com.shopping.redboy.util.NetUtil;

public class TopicAndBrandEngineImpl implements TopicAndBrandEngine {
	private Context context;

	public TopicAndBrandEngineImpl(Context context) {
		super();
		this.context = context;
	}

	/**
	 * 促销快报
	 */
	private final static String TOPIC_LIST_URL = "/topic";
	/**
	 * 专题商品列表
	 */
	private final static String TOPIC_ITEM_LIST_URL = "/topic/plist";
	/**
	 * 推荐品牌
	 */
	private final static String BRAND_LIST_URL = "/brand";
	/**
	 * 品牌商品列表
	 */
	private final static String BRAND_ITEM_LIST_URL = "/brand/plist";
	/**
	 * 限时抢购
	 */
	private final static String LIMIT_BUY_LIST_URL = "/limitbuy";
	/**
	 * 新品上架
	 */
	private final static String NEW_ITEM_LIST_URL = "/newproduct";
	/**
	 * 热门单品
	 */
	private final static String HOT_ITEM_LIST_URL = "/hotproduct";

	@Override
	public List<Topic> getPromotionList() {
		return getTopicList(TOPIC_LIST_URL);
	}

	@Override
	public List<Product> getTopicItemList() {
		return getProductList(TOPIC_ITEM_LIST_URL);
	}

	@Override
	public Map<String, List<Brand>> getBrandMap() {
		try {
			Map<String, List<Brand>> map = new LinkedHashMap<String, List<Brand>>();
			String json = new HttpClientUtil().SendGet(BRAND_LIST_URL, null);
			JSONArray jsonArray = new JSONObject(json).getJSONArray("brand");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jobj = (JSONObject) jsonArray.get(i);
				List<Brand> list = JSONUtil.getList_New(jobj.toString(),
						Brand.class, "value");
				map.put(jobj.getString("key"), list);
			}
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Product> getBrandItemList() {
		return getProductList(BRAND_ITEM_LIST_URL);
	}

	@Override
	public List<Product> getLimitBuyList() {
		return getProductList(LIMIT_BUY_LIST_URL);
	}

	@Override
	public List<Product> getNewItemList() {
		return getProductList(NEW_ITEM_LIST_URL);
	}

	@Override
	public List<Product> getHotItemList() {
		return getProductList(HOT_ITEM_LIST_URL);
	}

	/**
	 * 获取Topic信息集合
	 * 
	 * @param url
	 * @return
	 */
	private List<Topic> getTopicList(String url) {
		if (NetUtil.checkNet(context)) {
			String json = new HttpClientUtil().SendGet(url, null);
			List<Topic> list = JSONUtil.getList_New(json, Topic.class, "topic");

			for (Topic item : list) {
				try {
					Bitmap bitMap = BitmapUtil.getBitMapFormURL(item.getPic());
					item.setBitmap(bitMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;

		}
		return null;
	}

	/**
	 * 获取Product集合
	 * 
	 * @param url
	 * @return
	 */
	private List<Product> getProductList(String url) {
		if (NetUtil.checkNet(context)) {
			String json = new HttpClientUtil().SendGet(url, null);
			return JSONUtil.getList_New(json, Product.class, "productlist");
		}
		return null;
	}

}
