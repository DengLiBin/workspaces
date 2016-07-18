package com.shopping.redboy.util;

import java.lang.reflect.Field;

import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shopping.redboy.annotation.ColoumName;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.view.BaseView;

public class AnnotationUtil {
	public static int load(String idName)
	{
		int id=-1;
		Class<?>[] classes = com.shopping.redboy.R.class.getClasses();
		for(Class item : classes){
			String name = item.getSimpleName();
			if("id".equals(name)){
				Field[] fields = item.getDeclaredFields();
				for(Field field : fields){
					field.setAccessible(true);
					System.out.println(field.getName());
					if(field.getName().equals(idName)){
						try {
							id = field.getInt(null);
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
		}
		return id;
	}
	
	public static <V> View initField3(Class<V> clazz, View convertview, Context context){
		try {
			V v = clazz.newInstance();
			ResID clazzResID = clazz.getAnnotation(ResID.class);
			if(clazzResID != null){
				convertview = View.inflate(context, clazzResID.id(), null);
			}else{
				throw new RuntimeException("layout资源没有指定");
			}
			Field[] fields = v.getClass().getDeclaredFields();
			for(Field item : fields){
				item.setAccessible(true);
				int id = load(item.getName());
				if(id != -1){
					item.set(v, convertview.findViewById(id));
				}else{
					throw new RuntimeException("控件资源没有指定");
				}
			}
			convertview.setTag(v);
			return convertview;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ViewGroup initField(BaseView view , ViewGroup showview, Context context){
		ResID resID = view.getClass().getAnnotation(ResID.class);
		if(resID != null){
			showview = (ViewGroup) View.inflate(context, resID.id(), null);
		}else{
			throw new RuntimeException("需要指定布局文件对应的资源ID");
		}
		
		Field[] fields = view.getClass().getDeclaredFields();
		for(Field item : fields){
			item.setAccessible(true);
			ResID annotation = item.getAnnotation(ResID.class);
			if(annotation != null){
				int id = annotation.id();
				try {
					item.set(view, showview.findViewById(id));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (showview.getLayoutParams() == null) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.FILL_PARENT);
			showview.setLayoutParams(params);
		}
		return showview;
	}
	
	public static <V> View initField1(Class<V> clazz, View convertview, Context context){
		try {
			V v = clazz.newInstance();
			ResID clazzResID = clazz.getAnnotation(ResID.class);
			if(clazzResID != null){
				convertview = View.inflate(context, clazzResID.id(), null);
			}else{
				throw new RuntimeException("layout资源没有指定");
			}
			Field[] fields = v.getClass().getDeclaredFields();
			for(Field item : fields){
				item.setAccessible(true);
				ResID fieldResID = item.getAnnotation(ResID.class);
				if(fieldResID != null){
					item.set(v, convertview.findViewById(fieldResID.id()));
				}else{
					throw new RuntimeException("控件资源没有指定");
				}
			}
			convertview.setTag(v);
			return convertview;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Integer getClassResID(Class<? extends BaseView> clazz){
		ResID resID = clazz.getAnnotation(ResID.class);
		return resID.id();
	}
	

	
}
