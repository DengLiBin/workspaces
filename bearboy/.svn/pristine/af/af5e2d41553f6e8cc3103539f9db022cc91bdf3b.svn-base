package com.itheima.redbaby.dao;



import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.bean.HistoryProduct;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HistorySearchDao {

	private HistorySearchDBHelper helper;

	public HistorySearchDao(Context context){
		helper = new HistorySearchDBHelper(context);
	}
	
	public void add(String id,String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("name", name);
		db.insert("history", null, values);
		db.close();
	}
	public void delete(String id){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id", id);
		db.delete("history", "id=?",new String[]{id});
		db.close();
	}
	public List<HistoryProduct> findAll(){
		List<HistoryProduct> list = new ArrayList<HistoryProduct>();
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query("history", null, null, null, null, null, null);
		while(cursor.moveToNext()){
			HistoryProduct history = new HistoryProduct();
			String id = cursor.getString(0);
			String name = cursor.getString(1);
			history.setId(id);
			history.setName(name);
			list.add(history);
			history = null;
		}
		db.close();
		return list;
	}
	
	public void deleteAll(){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("history", null, null);
		db.close();
	}
}
