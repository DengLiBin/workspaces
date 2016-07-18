package com.jayqqaa12.abase.core;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class Engine
{
	private static Map<String, AbaseEngine> serviceMap = new HashMap<String, AbaseEngine>();


	public synchronized static AbaseEngine getInstance(Class<? extends AbaseEngine> clazz)
	{
		AbaseEngine service = serviceMap.get(clazz.getSimpleName());
		if (service == null)
		{
			try
			{
				service = clazz.newInstance();
				serviceMap.put(clazz.getSimpleName(), service);
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return service;
	}

}
