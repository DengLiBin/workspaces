package com.jayqqaa12.mobilesafe.engine.space;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.net.Uri;

import com.jayqqaa12.abase.core.AbaseDaoEngine;
import com.jayqqaa12.abase.core.Provider;
import com.jayqqaa12.abase.util.sys.ApkInfo;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.AppLock;
import com.jayqqaa12.mobilesafe.provider.AppLockProvider;

@SuppressWarnings("rawtypes")
public class AppLockEngine extends AbaseDaoEngine
{

	public List<Map<String, Object>> getUnlockData()
	{
		return getData(false);
	}

	public List<Map<String, Object>> getLockData()
	{
		return getData(true);
	}

	private List<Map<String, Object>> getData(boolean lock)
	{
		List<Map<String, Object>> lockData = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> unlockData = new ArrayList<Map<String, Object>>();
	
		List<ApkInfo> apks = ApkInfoUtil.getMyApps();
		List<String> locks = getAllLocks();
		
		for (ApkInfo apk : apks)
		{
			if (apk.appName.equals(ApkInfoUtil.getMyApkName())) continue;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", apk.appName);
			map.put("icon", apk.iconDrawable);
			map.put("packname", apk.packageName);
			
			if (locks.contains(apk.packageName))
			{
				map.put("icon2", R.drawable.lock_clolsed);
				lockData.add(map);
			}
			else
			{
				map.put("icon2", R.drawable.lock_open);
				unlockData.add(map);
			}
		}
		return lock ? lockData : unlockData;
	}


	public List<String> getAllLocks()
	{
		List<String> locks = new ArrayList<String>();
				
		 for(AppLock lock : dao.findAll(AppLock.class))
		 {
			 locks .add(lock.getPackname());
		 }
		 return locks;
		 
	}

	public void notifyProviderInsert(String packname)
	{
		ContentValues values = new ContentValues();
		values.put("packname", packname);
		getResolver().insert(Uri.parse(Provider.CONTENT + AppLockProvider.AUTHORITIES + Provider.SPACE_INSERT), values);
	}

	public void notifyProviderDelete(String packname)
	{
		getResolver().delete(Uri.parse(Provider.CONTENT + AppLockProvider.AUTHORITIES + Provider.SPACE_DELETE), null, new String[] { packname });
	}

	public boolean isOpenService()
	{
		return sp.getBoolean(Config.SPACE_APPLOCK_OPEN,true);
	}

}
