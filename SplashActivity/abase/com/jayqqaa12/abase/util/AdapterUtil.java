package com.jayqqaa12.abase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterUtil
{

	public static void changeAdpaterData(List<? extends Map<String, ?>> data, String[] from, Object[]... objs)
	{

		int i = -1;
		for (Map map : data)
		{
			i++;
			
			int j = -1;
			while (++j < from.length)
			{
				map.put(from[j], objs[j][i]);
			}

		}

	}

	public static List<? extends Map<String, ?>> getAdpaterData(String[] from, Object[]... objs)
	{

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		int i = -1;

		while (++i < objs[0].length)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			int j = -1;
			while (++j < from.length)
			{
				map.put(from[j], objs[j][i]);
			}

			data.add(map);
		}

		return data;
	}

}
