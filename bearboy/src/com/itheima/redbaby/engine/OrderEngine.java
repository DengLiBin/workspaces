package com.itheima.redbaby.engine;

import com.itheima.redbaby.bean.order.OrderList;

public interface OrderEngine {

	OrderList getOrderMsg(String type, String page, String pageNum, String userId);
}
