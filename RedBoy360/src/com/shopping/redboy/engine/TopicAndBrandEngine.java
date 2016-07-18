package com.shopping.redboy.engine;

import java.util.List;
import java.util.Map;

import com.shopping.redboy.domain.Brand;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.domain.Topic;


public interface TopicAndBrandEngine {
	/**
	 * 获取促销快报
	 * 
	 *
	 * @return
	 */
	List<Topic> getPromotionList();

	/**
	 * 获取专题商品列表信息
	 * 
	 * @return
	 */
	List<Product> getTopicItemList();

	/**
	 * 获取推荐品牌
	 * 
	 * @return
	 */
	Map<String, List<Brand>> getBrandMap();

	/**
	 * 获取品牌商品列表信息
	 * 
	 * @return
	 */
	List<Product> getBrandItemList();

	/**
	 * 获取限时抢购商品列表
	 * 
	 * @return
	 */
	List<Product> getLimitBuyList();

	/**
	 * 获取新品列表
	 * 
	 * @return
	 */
	List<Product> getNewItemList();

	/**
	 * 获取热门单品列表
	 * 
	 * @return
	 */
	List<Product> getHotItemList();

}
