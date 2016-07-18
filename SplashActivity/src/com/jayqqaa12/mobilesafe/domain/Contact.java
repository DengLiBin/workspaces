package com.jayqqaa12.mobilesafe.domain;

import java.io.Serializable;
import java.util.Date;


public class Contact  implements Serializable
{
	private static final long serialVersionUID = 8157481414021678571L;

	protected int id;
	protected String name;
	protected String number;
	protected String desc;
	protected Date date;
	protected int type;

	public Contact()
	{
	}
	
	public Contact(String name, String number, String desc)
	{
			this.number=number;
			this.name=name;
			this.date= new Date();
			this.desc=desc;
	}
	
	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	

}
