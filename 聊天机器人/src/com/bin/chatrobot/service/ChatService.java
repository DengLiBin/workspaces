package com.bin.chatrobot.service;

import java.util.ArrayList;
import java.util.List;

import com.bin.chatrobot.domain.Chat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bin.chatrobot.domain.Chat;

public class ChatService {
	private DBOpenHelper dbOpenHelper;
	
	public ChatService(Context context){
		this.dbOpenHelper=new DBOpenHelper(context);
	}
	
	/**
	 * 添加记录
	 * @param person
	 */
	public void save(Chat chat){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into chat(question,answer)values(?,?)",
				new Object[]{chat.getQuestion(),chat.getAnswer()});
		//db.close();关闭数据库
	}
	
	/**
	 * 查询记录
	 * @param id 记录ID
	 * @return
	 */
	public Chat find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from chat2 where id=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			int chatid = cursor.getInt(cursor.getColumnIndex("id"));
			
			String question = cursor.getString(cursor.getColumnIndex("question"));
			String answer = cursor.getString(cursor.getColumnIndex("answer"));
			return new Chat(chatid,question,answer);
		}
		cursor.close();
		return null;
	}
	
	
}
