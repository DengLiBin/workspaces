package com.jayqqaa12.mobilesafe.domain;

import com.jayqqaa12.abase.annotation.db.Table;



@Table(name = "applock")
public class AppLock
{
	private int id ;
	private String packname;
	
	public AppLock()
	{
	}
	
	public AppLock(String packname)
	{
		this.packname =packname;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getPackname()
	{
		return packname;
	}
	public void setPackname(String packname)
	{
		this.packname = packname;
	}
	
	
	
	
	

}
