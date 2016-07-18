package com.shopping.redboy.domain;

import java.util.Date;

/**
 * 订单信息
* @Description TODO
* @author liang
* @date 2014-4-16 下午4:49:46
 */
public class Order {
	 //订单ID
	private String orderId;
	//订单显示状态
	private String status;
	//下单时间
	private String time;
	//订单金额
	private double price;
	//订单标识，1=>可删除可修改   2=>不可修改   3=>已完成
	private int flag;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	public Order(){
		
	}
	
}
