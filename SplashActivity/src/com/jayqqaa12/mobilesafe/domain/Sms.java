package com.jayqqaa12.mobilesafe.domain;

import java.io.Serializable;
import java.util.Date;

import com.jayqqaa12.abase.util.common.DateUtil;


public class Sms implements Serializable
{
	private static final long serialVersionUID = -1295987688103818288L;
	protected int id;
	protected String name;
	protected String number;
	protected String content;
	protected String desc;
	protected String date;
	protected int type;
	
	public Sms()
	{
	}
	
	public Sms(String name, String number, String content,String desc)
	{
		
		this.content = content;
		this.name = name;
		this.desc = desc;
		this.date = DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS);
		this.number=number;
		
	}
	
	public Sms(int id, String number, String content, int type, String date)
	{
		this.id = id;
		this.number=number;
		this.content = content;
		this.type = type;
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
	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	
	
	

}
