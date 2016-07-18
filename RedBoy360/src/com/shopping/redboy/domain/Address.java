package com.shopping.redboy.domain;

/**
 * 地址信息
* @Description TODO
* @author liang
* @date 2014-4-16 下午6:32:29
 */
public class Address {
	//地址薄ID
	private int id;
	
	private String area;
	
	private String areadetail;
	
	private String city;
	
	private String fixedtel;
	
	private String name;
	
	private String phonenumber;
	
	private String province;
	
	private String zipcode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreadetail() {
		return areadetail;
	}
	public void setAreadetail(String areadetail) {
		this.areadetail = areadetail;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFixedtel() {
		return fixedtel;
	}
	public void setFixedtel(String fixedtel) {
		this.fixedtel = fixedtel;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
