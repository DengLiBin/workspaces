package com.itheima.redbaby.bean.home;

import java.util.ArrayList;
import java.util.List;

public class HomeBean {
	/**
	 * 回应信息
	 */
	private String response;
	/**
	 * 各种图片
	 */
	private List<Banner> home_banner;

	public void setHome_banner(List<Banner> home_banner) {
		this.home_banner = home_banner;
	}

	public List<Banner> getHome_banner() {
		return home_banner;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
