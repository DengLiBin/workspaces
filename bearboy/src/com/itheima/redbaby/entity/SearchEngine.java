package com.itheima.redbaby.entity;

import java.util.List;

import com.itheima.redbaby.bean.Product;

public interface SearchEngine {
	/**
	 * 获取热门搜索
	 * @return
	 */
	List<String> getRecommend();
	
	/**
	 * 根据关键字查询
	 * @param keyword 关键字 
	 * @param page 第几页
	 * @param pageNum 总页数
	 * @return 产品列表
	 */
	List<Product> search(String keyword,int page,int pageNum);
	
}
