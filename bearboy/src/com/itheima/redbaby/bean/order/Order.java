package com.itheima.redbaby.bean.order;

import java.io.Serializable;

/**
 * 订单简要信息
 * 
 * @author my
 * 
 */
public class Order implements Serializable {
	// 订单ID -->
	private String orderid;
	// 订单显示状态 -->
	private String status;
	// 下单时间 -->
	private String time;
	// 订单金额 -->
	private String price;
	// 订单标识，1=>可删除可修改 2=>不可修改 3=>已完成 -->
	private String flag;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFlag() {
		// 1=>可删除可修改 2=>不可修改 3=>已完成
		if ("1".equals(flag)) {
			return "可删除可修改";
		}
		if ("2".equals(flag)) {
			return "不可修改";
		}
		if ("3".equals(flag)) {
			return "已完成";
		}
		return "";
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
