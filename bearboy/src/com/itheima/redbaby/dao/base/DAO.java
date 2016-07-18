package com.itheima.redbaby.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * 实体操作的通用接口
 * @author Administrator
 *
 */
public interface DAO<M> {
	/**
	 * 增加
	 * @param m
	 * @return
	 */
	long insert(M m);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Serializable id);//int long String
	/**
	 * 修改
	 * @param m
	 * @return
	 */
	int update(M m);
	/**
	 * 查询
	 * @return
	 */
	List<M> findAll();
}
