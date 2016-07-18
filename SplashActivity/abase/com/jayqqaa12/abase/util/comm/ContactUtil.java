package com.jayqqaa12.abase.util.comm;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.jayqqaa12.abase.core.AbaseUtil;

public class ContactUtil extends AbaseUtil
{


	public static boolean isFindPhoneFromContact(String number)
	{

		for (String n : getAllContactPhone())
		{
			if (n == number) { return true; }
		}
		return false;

	}

	public static List<String> getAllContactPhone()
	{

		List<String> numbers = new ArrayList<String>();
		ContentResolver resolver = getContext().getContentResolver();

		Uri id_uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri data_uri = Uri.parse("content://com.android.contacts/data");

		Cursor cursor = resolver.query(id_uri, null, null, null, null);
		while (cursor.moveToNext())
		{
			String id = cursor.getString(cursor.getColumnIndex("_id"));
			Cursor datacursor = resolver.query(data_uri, null, "raw_contact_id=?", new String[] { id }, null);
			while (datacursor.moveToNext())
			{
				// mimetype
				String type = datacursor.getString(datacursor.getColumnIndex("mimetype"));
				if ("vnd.android.cursor.item/phone_v2".equals(type))
				{
					String number = datacursor.getString(datacursor.getColumnIndex("data1"));
					numbers.add(number);
				}
			}
			datacursor.close();
		}
		cursor.close();
		return numbers;
	}
	
	
	public static Cursor getAllSms()
	{
		
		ContentResolver resolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		Cursor cursor = resolver.query(uri, null,
				null, null, "date desc");
		return cursor;
		
	}
	
	
	

	/**
	 * 根据电话号码取得联系人姓名
	 */
	public static String getContactName(String number)
	{
		String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER };

		// 将自己添加到 msPeers 中
		Cursor cursor = getContext().getContentResolver()//
				.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, // Which
						ContactsContract.CommonDataKinds.Phone.NUMBER + "=?", // WHERE
						new String[] { number }, //
						null); // Sort order.

		String name = null;
		while (cursor.moveToNext())
		{
			// 取得联系人名字
			name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
		}

		return name;
	}


}
