package com.jayqqaa12.abase.util.media;

import com.jayqqaa12.abase.core.AbaseUtil;
import com.jayqqaa12.abase.util.ManageUtil;

public class KeyguardUtil extends AbaseUtil
{
	
	/**
	 * 是否 为 锁屏 状态 
	 * 
	 * @return
	 */
	public static boolean isKeyguardRestricted()
	{
		return ManageUtil.getKeyguardManager().inKeyguardRestrictedInputMode();
	}


}
