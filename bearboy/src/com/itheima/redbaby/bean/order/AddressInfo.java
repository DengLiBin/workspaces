package com.itheima.redbaby.bean.order;

import java.io.Serializable;

/**
 * 地址信息
 * @author my
 *
 */
public class AddressInfo implements Serializable{
	/**
	 * 地址簿ID
	 */
	private String id;
	/**
	 * 收货人姓名
	 */
	private String name;
	/**
	 * 手机
	 */
	private String phonenumber;
	/**
	 * 固话
	 */
	private String fixedtel;
	/**
	 * 邮编
	 */
	private String zipCode;
	/**
	 * 国家三级地址
	 */
	private String areaId;//地区
	private String areaDetail;//详情
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getFixedtel() {
		return fixedtel;
	}
	public void setFixedtel(String fixedtel) {
		this.fixedtel = fixedtel;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaDetail() {
		return areaDetail;
	}
	public void setAreaDetail(String areaDetail) {
		this.areaDetail = areaDetail;
	}

	
	

	

}
