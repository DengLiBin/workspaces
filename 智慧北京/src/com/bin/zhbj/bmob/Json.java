package com.bin.zhbj.bmob;

import cn.bmob.v3.BmobObject;

public class Json extends BmobObject {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	private String json;
}
