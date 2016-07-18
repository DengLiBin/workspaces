package com.itheima.redbaby.engine;

import java.util.List;
import java.util.Map;

import com.itheima.redbaby.bean.Product;

/**
 * 收藏夹的接口
 * @author Crist
 *
 */
public interface FavoriteInfoEngine {
	Map<String,List<Product>> getFavoriteInfo();
}
