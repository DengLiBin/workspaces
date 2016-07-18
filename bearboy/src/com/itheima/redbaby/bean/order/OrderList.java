package com.itheima.redbaby.bean.order;

import java.util.List;

public class OrderList {
	private String response;
	private List<Order> orderlist;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<Order> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}

}
