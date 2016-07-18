package com.jayqqaa12.mobilesafe.engine.software;

import java.util.ArrayList;
import java.util.List;

import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.sys.ApkInfo;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;

public class AppEngine extends AbaseEngine
{
	private List<ApkInfo> preloadApps = new ArrayList<ApkInfo>();
	private List<ApkInfo> sysApps = new ArrayList<ApkInfo>();
	private List<ApkInfo> myApps = new ArrayList<ApkInfo>();

	public List<ApkInfo> getPreloadApps()
	{
		return preloadApps;
	}

	public List<ApkInfo> getSysApps()
	{
		return sysApps;
	}

	public List<ApkInfo> getMyApps()
	{
		return myApps;
	}

	public void startGetAppsInfo()
	{
		List<ApkInfo> apps = ApkInfoUtil.getAllApps();
		
		this.myApps = (ApkInfoUtil.getMyApps(apps));
		this.sysApps = (ApkInfoUtil.getSysApps(apps));
		this.preloadApps = (ApkInfoUtil.getPreloadApps(apps));

	}

	public void destoryAppsInfo()
	{
		this.myApps.clear();
		this.sysApps.clear();
		this.preloadApps.clear();

	}

}
