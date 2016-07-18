package com.jayqqaa12.mobilesafe.engine.space.lost;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.jayqqaa12.abase.core.AbaseApp;
import com.jayqqaa12.abase.core.AbaseEngine;
import com.jayqqaa12.abase.util.ui.NotificationUtil;
import com.jayqqaa12.mobilesafe.config.MimeType;
import com.jayqqaa12.mobilesafe.domain.Contact;

public class ContactEngine extends AbaseEngine
{

	public int getContactCount()
	{
		return getContactInfos().size();
	}

	public List<Contact> getContactInfos()
	{
		ContentResolver resolver = getContext().getContentResolver();

		// 1.find contact id and name
		// 3.根据联系人的id 数据的type 获取到对应的数据(电话,email);
		List<Contact> infos = new ArrayList<Contact>();
		Contact info;
		
		Uri id_uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri data_uri = Uri.parse("content://com.android.contacts/data");
		Cursor cursor = resolver.query(id_uri, null, null, null, null);
		
		while (cursor.moveToNext())
		{
			info = new Contact();
			String id = cursor.getString(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("display_name"));
			info.setName(name);
			Cursor datacursor = resolver.query(data_uri, null, "raw_contact_id=?", new String[] { id }, null);
			while (datacursor.moveToNext())
			{
				// mimetype
				String type = datacursor.getString(datacursor.getColumnIndex("mimetype"));
				if (MimeType.PHONE.equals(type))
				{
					String number = datacursor.getString(datacursor.getColumnIndex("data1"));
					info.setNumber(number);
				}
			}
			datacursor.close();
			infos.add(info);
			info = null;
		}
		cursor.close();
		return infos;
	}

}
