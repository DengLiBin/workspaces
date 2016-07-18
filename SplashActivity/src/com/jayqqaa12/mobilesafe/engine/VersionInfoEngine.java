package com.jayqqaa12.mobilesafe.engine;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.xmlpull.v1.XmlPullParserException;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.abase.util.ResUtil;
import com.jayqqaa12.abase.util.common.DateUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.VersionInfo;
import com.jayqqaa12.mobilesafe.xml.VersionInfoParse;

public class VersionInfoEngine extends AbaseEngine
{

	private static final String TAG = "VersionInfoService";

	public String getVersionNumber()
	{
		try
		{
			PackageManager manager = getContext().getPackageManager();
			PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
			return info.versionName;
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return getContext().getString(R.string.version_number_unkown);
		}
	}

	public boolean haveNewVersion(String current_version_number)
	{
		boolean result = false;

		try
		{
			VersionInfo versioninfo = getVersionInfo(getContext().getString(R.string.version_info_address));

			if (!versioninfo.getNumber().equals(current_version_number))
			{
				result = true;
			}
			else
			{
				ToastUtil.ShortToast("没有发现新版本");
			}

		}
		catch (Exception e)
		{
			ToastUtil.ShortToast("检查更新失败");
			e.printStackTrace();
		}
		return result;
	}

	public VersionInfo getVersionInfo(String address) throws IOException, XmlPullParserException
	{
		URL url = new URL(address);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(ResUtil.getIntegerArray(R.array.http_timeout)[5]);

		conn.setRequestMethod("GET");

		VersionInfo info = null;
		info = VersionInfoParse.parseVersion(conn.getInputStream());

		return info;
	}

	public boolean isCheckUpdate()
	{
		boolean result = false;
		String startDate = sp.getString(Config.UPDATE_DATE, null);

		if (startDate == null)
		{
			logDate();
		}
		else
		{
			long day = DateUtil.dateDiff(startDate, DateUtil.getDate(new Date()))[0];

			Log.i(TAG, "已经 " + day + "天没有检测更新了");

			if (day > 10)
			{
				result = true;
				logDate();
			}
		}

	
		return result;

	}

	public void logDate()
	{
		ConfigSpUtil.setValue(Config.UPDATE_DATE, DateUtil.getDate(new Date()));
	}

}
