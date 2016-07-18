package com.jayqqaa12.mobilesafe.config;

public enum InterceptModel
{
	INTELLIGENT("智能模式"),BLACK("黑名单模式"),WHITE("白名单模式"),REJECT("拒接模式"),UNDISTURB("免打扰模式"),USER_DEFINED("自定义模式");
	
	private  String name ;
	
	public static final int INTELLIGENT_MODEL = 0;
	public static final int BLACK_MODEL =1;
	public static final int WHITE_MODEL =2;
	public static final int REJECT_MODEL =3;
	public static final int UNDISTURB_MODEL =4;
	public static final int USER_DEFINED_MODEL =5;
	
	
	
	InterceptModel(String name)
	{
		this.name = name;
		
	}
	
	public static String getName(InterceptModel model)
	{
		return model.name;
	}
	
	
	public static InterceptModel getModel(int ordinal)
	{
		
		for(InterceptModel model : values())
		{
			if(model.ordinal() ==ordinal)
			{
				
				return model;
			}
			
		}
		
		return null;
	}


	
	
	
	

}
