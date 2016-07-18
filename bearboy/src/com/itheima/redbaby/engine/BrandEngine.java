package com.itheima.redbaby.engine;

import java.util.List;

import com.itheima.redbaby.bean.Brand;

/**
 * 品牌的业务类
 * @author zhangyun
 *
 */
public interface BrandEngine {
	/**
	 * 获取所有的品牌
	 * @return
	 */
	public List<Brand> getBrand();
}
