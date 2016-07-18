package com.shopping.redboy.domain;

/**
 * 服务器返回数据
 */
public class Sinvoice {
	/**
	 * 商品编号
	 */
	private int id;
	/**
	 * 商品类型
	 */
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
