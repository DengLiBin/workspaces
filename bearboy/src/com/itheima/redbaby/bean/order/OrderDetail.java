package com.itheima.redbaby.bean.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单详细列表
 * 
 * @author my
 * 
 */
public class OrderDetail implements Serializable {
	/**
	 * 请求内容
	 */
	private String response;
	/**
	 * 订单简明信息
	 */
	private Order order_info;
	/**
	 * 地址
	 */
	private AddressInfo address_info;
	/**
	 * 支付方式
	 * <p>
	 * 支付类型，1=>货到付款 2=>货到POS机 3=>支付宝(待定)
	 */
	private Payment payment_info;
	/**
	 * 送货时间
	 */
	private Delivery delivery_info;
	/**
	 * 发票
	 */
	private Invoice invoice_info;
	/**
	 * 商品列表
	 */
	private List<OrderProduct> productlist;
	/**
	 * 享受促销信息
	 */
	private List<String> checkout_prom;

	private SumUp checkout_addup;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Order getOrder_info() {
		return order_info;
	}

	public void setOrder_info(Order order_info) {
		this.order_info = order_info;
	}

	public AddressInfo getAddress_info() {
		return address_info;
	}

	public void setAddress_info(AddressInfo address_info) {
		this.address_info = address_info;
	}

	public Payment getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(Payment payment_info) {
		this.payment_info = payment_info;
	}

	public Delivery getDelivery_info() {
		return delivery_info;
	}

	public void setDelivery_info(Delivery delivery_info) {
		this.delivery_info = delivery_info;
	}

	public Invoice getInvoice_info() {
		return invoice_info;
	}

	public void setInvoice_info(Invoice invoice_info) {
		this.invoice_info = invoice_info;
	}

	public List<OrderProduct> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<OrderProduct> productlist) {
		this.productlist = productlist;
	}

	public List<String> getCheckout_prom() {
		return checkout_prom;
	}

	public void setCheckout_prom(List<String> checkout_prom) {
		this.checkout_prom = checkout_prom;
	}

	public SumUp getCheckout_addup() {
		return checkout_addup;
	}

	public void setCheckout_addup(SumUp checkout_addup) {
		this.checkout_addup = checkout_addup;
	}

}
