package com.itheima.redbaby.bean.order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProduct implements Serializable {
	private String id;
	private String name;
	private String pic;
	private String price;
	private List<OrderAttribute> product_property;
	private String number;
	private String uplimit;
	private String isgift;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<OrderAttribute> getProduct_property() {
		return product_property;
	}

	public void setProduct_property(List<OrderAttribute> product_property) {
		this.product_property = product_property;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUplimit() {
		return uplimit;
	}

	public void setUplimit(String uplimit) {
		this.uplimit = uplimit;
	}

	public String getIsgift() {
		if ("1".equals(isgift)) {
			return "是";
		}
		if ("2".equals(isgift)) {
			return "否";
		}
		return isgift;
	}

	public void setIsgift(String isgift) {
		this.isgift = isgift;
	}

}
