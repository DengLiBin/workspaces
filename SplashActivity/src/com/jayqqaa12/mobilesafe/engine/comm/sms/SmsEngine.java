package com.jayqqaa12.mobilesafe.engine.comm.sms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Xml;

import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.abase.util.comm.ContactUtil;
import com.jayqqaa12.abase.util.common.DateUtil;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.Sms;

public class SmsEngine extends AbaseEngine
{

	public String getBackupDate()
	{
		return sp.getString(Config.SMS_BACKUP_DATE, null);

	}

	public void setBackupDate(Date date)
	{

		ConfigSpUtil.setValue(Config.SMS_BACKUP_DATE, DateUtil.format(date,DateUtil.YYYY_MM_DD_HH_MM_SS));

	}

	public List<Sms> getAllSms()
	{
		List<Sms> list = new ArrayList<Sms>();
		Cursor cursor = ContactUtil.getAllSms();

		Sms sms;
		while (cursor.moveToNext())
		{
			int id = cursor.getInt(0);
			String number = cursor.getString(cursor.getColumnIndex("address"));
			String body = cursor.getString(cursor.getColumnIndex("body"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			int type = cursor.getInt(cursor.getColumnIndex("type"));
			sms = new Sms(id, number, body, type, date);
			list.add(sms);
		}
		cursor.close();
		return list;
	}
	
	
	

	public void restoreSms(ProgressDialog pd) throws Exception
	{

		File file = new File(Config.SMS_BACKUP_FILE_PATH);
		
		if(!file.exists()) throw new FileNotFoundException();

		ContentValues values = null;
		FileInputStream fis = new FileInputStream(file);
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(fis, "utf-8");
		int type = parser.getEventType();
		int currentcount = 0;
		while (type != XmlPullParser.END_DOCUMENT)
		{

			switch (type)
			{

			case XmlPullParser.START_TAG:
				if ("count".equals(parser.getName()))
				{
					String count = parser.nextText();
					pd.setMax(Integer.parseInt(count));
				}

				if ("sms".equals(parser.getName()))
				{
					values = new ContentValues();
				}
				else if ("number".equals(parser.getName()))
				{
					values.put("address", parser.nextText());
				}
				else if ("date".equals(parser.getName()))
				{
					values.put("date", parser.nextText());
				}
				else if ("type".equals(parser.getName()))
				{
					values.put("type", parser.nextText());
				}
				else if ("body".equals(parser.getName()))
				{
					values.put("body", parser.nextText());
				}

				break;

			case XmlPullParser.END_TAG:
				if ("sms".equals(parser.getName()))
				{

					ContentResolver resolver = getContext().getContentResolver();
					resolver.insert(Uri.parse("content://sms/"), values);

					values = null;
					currentcount++;
					pd.setProgress(currentcount);
				}
				break;

			}
			type = parser.next();
		}

	}

	public String getBackupCount()
	{

		String count = "";
		try
		{
			File file = new File(Config.SMS_BACKUP_FILE_PATH);
			if(!file .exists()) return "0";
			
			FileInputStream fis;
			fis = new FileInputStream(file);

			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(fis, "utf-8");
			int type = parser.getEventType();

			while (type != XmlPullParser.END_DOCUMENT)
			{

				switch (type)
				{

				case XmlPullParser.START_TAG:
					if ("count".equals(parser.getName()))
					{
						count = parser.nextText();
					}
					break;
				}
				type = parser.next();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return count;

	}

	public boolean isFindBackupFile()
	{
		return  new File(Config.SMS_BACKUP_FILE_PATH).exists();
	}

}
