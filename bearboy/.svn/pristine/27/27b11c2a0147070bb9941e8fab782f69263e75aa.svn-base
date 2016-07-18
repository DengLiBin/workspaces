package com.itheima.redbaby.ui.manager;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.bean.Product;

public class ShoppingCart {
	private ShoppingCart() {
	}

	private static ShoppingCart shoppingCart = new ShoppingCart();
	private List<Product> products = new ArrayList<Product>();

	public static ShoppingCart getInstance() {
		return shoppingCart;
	}

	public List<Product> getCart() {
		return products;
	}

	public void setCart(List<Product> products) {
		this.products = products;
	}

	private List<Product> pros = new ArrayList<Product>();

	public List<Product> getPros() {
		return pros;
	}
}
