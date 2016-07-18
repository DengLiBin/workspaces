package com.bin.chatrobot.service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "chat.db", null, 1);//���ݿ����ƣ��汾��
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//�������ݿ�ÿһ�α�������ʱ����õ�
		db.execSQL("CREATE TABLE chat (id integer primary key autoincrement,question varchar(50), answer varchar(100))");
	}

	@Override
	//���ݿ�汾�����仯���ø÷���
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
	}

}
