package com.itheima.redbaby.bean;

import java.io.Serializable;

public class Version implements Serializable{
	private String response;
	private VersionItem version;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public VersionItem getVersion() {
		return version;
	}
	public void setVersion(VersionItem version) {
		this.version = version;
	}
	

}
