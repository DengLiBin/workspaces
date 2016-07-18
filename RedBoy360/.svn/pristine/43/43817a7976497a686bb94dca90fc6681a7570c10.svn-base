package com.shopping.redboy.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static int currentVersion = 2;
	private static int startVeersion = 1;
	/**
	 * 所有主键使用此字符串
	 */
	public static final String TABLE_ID = "_id";
	/**
	 * 表名以tb开头，中间用下划线分割，后面接上实体类名称，表中字段部分要以表名开头，全部小写
	 */
	public static final String TABLE_HELP = "tb_help";
	public static final String TABLE_HELP_TITLE = "tb_help_title";
	public static final String TABLE_HELP_SUMMARY = "tb_help_summary";

	public DBHelper(Context context) {
		super(context, "redboy.db", null, startVeersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_HELP + " ( " + TABLE_ID
				+ " integer primary key autoincrement, " + TABLE_HELP_TITLE
				+ " varchar(20), " + TABLE_HELP_SUMMARY + " varchar(20)) ");
		onUpgrade(db, startVeersion, currentVersion);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (newVersion) {
		case 2:
			break;
		}
	}

}
