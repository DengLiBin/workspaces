package com.jayqqaa12.mobilesafe.config;

public enum InterceptSaveTime
{
	FOREVER("永久"),ONE_WEEK("一星期") ,ONE_MONTH("一个月");
	
	
	private String name;

	private InterceptSaveTime(String name)
	{
		this.name= name;
	}

	public static InterceptSaveTime getTime(int ordinal)
	{
		for(InterceptSaveTime time:values())
		{
			if(time.ordinal()==ordinal)
			{
				return time;
			}
			
		}
			
		return null;
		
	}
	
	
	
	public static String getName(InterceptSaveTime time)
	{
		
		return time.name;
	}

}
