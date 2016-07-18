package com.itheima.redbaby.bean.order;

public class Delivery {
	private String type;

	public String getType() {
		// 1 => 周一至周五送货 2=> 双休日及公众假期送货 3=> 时间不限，工作日双休日及公众假期均可送货
		if ("1".equals(type)) {
			return "周一至周五送货";
		}
		if ("2".equals(type)) {
			return "双休日及公众假期送货";
		}
		if ("3".equals(type)) {
			return "时间不限，工作日双休日及公众假期均可送货";

		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
