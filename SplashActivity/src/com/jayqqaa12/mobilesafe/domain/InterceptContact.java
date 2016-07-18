package com.jayqqaa12.mobilesafe.domain;

import com.jayqqaa12.abase.annotation.db.Parent;
import com.jayqqaa12.abase.annotation.db.Table;
@Parent
@Table(name = "interceptphone")
public class InterceptContact extends Contact
{

	public InterceptContact(String name, String number, String desc)
	{
		super(name,number,desc);
	}

}
