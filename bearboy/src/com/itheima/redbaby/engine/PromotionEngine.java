package com.itheima.redbaby.engine;

import java.util.List;

import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.Promotion;

/**
 * 促销信息的业务接口
 * @author zhangyun
 *
 */
public interface PromotionEngine {
	/**
	 * 获取所有的促销信息
	 * @return
	 */
	List<Promotion> getPromotionList();

	/**
	 * 获取促销的商品
	 * @param pageNum 
	 * @param offset
	 * @param id
	 * @return
	 */
	List<Product> getPromotionProduct(Integer pageNum, Integer offset,
			Integer id);
}
