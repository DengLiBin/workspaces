package com.itheima.redbaby.engine;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.goodsentry.GoodsTabulation;
import com.itheima.redbaby.net.HttpClientUtil;

public interface SearchEngine {
	/**
	 * 获取热门搜索
	 * 
	 * @return
	 */
	List<String> getRecommend();

	/**
	 * 根据关键字查询
	 * 
	 * @param keyword
	 *            关键字
	 * @param page
	 *            第几页
	 * @param pageNum
	 *            总页数
	 * @return 产品列表
	 */
	List<Product> search(String keyword, int page, int pageNum);
	
	/**
	 * 如果你在上一步查出来所有商品，下一步想获取服务器中某类商品的总数，就调用此方法
	 * 为什么会有这个方法：因为这个属性服务器返回后无法封装到实体中，因为它本质上不属于任何实体
	 * @return
	 */
	public int getProductCount();
	
	
	//----------------------------------------------------------
	
	public static class SearchParam
	{
		
		
		private String keyword;
		private int page;
		private int pageNum;
		
		public SearchParam() {
			super();
		}
		public SearchParam(String keyword, int page, int pageNum, int orderby) {
			super();
			this.keyword = keyword;
			this.page = page;
			this.pageNum = pageNum;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}		
	}
	
	
}
