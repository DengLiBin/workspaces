package com.itheima.redbaby.bean.order;

public class Payment {
	private String type;

	public String getType() {
		//1=>货到付款 2=>货到POS机
//        3=>支付宝(待定)

		if ("1".equals(type)) {
			return "货到付款";
		}
		if ("2".equals(type)) {
			return "货到POS机";
		}
		if ("3".equals(type)) {
			return "支付宝";
		}
		
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
