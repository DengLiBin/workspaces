package com.itheima.redbaby.bean.order;

import java.util.List;

public class AddressBean {
	private String response;
	private List<AddressInfo> addresslist;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public List<AddressInfo> getAddresslist() {
		return addresslist;
	}
	public void setAddresslist(List<AddressInfo> addresslist) {
		this.addresslist = addresslist;
	}
	
}
