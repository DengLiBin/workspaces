package service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import domain.Person;

public class PersonService {
	private DBOpenHelper dbOpenHelper;
	
	public PersonService(Context context){
		this.dbOpenHelper=new DBOpenHelper(context);
	}
	public void payment(){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.beginTransaction();//��������
		try{
			//Ҫô��ִ��Ҫô����ִ��
			db.execSQL("update person set amount=amount-10 where personid=1");
			db.execSQL("update person set amount=amount+10 where personid=2");
			db.setTransactionSuccessful();//��������ı�־ΪTrue
		}finally{
			db.endTransaction();//��������,�����������commit,rollback,
		//������ύ��ع���������ı�־������,�������ı�־ΪTrue������ͻ��ύ�����ع�,Ĭ�����������ı�־ΪFalse
		}
	}
	/**
	 * ��Ӽ�¼
	 * @param person
	 */
	public void save(Person person){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into person(name,phone)values(?,?)",
				new Object[]{person.getName(),person.getPhone()});
		//db.close();�ر����ݿ�
	}
	/**
	 * ɾ����¼
	 * @param id ��¼ID
	 */
	public void delete(Integer id){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from person where personid=?", new Object[]{id});
	}
	/**
	 * ���¼�¼
	 * @param person
	 */
	public void update(Person person){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update person set name=?,phone=? where personid=?",
				new Object[]{person.getName(), person.getPhone()});
	}
	/**
	 * ��ѯ��¼
	 * @param id ��¼ID
	 * @return
	 */
	public Person find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where personid=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			return new Person(personid, name, phone);
		}
		cursor.close();
		return null;
	}
	/**
	 * ��ҳ��ȡ��¼
	 * @param offset ����ǰ���������¼
	 * @param maxResult ÿҳ��ȡ��������¼
	 * @return
	 */
	public List<Person> getScrollData(int offset,int maxResult){
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person order by personid asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		while(cursor.moveToNext()){//��ǰ��
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));//personid��һ��
			
			String name = cursor.getString(cursor.getColumnIndex("name"));//name��һ��
			String phone = cursor.getString(cursor.getColumnIndex("phone"));//phone��һ��
			persons.add(new Person(personid, name, phone));
		}
		cursor.close();
		return persons;
	}
	/**
	 * ��ҳ��ȡ��¼
	 * @param offset ����ǰ���������¼
	 * @param maxResult ÿҳ��ȡ��������¼
	 * @return
	 */
	public Cursor getCursorScrollData(int offset, int maxResult){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select personid as _id,name,phone,amount from person order by personid asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		return cursor;
	}
	
	/**
	 * ��ȡ��¼����
	 * @return
	 */
	public long getCount(){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
}
