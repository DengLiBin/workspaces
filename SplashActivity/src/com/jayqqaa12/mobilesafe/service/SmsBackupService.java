package com.jayqqaa12.mobilesafe.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.core.AbaseIntentService;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.ManageUtil;
import com.jayqqaa12.abase.util.sys.SdCardUtil;
import com.jayqqaa12.abase.util.ui.ScreenUtil;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.Sms;
import com.jayqqaa12.mobilesafe.engine.comm.sms.SmsEngine;

public class SmsBackupService extends AbaseIntentService
{
	private static final String TAG = "SmsBackupService";
	@FindEngine
	private SmsEngine engine;

	private Binder binder = new SmsServiceBind();



	@Override
	public void onCreate()
	{
		super.onCreate();

		Log.i(TAG, "serivce is onCreate");
	}
	
	

	@Override
	public IBinder onBind(Intent intent)
	{
		return binder;
	}

	public class SmsServiceBind extends Binder
	{
		public SmsBackupService getService()
		{
			return SmsBackupService.this;
		}

	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		backupSms();

	}

	private void backupSms()
	{
		Log.i(TAG, "start bakcup");

		try
		{
			if (!SdCardUtil.isCanUseSdCard()) throw new NotFoundException("SD卡找不到 或者不可用");

			List<Sms> list = engine.getAllSms();
			
			if(list.size()==0)
			{
				ToastUtil.ShortToast("没有短信 需要 备份哦");
				
				return ;
			}
			
			
			File file = new File(Config.SMS_BACKUP_FILE_PATH);

			XmlSerializer serializer = Xml.newSerializer();
			FileOutputStream os = new FileOutputStream(file);

			serializer.setOutput(os, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag(null, "smss");
			serializer.startTag(null, "count");
			serializer.text(list.size() + "");
			serializer.endTag(null, "count");

			int i = 0;

			for (Sms sms : list)
			{
				serializer.startTag(null, "sms");
				serializer.startTag(null, "id");
				serializer.text(sms.getId() + "");
				serializer.endTag(null, "id");

				serializer.startTag(null, "number");
				serializer.text(sms.getNumber());
				serializer.endTag(null, "number");

				serializer.startTag(null, "date");
				serializer.text(sms.getDate());
				serializer.endTag(null, "date");

				serializer.startTag(null, "type");
				serializer.text(sms.getType() + "");
				serializer.endTag(null, "type");

				String content = sms.getContent().replaceAll("[^\\w]", "");
				Log.i(TAG,content);
				
				serializer.startTag(null, "body");
				serializer.text(content);
				serializer.endTag(null, "body");
				serializer.endTag(null, "sms");


			}

			serializer.endTag(null, "smss");
			serializer.endDocument();
			// 把文件缓冲区的数据写出去
			os.flush();
			os.close();
			ToastUtil.ShortToast("备份完成,备份了"+list.size()+"条短信");
			
			engine.setBackupDate(new Date());

		
		}
		catch (NotFoundException e)
		{
			ToastUtil.ShortToast("SD卡找不到 或者不可用");
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			ToastUtil.ShortToast("备份失败");
			e.printStackTrace();
		}
		catch (IllegalStateException e)
		{
			ToastUtil.ShortToast("备份失败");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			ToastUtil.ShortToast("备份失败");
			e.printStackTrace();
		}
		finally
		{
			Looper.loop();
		}

	}

	@Override
	public void onDestroy()
	{
		IntentUtil.startService(this,MonitorService.class);
		super.onDestroy();
	}
}
