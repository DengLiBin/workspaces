package com.jayqqaa12.mobilesafe.engine.space.lost;

import android.util.Log;

import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.abase.util.comm.PhoneUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.security.DisgestUtil;
import com.jayqqaa12.abase.util.security.ValidateUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.config.Config;

public class LostEngine extends AbaseEngine
{

	public boolean isSimBind()
	{
		return sp.getString(Config.PROTECTED_SIM_NUMBER, null) != null;
	}

	public void setSimBind(boolean isopen)
	{
		if (isopen)
		{
			String simserial = PhoneUtil.getSimSerialNumber();
			// if sim is null
			if (simserial == null) ToastUtil.ShortToast("SIM 卡不可用 ");
			else ConfigSpUtil.setValue(Config.PROTECTED_SIM_NUMBER, simserial);

		}
		else
		{
			
			ConfigSpUtil.setValue(Config.PROTECTED_SIM_NUMBER, null);
		}
		
	}


	public String getSimNumber()
	{
		return ConfigSpUtil.getString(Config.PROTECTED_SIM_NUMBER, null);
	}

	public void setProtected(boolean isProtected)
	{
		if (isProtected)
		{
			if (isSetProtectedNumber() && isSimBind()) ConfigSpUtil.setValue(Config.PROTECTED, true);
			else ToastUtil.ShortToast("SIM卡未绑定 或 安全号码未设置");
		}
		else
		{
			ConfigSpUtil.setValue(Config.PROTECTED, false);
		}
	}

	public boolean isOpenService()
	{
		return sp.getBoolean(Config.PROTECTED, false);
	}

	public String getProtectedNumber()
	{
		return sp.getString(Config.PROTECTED_NUMBER, null);
	}

	public void setProtectedNumber(String number)
	{
		ConfigSpUtil.setValue(Config.PROTECTED_NUMBER, number);
	}

	public boolean isSetProtectedNumber()
	{
		return getProtectedNumber() != null;
	}

	public boolean isSetup()
	{
		return sp.getBoolean(Config.PROTECTED_GUIDE_SETUP, false);
	}

	public boolean havePassword()
	{
		boolean result = false;
		String password = sp.getString(Config.PROTECTED_PWD, null);
		if (password != null) result = true;
		else result = false;

		return result;
	}

	public void setupOver()
	{
		ConfigSpUtil.setValue(Config.PROTECTED_GUIDE_SETUP, true);
	}

	public String getPassword()
	{
		return sp.getString(Config.PROTECTED_PWD, null);
	}

	public void setPassword(String pwd)
	{
		ConfigSpUtil.setValue(Config.PROTECTED_PWD, DisgestUtil.encodeMD5(pwd));
	}

	public boolean isSimChange()
	{
		boolean result = false;

		String currentsim = PhoneUtil.getSimSerialNumber();
		String realsim = getSimNumber();

		if (ValidateUtil.isValid(currentsim) && ValidateUtil.isValid(realsim))
		{
			result = !currentsim.equals(realsim);
		}

		return result;
	}

	public boolean parsePassword(String pwd)
	{
		if (ValidateUtil.isValid(pwd) && DisgestUtil.encodeMD5(pwd).equals(getPassword()))
		{
			Log.i(TAG.SERVICE, "pwd pass");
			return true;
		}

		return false;
	}

	public boolean vaildataPwd(String pwd)
	{
		return DisgestUtil.encodeMD5(pwd).equals(getPassword());
	}

	public boolean isOpenUninstallProtectedService()
	{
		return sp.getBoolean(Config.PROTECTED_UNINSTALL, true);
	}

}
