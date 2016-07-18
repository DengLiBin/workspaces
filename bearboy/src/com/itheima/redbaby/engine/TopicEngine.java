package com.itheima.redbaby.engine;

import java.util.List;

import com.itheima.redbaby.bean.Product;

/**
 * 处理专题模块的业务类
 * @author zhangyun
 *
 */
public interface TopicEngine {

	/**
	 * 获取新品
	 * @param pageNum 第几页
	 * @param offset 每页个数
	 * @return 返回商品列表
	 */
	public List<Product> getNewProduct(int pageNum,int offset);
	
	/**
	 * 获取热门单品
	 * @param pageNum 第几页
	 * @param offset  每页的个数
	 * @return 返回热门单品
	 */
	public List<Product> getHotProduct(int pageNum,int offset);

	/**
	 * 获取品牌的项目
	 * @param pageNum
	 * @param offset
	 * @param brandItem
	 * @return
	 */
	public Object getBrandItem(Integer pageNum, Integer offset,
			Integer brandItem);

}
