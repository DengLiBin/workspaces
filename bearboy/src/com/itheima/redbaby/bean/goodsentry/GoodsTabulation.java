package com.itheima.redbaby.bean.goodsentry;

import java.util.List;

import com.itheima.redbaby.bean.Product;
/**
 * 商品列表的bean
 */
public class GoodsTabulation {
	private String response;
	private int list_count;
	private List<Product> productlist;
	public GoodsTabulation(String response, int list_count, List<Product> productlist) {
		super();
		this.response = response;
		this.list_count = list_count;
		this.productlist = productlist;
	}
	public GoodsTabulation() {
		super();
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getList_count() {
		return list_count;
	}
	public void setList_count(int list_count) {
		this.list_count = list_count;
	}
	public List<Product> getProductlist() {
		return productlist;
	}
	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
}
