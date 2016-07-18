package com.itheima.redbaby.bean.order;

import java.io.Serializable;

/**
 * 发票
 * 
 * @author my
 * 
 */
public class Invoice implements Serializable {
	private String title;
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
