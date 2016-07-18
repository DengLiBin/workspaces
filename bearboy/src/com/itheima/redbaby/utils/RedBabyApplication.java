package com.itheima.redbaby.utils;

import com.itheima.redbaby.GloableParameters;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap.CompressFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * 定义了一些初始化的全局的信息
 * 
 * @author zhangyun
 * 
 */
public class RedBabyApplication extends Application {
	/**
	 * 屏幕的宽度
	 */
	public int screenWidth;
	/**
	 * 屏幕的高度
	 */
	public int screenHight;

	@Override
	public void onCreate() {
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
		screenHight = outMetrics.heightPixels;

		/**
		 * 初始化ImageLoaderConfiguration
		 */
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(480, 800) // default
																																			// =
																																			// device
																																			// screen
																																			// dimensions
				.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null).denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024).discCacheSize(50 * 1024 * 1024).discCacheFileCount(100).writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
		GloableParameters.VERSION = getVersion();
		super.onCreate();
	}

	public String getVersion() {
		try {
			PackageManager pm = getPackageManager();
			String versionName = pm.getPackageInfo(getPackageName(), 0).versionName;

			return versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();

		}
		return null;
	}

}
