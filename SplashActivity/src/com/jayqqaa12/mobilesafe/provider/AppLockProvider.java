package com.jayqqaa12.mobilesafe.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.jayqqaa12.abase.core.AbaseContentProvider;
import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.Provider;
import com.jayqqaa12.mobilesafe.domain.AppLock;

public class AppLockProvider extends AbaseContentProvider
{
	
	public static final String AUTHORITIES = "com.jayqqaa12.mobilesafe.applockprovider";
	private static final int CODE_INSERT = 10;
	private static final int CODE_DELETE = 11;
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final Uri AUTHORITIES_URI = Uri.parse(Provider.CONTENT + AUTHORITIES);


	
	static
	{
		matcher.addURI(AUTHORITIES, Provider.INSERT, CODE_INSERT);
		matcher.addURI(AUTHORITIES, Provider.DELETE, CODE_DELETE);
	}
	

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
	{
		return null;
	}

	@Override
	public String getType(Uri uri)
	{
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		int result = matcher.match(uri);
		if (result == CODE_INSERT)
		{
			String packname = (String) values.get("packname");
			
			dao.save(new AppLock(packname));
			
			getContext().getContentResolver().notifyChange(uri, null);
		}
		else
		{
			throw new IllegalArgumentException("uri地址不正确");
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)
	{
		int result = matcher.match(uri);
		if (result == CODE_DELETE)
		{
			String packname = selectionArgs[0];
			
			
			dao.deleteByWhere(AppLock.class,"packname= '"+packname+"'");
			
			getContext().getContentResolver().notifyChange(AUTHORITIES_URI, null);
		}
		else
		{
			throw new IllegalArgumentException("uri地址不正确");
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		return 0;
	}

	

}
