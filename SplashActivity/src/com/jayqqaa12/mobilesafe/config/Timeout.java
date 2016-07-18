package com.jayqqaa12.mobilesafe.config;

public enum Timeout
{
	SHORT(5,"5秒"),MIDDLE(15,"15秒"),LONG(30,"30秒"),FOREVEN(-1,"永久");
	
	private  int value;
	private String name;

	private Timeout(int value,String name)
	{
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(int value)
	{
		
		Timeout [] times = values();
		
		for(Timeout t:times)
		{
			if(t.getValue() == value)
			{
				return t.name;
			}
		}
		return null;
	}
	
	public static Integer getOrdinal(int value)
	{
		
		for(Timeout t:values())
		{
			if(t.value ==value)
			{
				return t.ordinal();
			}
		}
		return null;
	}
	
	
	
	
	public static String getNameByOrdinal(int ordinal)
	{
		Timeout [] times = values();
		
		for(Timeout t:times)
		{
			if(t.ordinal()== ordinal)
			{
				return t.name;
			}
		}
		return "";
	}


	
	
	
	public  int getValue()
	{

		return value;
	}


	public static Timeout getTimeoutByOrdinal(int ordinal)
	{
		for (Timeout t: values())
		{
			
			if(t.ordinal()==ordinal)
			{
				return t;
			}
		}
		
		return null;
		
	}

	public static String[] getNames()
	{
		String []  names= new String [Timeout.values().length];
		int i=0;
		for(Timeout t: values())
		{
			names[i++]=t.getNameByOrdinal(t.ordinal());
		}
		
		return names;
		
	}

}
