package com.jayqqaa12.abase.util.sys;

import com.jayqqaa12.abase.core.AbaseUtil;

import android.os.Environment;

public class SdCardUtil extends AbaseUtil
{
	
	/**
	 * sd card
	 * @return
	 */
	public static boolean isCanUseSdCard() { 
	    try { 
	        return Environment.getExternalStorageState().equals( 
	                Environment.MEDIA_MOUNTED); 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	    return false; 
	} 
	
}
