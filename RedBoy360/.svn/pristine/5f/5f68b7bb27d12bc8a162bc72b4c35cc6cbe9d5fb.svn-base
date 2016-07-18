package com.shopping.redboy.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shopping.redboy.annotation.ColoumName;
import com.shopping.redboy.annotation.TableName;
import com.shopping.redboy.dao.DBHelper;
import com.shopping.redboy.dao.Interface.BaseDao;
import com.shopping.redboy.util.AnnotationUtil;

public class BaseDaoImpl<T> implements BaseDao<T> {
	private SQLiteDatabase db;
	
	public BaseDaoImpl(Context context) {
		db = new DBHelper(context).getWritableDatabase();
	}

	@Override
	public long insert(T t) {
		ContentValues values = new ContentValues();
		fillObjectToContentValues(t,values);
		return db.insert(getTableName(), null, values);
	}

	@Override
	public int delete(Serializable id) {
		return db.delete(getTableName(), " _id=? ", new String[]{id.toString()});
	}

	@Override
	public int update(T t) {
		ContentValues values = new ContentValues();
		fillObjectToContentValues(t,values);
		return db.update(getTableName(), values, " _id=? ",new String[]{getID(t).toString()});
	}

	@Override
	public List<T> findAll() {
		List<T> list = new ArrayList<T>();
		Cursor cursor = db.query(getTableName(), null, null, null, null, null, null);
		if(cursor != null){
			while(cursor.moveToNext()){
				T t = getInstance();
				fillCursorToObject(t, cursor);
				list.add(t);
			}
			cursor.close();
			return list;
		}
		return null;
	}
	private void fillCursorToObject(T t, Cursor cursor) {
		Field[] fields = t.getClass().getDeclaredFields();
		for(Field item : fields){
			item.setAccessible(true);
			ColoumName annotation = item.getAnnotation(ColoumName.class);
			if(annotation != null){
				String columname = annotation.value();
				Object value;
				if(annotation.autoincrement()){
					value = cursor.getInt(cursor.getColumnIndex(columname));
				}else{
					value = cursor.getString(cursor.getColumnIndex(columname));
				}
				try {
					item.set(t, value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private Object getID(T t) {
		try {
			Field field = t.getClass().getDeclaredField("id");
			field.setAccessible(true);
			return field.get(t);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	private String getTableName(){
		T t = getInstance();
		TableName annotation = t.getClass().getAnnotation(TableName.class);
		if(annotation != null){
			return annotation.value();
		}
		return null;
	}

	private T getInstance() {
		try {
			Class clazz = getClass();
			Type type = clazz.getGenericSuperclass();
			if(type != null && type instanceof ParameterizedType){
				Type[] types = ((ParameterizedType)type).getActualTypeArguments();
				if(types != null){
					return (T)((Class)types[0]).newInstance();
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private <T> void fillObjectToContentValues(T t, ContentValues values) {
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			for(Field item : fields){
				item.setAccessible(true);
				ColoumName annotation = item.getAnnotation(ColoumName.class);
				if(annotation != null){
					String key = annotation.value();
					Object value = item.get(t);
					if(!annotation.autoincrement()){
						values.put(key, value.toString());
					}
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
