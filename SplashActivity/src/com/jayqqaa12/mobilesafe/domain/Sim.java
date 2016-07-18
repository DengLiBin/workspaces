package com.jayqqaa12.mobilesafe.domain;

import java.io.Serializable;



public class Sim implements Serializable
{
	private static final long serialVersionUID = -8163128852079215563L;
	private int id;
	private String name;
	private String sim;
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
	public String getSim()
	{
		return sim;
	}
	public void setSim(String sim)
	{
		this.sim = sim;
	}
	
	
	
	
	

}
