package com.bin.chatrobot.service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "chat.db", null, 1);//数据库名称，版本号
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//是在数据库每一次被创建的时候调用的
		db.execSQL("CREATE TABLE chat (id integer primary key autoincrement,question varchar(50), answer varchar(100))");
	}

	@Override
	//数据库版本发生变化调用该方法
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
	}

}
