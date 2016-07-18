package com.itheima.redbaby.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.test.AndroidTestCase;

import com.itheima.redbaby.bean.Brand;
import com.itheima.redbaby.bean.PaymentInfo;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.Promotion;
import com.itheima.redbaby.engine.AddressEngine;
import com.itheima.redbaby.engine.BrandEngine;
import com.itheima.redbaby.engine.PromotionEngine;
import com.itheima.redbaby.engine.ShoppingSpringEngine;
import com.itheima.redbaby.engine.TopicEngine;
import com.itheima.redbaby.engine.impl.AddressEngineImpl;
import com.itheima.redbaby.engine.impl.CartShopppingEngineImpl;
import com.itheima.redbaby.engine.impl.PaymentEngineImpl;
import com.itheima.redbaby.net.HttpClientUtil;
import com.itheima.redbaby.utils.BeanFactory;



public class TestEngine extends AndroidTestCase {
	
	public void test(){
		PromotionEngine promotionEngine = BeanFactory.getFactory().getInstance(PromotionEngine.class);
		List<Promotion> promotionList = promotionEngine.getPromotionList();
		System.out.println(promotionEngine);
	}

	public void test2(){
		BrandEngine engine = BeanFactory.getFactory().getInstance(BrandEngine.class);
		@SuppressWarnings("unused")
		List<Brand> brand = engine.getBrand();
		System.out.println("brand");
				
	}
	public void test3(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("page", "1");
		map.put("pageNum", "10");
		map.put("orderby", "comment_down");
		map.put("cId", "3");
		HttpClientUtil hc=new HttpClientUtil();
		String uri="HTTP://192.168.1.59:8078/x_search_product_list.php";
		String sendPost = hc.sendPost(uri, map);
		System.out.println(sendPost);
	}
	
	public void test4(){
		ShoppingSpringEngine engine = BeanFactory.getFactory().getInstance(ShoppingSpringEngine.class);
		List<Product> productList = engine.getShoppingSpring();
		System.out.println(productList);
	}
	public void test5(){
		PaymentInfo paymentInfo = new  PaymentEngineImpl().getPaymentInfo(getContext());
		System.out.println();
	}
	
	public void test6(){
		TopicEngine topicEngine = BeanFactory.getFactory().getInstance(TopicEngine.class);
		List<Product> newProduct = topicEngine.getNewProduct(0, 10);
		System.out.println(newProduct);
	}

	public void test7(){
		PromotionEngine promotionEngine = BeanFactory.getFactory().getInstance(PromotionEngine.class);
		List<Promotion> promotionList = promotionEngine.getPromotionList();
		System.out.println(promotionList);
	}
	
	public void test8(){
		PromotionEngine promotionEngine = BeanFactory.getFactory().getInstance(PromotionEngine.class);
		List<Product> promotionProduct = promotionEngine.getPromotionProduct(1, 10, 1);
		System.out.println(promotionProduct);
	}
	
	public void test9(){
		PromotionEngine promotionEngine = BeanFactory.getFactory().getInstance(PromotionEngine.class);
		List<Promotion> promotionList = promotionEngine.getPromotionList();
		System.out.println(promotionList);
	}
	
	public void test10(){
		AddressEngine addressEngine = new AddressEngineImpl();
		addressEngine.deleteAddress("24");
	}

}
