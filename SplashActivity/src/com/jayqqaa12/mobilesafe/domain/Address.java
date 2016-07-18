package com.jayqqaa12.mobilesafe.domain;

import com.jayqqaa12.abase.annotation.db.Table;


@Table(name = "address")
public class Address
{
	private int id;
	private String city;
	private String area;
	private String mobileprefix;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getArea()
	{
		return area;
	}
	public void setArea(String area)
	{
		this.area = area;
	}
	public String getMobileprefix()
	{
		return mobileprefix;
	}
	public void setMobileprefix(String mobileprefix)
	{
		this.mobileprefix = mobileprefix;
	}
	
	
	
}
