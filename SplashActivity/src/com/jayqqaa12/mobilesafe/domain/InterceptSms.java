package com.jayqqaa12.mobilesafe.domain;

import com.jayqqaa12.abase.annotation.db.Parent;
import com.jayqqaa12.abase.annotation.db.Table;

@Parent
@Table(name = "inteceptsms")
public class InterceptSms extends Sms
{

	public InterceptSms(String name, String number, String content, String desc)
	{
		super(name,number,content,desc);
	}

}
