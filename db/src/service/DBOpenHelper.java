package service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "itcast.db", null, 2);//���ݿ����ƣ��汾��
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//�������ݿ�ÿһ�α�������ʱ����õ�
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE person (personid integer primary key autoincrement, name varchar(20))");
	}

	@Override
	//���ݿ�汾�����仯���ø÷���
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
	}

}
