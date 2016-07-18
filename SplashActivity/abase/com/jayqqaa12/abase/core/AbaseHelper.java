package com.jayqqaa12.abase.core;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 当没有 创建 application 时 应该 用 设置 context
 * 
* @author jayqqaa12 
* @date 2013-5-5
 */
public class AbaseHelper extends SQLiteOpenHelper
{
	private String createSql;
	private DbUpdateListener listener;

	public AbaseHelper(String name, int version, String createSql)
	{
		super(Abase.getContext(), name, null, version);
		this.createSql = createSql;
	}
	
	

	public AbaseHelper(String name, int version, String createSql, DbUpdateListener listener)
	{
		super(Abase.getContext(), name, null, version);
		this.createSql = createSql;
		this.listener = listener;
	}
	
	
	
	

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(createSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		if (listener != null)
		{
			listener.onUpgrade(db, oldVersion, newVersion);
		}
		else
		{ // 清空所有的数据信息
			Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type ='table'", null);
			if (cursor != null)
			{
				while (cursor.moveToNext())
				{
					db.execSQL("DROP TABLE " + cursor.getString(0));
				}
			}
			if (cursor != null)
			{
				cursor.close();
				cursor = null;
			}
		}

	}

	public interface DbUpdateListener
	{
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	}

}
