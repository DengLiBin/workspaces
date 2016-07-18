package com.jayqqaa12.abase.util;

import android.app.admin.DevicePolicyManager;
import android.view.View;
import android.widget.Toast;

import com.jayqqaa12.abase.core.Abase;
import com.jayqqaa12.abase.core.AbaseApp;
import com.jayqqaa12.abase.core.AbaseUtil;


/**
 * 
* @author jayqqaa12 
* @date 2013-5-15
 */
public class SysUtil extends AbaseUtil
{
	
	

	
	/**
	 * 恢复 出厂模式！
	 */
	public static void wipeData()
	{
		DevicePolicyManager manager = ManageUtil.getDevicePolicyManager();
		manager.wipeData(0);
	}

	
	/**
	 * 无权限 重启 手机
	 * 记得 必需在 主线程显示
	 * 写个 线程 发消息
	 * 
	 */
	public static void reboot()
	{
		
		Toast toast = new Toast(getContext());
		View view = new View(getContext());
		toast.setView(view);
		toast.show();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	

}
