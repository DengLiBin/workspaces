package com.shopping.redboy.domain;

import java.util.List;
import java.util.Map;

/**
 * 订单详情
 * 
 * @Description TODO
 * @author liang
 * @date 2014-4-16 下午4:57:47
 */
public class OrderDetail {
	/**
	 * 支付方式,1=>货到付款 2=>货到POS机 3=>支付宝(待定)
	 */
	private int payment_info;
	/**
	 * 送货时间,1 => 周一至周五送货 2=> 双休日及公众假期送货 3=> 时间不限，工作日双休日及公众假期均可送货
	 */
	private int delivery_info;
	/**
	 * 享受促销信息
	 */
	private List<String> checkout_prom;
	/**
	 * 商品列表
	 */
	private List<OrderProd> productInfo;
	/**
	 * 地址信息
	 */
	private Address addressInfo;
	/**
	 * 发票信息
	 */
	private Invoice invoiceInfo;
	/**
	 * 订单基本信息
	 */
	private Order orderInfo;
	/**
	 * 总计
	 */
	private Map<String,String> checkout_addup;


	public Map<String, String> getCheckout_addup() {
		return checkout_addup;
	}

	public void setCheckout_addup(Map<String, String> checkout_addup) {
		this.checkout_addup = checkout_addup;
	}

	public List<String> getCheckout_prom() {
		return checkout_prom;
	}

	public Invoice getInvoiceInfo() {
		return invoiceInfo;
	}

	public void setInvoiceInfo(Invoice invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}

	public int getDelivery_info() {
		return delivery_info;
	}

	public void setDelivery_info(int delivery_info) {
		this.delivery_info = delivery_info;
	}

	public int getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(int payment_info) {
		this.payment_info = payment_info;
	}

	public Address getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(Address addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Order getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(Order orderInfo) {
		this.orderInfo = orderInfo;
	}

	public void setCheckout_prom(List<String> checkout_prom) {
		this.checkout_prom = checkout_prom;
	}

	public List<OrderProd> getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(List<OrderProd> productInfo) {
		this.productInfo = productInfo;
	}



	
}
