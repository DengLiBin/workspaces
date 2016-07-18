package com.shopping.redboy.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.redboy.annotation.JSON;

public class JSONUtil {
	/**
	 * 专题及品牌的部分json解析工具
	 * 
	 * @param json
	 * @param clazz
	 * @param startTag
	 * @return
	 */
	public static <T> List<T> getList(String json, Class<T> clazz,
			String startTag) {

		List<T> list = new ArrayList<T>();
		// 获取字段
		Field[] fields = clazz.getDeclaredFields();

		try {
			JSONArray jsonArray = new JSONObject(json).getJSONArray(startTag);

			for (int i = 0; i < jsonArray.length(); i++) {
				T t = clazz.newInstance();
				JSONObject obj = (JSONObject) jsonArray.opt(i);
				for (Field field : fields) {
					field.setAccessible(true);
					String name = field.getName();
					String value = obj.getString(name);
					if (value != null) {
						field.set(t, value);
					}
				}
				list.add(t);
			}
			return list;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static List<Class> classes;
	static {
		classes = new ArrayList<Class>();
		classes.add(Integer.class);
		classes.add(Double.class);
		classes.add(Float.class);
	}

	public static <T> List<T> getList_New(String json, Class<T> clazz,
			String startTag) {

		List<T> list = new ArrayList<T>();
		// 获取字段
		Field[] fields = clazz.getDeclaredFields();

		try {
			JSONArray jsonArray = new JSONObject(json).getJSONArray(startTag);

			for (int i = 0; i < jsonArray.length(); i++) {
				T t = clazz.newInstance();
				JSONObject obj = (JSONObject) jsonArray.opt(i);
				for (Field field : fields) {

					JSON annotation = field.getAnnotation(JSON.class);
					if (annotation == null) {
						continue;
					}
					field.setAccessible(true);
					String jsonName = annotation.name();
					Class type = annotation.type();
					try {
						if (type == Integer.class) {
							int value = obj.getInt(jsonName);
							field.set(t, value);
						} else if (type == Float.class) {
							float value = (float) obj.getDouble(jsonName);
							field.set(t, value);
						} else if (type == Double.class) {
							double value = obj.getDouble(jsonName);
							field.set(t, value);
						} else if (type == String.class) {
							String value = obj.getString(jsonName);
							field.set(t, value);
						} else if (type == Long.class) {
							long value = obj.getLong(jsonName);
							field.set(t, value);
						}
					} catch (Exception e) {
						continue;
					}
				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 未完成
	 * 
	 * @param json
	 * @param clazz
	 * @param startTag
	 * @return
	 */
	public static <T> List<T> getList_Normal(String json, Class<T> clazz,
			String startTag) {

		List<T> list = new ArrayList<T>();
		// 获取字段
		Field[] fields = clazz.getDeclaredFields();

		try {
			JSONArray jsonArray = new JSONObject(json).getJSONArray(startTag);

			for (int i = 0; i < jsonArray.length(); i++) {
				T t = clazz.newInstance();
				JSONObject obj = (JSONObject) jsonArray.opt(i);
				for (Field field : fields) {

					JSON annotation = field.getAnnotation(JSON.class);
					if (annotation == null) {
						continue;
					}
					field.setAccessible(true);
					String jsonName = annotation.name();
					Class type = annotation.type();
					try {
						if (type == Integer.class) {
							int value = obj.getInt(jsonName);
							field.set(t, value);
						} else if (type == Float.class) {
							float value = (float) obj.getDouble(jsonName);
							field.set(t, value);
						} else if (type == Double.class) {
							double value = obj.getDouble(jsonName);
							field.set(t, value);
						} else if (type == String.class) {
							String value = obj.getString(jsonName);
							field.set(t, value);
						} else if (type == Long.class) {
							long value = obj.getLong(jsonName);
							field.set(t, value);
						}
					} catch (Exception e) {
						continue;
					}
				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			try {
				JSONObject jsonObject = new JSONObject(json)
						.getJSONObject(startTag);
				JSONArray names = jsonObject.names();
				T t = clazz.newInstance();
				Field[] fields_ = clazz.getFields();
				for (int i = 0; i < names.length(); i++) {
					String name = names.getString(i);

					for (Field field : fields_) {
						JSON annotation = field.getAnnotation(JSON.class);
						if (name.equals(annotation.name())) {
							// (field.getClass())
						}
					}

				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
}
