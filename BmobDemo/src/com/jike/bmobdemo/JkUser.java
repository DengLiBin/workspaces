package com.jike.bmobdemo;

import cn.bmob.v3.BmobUser;

public class JkUser extends BmobUser {

	private String jikeNick;
	private Integer number;

	public String getJikeNick() {
		return jikeNick;
	}

	public void setJikeNick(String jikeNick) {
		this.jikeNick = jikeNick;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
