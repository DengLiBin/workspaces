package com.shopping.redboy.domain;

/**
 * 提交结算的信息
 * @author Admin
 *
 */
public class SorderSubmit {
	/**
	 * 商品ID
	 */
	private String sku;
	/**
	 * 地址ID
	 */
	private String addressid;
	/**
	 * 支付方式
	 */
	private String paymentid;
	/**
	 * 送货时间
	 */
	private String deliveryid;
	/**
	 * 发票类型
	 */
	private String invoicetype;
	/**
	 * 发票抬头
	 */
	private String invoicetitle;
	/**
	 * 发票内容
	 */
	private String invoicecontent;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getDeliveryid() {
		return deliveryid;
	}
	public void setDeliveryid(String deliveryid) {
		this.deliveryid = deliveryid;
	}
	public String getInvoicetype() {
		return invoicetype;
	}
	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}
	public String getInvoicetitle() {
		return invoicetitle;
	}
	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle = invoicetitle;
	}
	public String getInvoicecontent() {
		return invoicecontent;
	}
	public void setInvoicecontent(String invoicecontent) {
		this.invoicecontent = invoicecontent;
	}
}
