package com.jayqqaa12.abase.core;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public abstract class AbaseContentProvider extends ContentProvider
{
	protected AbaseDao dao;

	@Override
	public boolean onCreate()
	{
		Abase.setContext(getContext());
		Abase.init(this);
		dao = AbaseDao.create();
		return false;
	}

}
