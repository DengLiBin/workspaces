package com.itheima.redbaby.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.itheima.redbaby.bean.order.AddressInfo;

public class PaymentInfo implements Serializable{

	private AddressInfo addressInfo;
	private String payment_info;
	private String delivery_info;
	private List<Product> productlist;
	private String[] checkout_prom;
	private Map<String, String> checkout_addup;

	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(String payment_info) {
		this.payment_info = payment_info;
	}

	public String getDelivery_info() {
		return delivery_info;
	}

	public void setDelivery_info(String delivery_info) {
		this.delivery_info = delivery_info;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}

	public String[] getCheckout_prom() {
		return checkout_prom;
	}

	public void setCheckout_prom(String[] checkout_prom) {
		this.checkout_prom = checkout_prom;
	}

	public Map<String, String> getCheckout_addup() {
		return checkout_addup;
	}

	public void setCheckout_addup(Map<String, String> checkout_addup) {
		this.checkout_addup = checkout_addup;
	}

}
