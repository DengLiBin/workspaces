package com.shopping.redboy.domain;
/**
 * 发票信息
* @Description TODO
* @author liang
* @date 2014-4-16 下午6:09:26
 */
public class Invoice {
	//发票抬头
	private String title;
	//发票内容
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
