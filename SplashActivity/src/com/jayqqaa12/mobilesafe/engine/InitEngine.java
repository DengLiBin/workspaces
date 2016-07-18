package com.jayqqaa12.mobilesafe.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import android.os.Environment;

import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.abase.util.common.DateUtil;
import com.jayqqaa12.abase.util.io.FileUtil;
import com.jayqqaa12.abase.util.io.ZipUtils;
import com.jayqqaa12.abase.util.sys.RootUtil;
import com.jayqqaa12.abase.util.sys.SdCardUtil;
import com.jayqqaa12.abase.util.ui.ShortcutUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;

public class InitEngine extends AbaseEngine
{
	public boolean isInitDB()
	{
		try
		{
			return FileUtil.isFileDecompression(FileUtil.getAllAssertFileName(getContext()), Config.DB_DIR, ".12");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return false;
	}


	/**
	 *  assets 里面 压缩的 文件 名 必需 和 压缩文件名字 一样 后缀可以 改不一样！！
	 */
	public void upZipDB()
	{
		String[] files = FileUtil.getAllAssertFileName(getContext());
		List<String> dbFiles = FileUtil.getFiles(files, Config.LOCATION_DB_RULE);
		try
		{

			for (String file : dbFiles)
			{
				FileUtil.copyAssetsFile(getContext(), file, Config.DB_DIR);
				ZipUtils.upZipFile(Config.DB_DIR + File.separator + file, Config.DB_DIR);
				FileUtil.deleteFile(Config.DB_DIR + File.separator + file);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public boolean isFirstRunning()
	{
		return sp.getBoolean(Config.FIRST_RUNNING, true);
	}

	
	/**
	 * 设置 后下次 运行 重新 初始化 数据
	 */
	public void reInit()
	{
		ConfigSpUtil.setValue(Config.FIRST_RUNNING, true);
	}
	

	/**
	 * 初始化 可以 设置 初始化的  设置 值
	 * @param value
	 */
	public void init()
	{
		ToastUtil.ShortToast("哦咯 叔给你创建了个快捷方式");
		ShortcutUtil.createShortCut(getContext(),R.drawable.icon,R.string.app_name);
		
		ConfigSpUtil.setValue(Config.FIRST_RUNNING, false);
		ConfigSpUtil.setValue(Config.TRAFFIC_START_DATE,DateUtil.getDate(new Date()));
		RootUtil.getRootAhth();
	}

	public void initDir()
	{
		if (SdCardUtil.isCanUseSdCard())
		{
			File dir = new File(Environment.getExternalStorageDirectory() + File.separator + Config.APP_DIR_PATH);
			if(!dir.exists()) dir.mkdirs();
		}

	}
	
	
	
	
	

}
