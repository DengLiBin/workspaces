package com.itheima.redbaby.bean;

import java.util.List;

public class Cart {
	private List<CartItem> cartItems;
	private String[] prom;
	private int totalCount;
	private float totalPrice;
	private float totalPoint;
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public String[] getProm() {
		return prom;
	}
	public void setProm(String[] prom) {
		this.prom = prom;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public float getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(float totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	
}
