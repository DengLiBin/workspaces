package com.itheima.redbaby.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.test.AndroidTestCase;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.bean.ClassifyFist;
import com.itheima.redbaby.bean.CommodityClassify;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.goodsentry.GoodsTabulation;
import com.itheima.redbaby.dao.HistorySearchDBHelper;
import com.itheima.redbaby.dao.HistorySearchDao;
import com.itheima.redbaby.engine.impl.SearchEngineImpl;
import com.itheima.redbaby.net.HttpClientUtil;

public class QWTest extends AndroidTestCase {
	public void test3() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", "1");
		map.put("pageNum", "10");
		map.put("orderby", "comment_down");
		map.put("cId", "3");
		map.put("moneyMin", "0");
		map.put("moneyMax", "1");
		HttpClientUtil hc = new HttpClientUtil();
		String uri = "HTTP://192.168.1.70:8078/x_search_product_list.php";
		String sendPost = hc.sendPost(uri, map);
		System.out.println(sendPost);
	}

	public void test2() {
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("page", "1");
		// map.put("pageNum", "10");
		// map.put("orderby", "comment_down");
		// map.put("id", "3");
		HttpClientUtil hc = new HttpClientUtil();
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", "1");
		String uri = "http://192.168.1.20/Jerry_json.php";
		String sendGet = hc.sendGet(uri);
		System.out.println(sendGet);
		GoodsTabulation parseObject = JSON.parseObject(sendGet, GoodsTabulation.class);

		String str = "dsafdsa";
	}

	public void test4() {
		Map<String, String> map = new HashMap<String, String>();
		// map.put("page", "1");
		// map.put("pageNum", "10");
		// map.put("orderby", "comment_down");
		// map.put("cId", "3");
		HttpClientUtil hc = new HttpClientUtil();
		String uri = "HTTP://192.168.1.59:8078/x_fenlei1.php";
		String sendPost = hc.sendGet(uri);
		CommodityClassify parseObject = JSON.parseObject(sendPost, CommodityClassify.class);
		List<ClassifyFist> category = parseObject.getCategory();
		System.out.println(sendPost);
		System.out.println(category.get(0).getId());
		System.out.println();
		System.out.println(category.get(0).getBigname());
	}

	public void test5() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		HttpClientUtil hc = new HttpClientUtil();
		String uri = "HTTP://192.168.1.59:8078/x_fenlei2.php";
		String sendPost = hc.sendPost(uri, map);
		System.out.println(sendPost);
		// CommodityClassify parseObject = JSON.parseObject(sendPost, CommodityClassify.class);
		// List<ClassifyFist> category = parseObject.getCategory();
		// System.out.println(category.get(0).getId());
		// System.out.println();
		// System.out.println(category.get(0).getBigname());

	}

	public void test6() {
		SearchEngineImpl impl = new SearchEngineImpl();
		List<Product> goodsTabulationList = impl.getGoodsTabulationList("1");
		for (Product product : goodsTabulationList) {
			String string = goodsTabulationList.toString();
			System.out.println(string);
		}
	}

	public void test7() {
		SearchEngineImpl impl = new SearchEngineImpl();
		List<Product> goodsTabulationList = impl.getGoodsMethod("口");
		for (Product product : goodsTabulationList) {
			String string = goodsTabulationList.toString();
			System.out.println(string);
		}
	}

	public void text7() {
		HistorySearchDao dao = new HistorySearchDao(getContext());
		dao.deleteAll();
	}
}
