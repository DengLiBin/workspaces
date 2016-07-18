package com.shopping.redboy.net;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shopping.redboy.domain.ProductComment;
import com.shopping.redboy.engine.CategoryDetailEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.HttpClientUtil;

import android.test.AndroidTestCase;

public class TestAccount extends AndroidTestCase {

	public void textacc(){
		HttpClientUtil clientUtil = new HttpClientUtil();
		Map<String, String> params = new HashMap<String, String>();
		params.put( "userId", "30505");
		params.put( "bonus", " 3002");
		String json = clientUtil.SendGet("/userinfo", null );
		System.out.println(json);
	}
	
	public void testProductComment(){
		CategoryDetailEngine engine = BeanFactory.getImpl(CategoryDetailEngine.class);
		List<ProductComment> productComments = engine.getProductComment();
		for(ProductComment comment : productComments){
			System.out.println(comment);
		}
	}
}
