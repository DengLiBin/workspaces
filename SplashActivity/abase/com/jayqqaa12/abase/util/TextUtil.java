package com.jayqqaa12.abase.util;

import java.text.DecimalFormat;

import com.jayqqaa12.abase.core.AbaseUtil;

public class TextUtil extends AbaseUtil
{

	/**
	 * 返回byte的数据大小对应的文本
	 * 
	 * 不加单位
	 * 
	 * @param size
	 * @return
	 */
	public static String getDataSize(long size)
	{
		if (size <= 0) { return "0.00"; }
		DecimalFormat formater = new DecimalFormat("####.00");
		if (size < 1024)
		{
			return size + "";
		}
		else if (size < 1024 * 1024)
		{
			float kbsize = size / 1024f;
			return formater.format(kbsize) + "";
		}
		else if (size < 1024 * 1024 * 1024)
		{
			float mbsize = size / 1024f / 1024f;
			return formater.format(mbsize) + "";
		}
		else if (size < 1024 * 1024 * 1024 * 1024)
		{
			float gbsize = size / 1024f / 1024f / 1024f;
			return formater.format(gbsize) + "";
		}
		else
		{
			return "max";
		}

	}
	
	
	
	
	

	/**
	 * 
	 * 
	 * @param kb 
	 * @return
	 */
	public static String getKbToMb(long kb)
	{
		if (kb <= 0) { return "0.0"; }
		DecimalFormat formater = new DecimalFormat("####.0");
		float mb = kb  / 1024f;
		String result = formater.format(mb) + "";
		if(result.startsWith(".")) result ="0"+result;
		
		return result.equals("0.0")?"0.1":result; 

	}

	
	
	

	/**
	 * 
	 * 
	 * @param bits
	 * @return
	 */
	public static String getBitToMb(long bits)
	{
		if (bits <= 0) { return "0.0"; }
		DecimalFormat formater = new DecimalFormat("####.0");
		float mb = bits /8f/ 1024f / 1024f;
		String result = formater.format(mb) + "";
		
		if(result.startsWith(".")) result ="0"+result;
		
		return result.equals("0.0")?"0.1":result; 

	}

	/**
	 * 
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getByteToMb(long bytes)
	{
		if (bytes <= 0) { return "0.0"; }
		DecimalFormat formater = new DecimalFormat("####.0");
		float mb = bytes / 1024f / 1024f;
		String result = formater.format(mb) + "";
		
		if(result.startsWith(".")) result ="0"+result;
		
		return result.equals("0.0")?"0.1":result; 

	}

}
