package com.shopping.redboy.dao.Interface;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	long insert(T t);
	int delete(Serializable id);
	int update(T t);
	List<T> findAll();
}
