package com.jayqqaa12.abase.util.ui;

import com.jayqqaa12.abase.core.AbaseUtil;
import com.jayqqaa12.abase.util.ManageUtil;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil  extends AbaseUtil
{
	public static void ShortToast(String msg)
	{
		Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
	}
	
	
	public static void LongToast( String msg)
	{
		
		Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
	}
	

	
	
	


}
