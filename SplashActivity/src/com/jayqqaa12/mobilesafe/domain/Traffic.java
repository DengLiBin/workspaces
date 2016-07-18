package com.jayqqaa12.mobilesafe.domain;

import java.util.Date;

import com.jayqqaa12.abase.annotation.db.Table;
import com.jayqqaa12.abase.annotation.db.Transient;


@Table(name = "traffic")
public class Traffic
{
	private int id;
	private int uid;
	private long wifiDay;
	private long wifiMonth;
	private long mobileDay;
	private long mobileMonth;
	private Date date;
	
	@Transient
	private long cache;
	
	
	

	public long getCache()
	{
		return cache;
	}

	public void setCache(long cache)
	{
		this.cache = cache;
	}

	public long getWifiDay()
	{
		return wifiDay;
	}

	public void setWifiDay(long wifiDay)
	{
		this.wifiDay = wifiDay;
	}

	public long getWifiMonth()
	{
		return wifiMonth;
	}

	public void setWifiMonth(long wifiMonth)
	{
		this.wifiMonth = wifiMonth;
	}

	public long getMobileDay()
	{
		return mobileDay;
	}

	public void setMobileDay(long mobileDay)
	{
		this.mobileDay = mobileDay;
	}

	public long getMobileMonth()
	{
		return mobileMonth;
	}

	public void setMobileMonth(long mobileMonth)
	{
		this.mobileMonth = mobileMonth;
	}


	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}


}
