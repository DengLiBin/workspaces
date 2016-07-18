package com.jayqqaa12.mobilesafe.config;

import com.jayqqaa12.mobilesafe.R;

public enum Style
{
	DEFAULT("默认",R.drawable.call_locate_white),GREEN("苹果绿",R.drawable.call_locate_green),
	ORANGE("活力橙",R.drawable.call_locate_orange),
	GRAY("大叔灰",R.drawable.call_locate_gray),BLUE("卫士蓝",R.drawable.call_locate_blue);
	
	private final String name;
	private int drawable;
	
	private Style(String name,int drawable)
	{
		this.name = name;
		this.drawable = drawable;
	}
	
	
	public static int getDrawable(int ordinal)
	{
		for(Style s:values())
		{
			if(s.ordinal() ==ordinal)
			{
				return s.drawable;
			}
		}
		return 0;
	}
	
	public static String [] getNames()
	{
		String [] names = new String[Style.values().length];
		
		int i =0;
		for(Style s: Style.values())
		{
			names[i++]= s.getName(); 
		}
		return names;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public static String getName(int ordinal)
	{
	 return	getStyle(ordinal).getName();
		
	}
	
	
	public static Style getStyle(int ordinal)
	{
		for(Style s:values())
		{
			if(s.ordinal()==ordinal)
				return s;
		}
		
		return null;
	}

}
