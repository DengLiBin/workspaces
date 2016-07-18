package com.jayqqaa12.mobilesafe.engine.comm.phone;

import android.text.TextUtils;

import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.security.ValidateUtil;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.Contact;
import com.jayqqaa12.mobilesafe.domain.RuleContact;

public class LocationNumberEngine extends AbaseEngine
{
	private AbaseDao ruleDao =  AbaseDao.open(Config.DB_DIR,"rule");
	private AbaseDao addressDao = AbaseDao.open(Config.DB_DIR,"address");
	
	
	

	public String getAddress(String number)
	{
		String address = null;

		if (!number.matches(Config.NUMBER_PATTERN)) { return "请正确输入"; }

		// 查常用 号码
		RuleContact c = ruleDao.findByWhere(RuleContact.class,"number="+number);
		if (c != null)
		{
			address = c.getName();
			return address;
		}

		if (number.matches(Config.PHONE_PATTERN))
		{ // 手机号码
			address = addressDao.findStringBySql("select city from info where mobileprefix="+number.substring(0,7),"city");
			return address;
		}
		else
		{ // 固定电话
			int len = number.length();

			switch (len)
			{
			case 3:
				address = addressDao.findStringBySql("select city from info where area="+number.substring(0,3)+" limit 1","city");
				break;
			case 4:

				address = addressDao.findStringBySql("select city from info where area="+number.substring(0,4)+" limit 1","city");

				break;
			case 7: // 本地号码
				address = "本地号码";
				break;
			case 8: // 本地号码
				address = "本地号码";
				break;
			case 10: // 3位区号 + 7位号码
				// select city from info where area='010' limit 1
				address = addressDao.findStringBySql("select city from info where area="+number.substring(0,3)+" limit 1","city");
				break;
			case 11: // 3位区号 + 8位号码 4位区号+7位号码
				// select city from info where area='010' limit 1
				address = addressDao.findStringBySql("select city from info where area="+number.substring(0,3)+" limit 1","city");
				if (ValidateUtil.isValid(address))
				{
					address = addressDao.findStringBySql("select city from info where area="+number.substring(0,4)+" limit 1","city");

				}
				break;
			case 12: // 4位区号 +8位号码
				// select city from info where area='010' limit 1
				address = addressDao.findStringBySql("select city from info where area="+number.substring(0,4)+" limit 1","city");

				break;

			default:

				break;
			}
		}

		if (TextUtils.isEmpty(address))
		{
			address = "归属地未知";

		}

		return address;
	}

}
