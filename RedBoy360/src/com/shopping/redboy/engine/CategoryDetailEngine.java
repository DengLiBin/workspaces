package com.shopping.redboy.engine;

import java.util.List;
import java.util.Map;

import com.shopping.redboy.domain.Product;
import com.shopping.redboy.domain.ProductCategory;
import com.shopping.redboy.domain.ProductComment;
import com.shopping.redboy.domain.ProductProperties;
import com.shopping.redboy.domain.ProductProperties.shaixuanValue;

public interface CategoryDetailEngine {
	/**
	 * 获得服务器端的分类信息
	 * @return	分类信息的list集合
	 */
	List<ProductCategory> getCategoryInfo();
	
	/**
	 * 获得服务器端的商品列表的信息
	 * @param params	请求的参数，第几页，每页多少个等
	 * @return	从服务器返回的商品的list集合
	 */
	List<Product> getProductList(Map<String , String > params);
	
	/**
	 * 获得服务器端商品列表的筛选信息
	 * @return	返回的筛选信息的属性list集合
	 */
	List<ProductProperties> getShaixuanValue();
	
	/**
	 * 获得服务器端商品列表的详细信息
	 * @return	返回商品详情，一个product
	 */
	Product getProductDetail();
	
	/**
	 * 获得服务器端商品的评论信息
	 * @return 返回一个装着productComment的list集合
	 */
	List<ProductComment> getProductComment();
}
