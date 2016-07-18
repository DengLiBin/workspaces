package com.jayqqaa12.mobilesafe.domain;


public class VersionInfo
{
	public static final String  XML_TITLE_INFO ="info";
	public static final String XML_TITLE_DESCIPTION="desciber";
	public static final String XML_TITLE_APKURL="apkurl";
	public static final String XML_TITLE_VERSION="version";
	
	private String number;
	private String desciber;
	private String apkurl;
	
	
	
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public String getDesciber()
	{
		return desciber;
	}
	public void setDesciber(String desciber)
	{
		this.desciber = desciber;
	}
	public String getApkurl()
	{
		return apkurl;
	}
	public void setApkurl(String apkurl)
	{
		this.apkurl = apkurl;
	}
	
	
	
	
	

}
