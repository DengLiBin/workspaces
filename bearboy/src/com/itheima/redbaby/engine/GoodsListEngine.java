package com.itheima.redbaby.engine;

import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.goodsentry.GoodsTabulation;

/**
 * 商品列表的业务类 
 * @author qw
 *
 */
public interface GoodsListEngine {
	/**
	 * 获取HOME信息
	 * 
	 * @return
	 */
	public GoodsTabulation getGoodsList(String cId,String page,String pageNum,String srot);
	/**
	 * 获取详情
	 */
	public Product getProduct(String id);
}
