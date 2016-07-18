package com.jayqqaa12.abase.util.sys;

import java.io.Serializable;
import java.util.Date;

import android.graphics.drawable.Drawable;

public class ApkInfo<T>  implements Serializable{
	
	
	public String packageName;
	public int iconId;
	public int uid;
	public Drawable iconDrawable;
	public String appName;
	public int versionCode;
	public long appSize;
	public String versionName;
	public boolean isSysApp;
	public boolean isPreloadApp;
	public Date date;
	public String size;
	public boolean check;
	
	/**
	 * 这个对象 可以用来 存放 一些 其他 数据  
	 * 
	 * 用在 adapter 的时候 很好用
	 * 
	 */
	public T obj;
	

	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ApkInfo other = (ApkInfo) obj;
		if (appName == null)
		{
			if (other.appName != null) return false;
		}
		else if (!appName.equals(other.appName)) return false;
		return true;
	}




	@Override
	public String toString() {
		return "ApkInfo [packageName=" + packageName + ", iconId=" + iconId
				+ ", iconDrawable=" + iconDrawable + ", programName="
				+ appName + ", versionCode=" + versionCode
				+ ", versionName=" + versionName + "]";
	}
}
