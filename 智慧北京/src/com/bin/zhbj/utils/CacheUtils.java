package com.bin.zhbj.utils;

import android.content.Context;

/**
 * @author Administrator
 *���湤����
 */
public class CacheUtils {
	/**
	 * ���û���
	 * key��json��url
	 * value��json��ʽ���ַ����ı��ļ�
	 */
	public static void setCache(Context ctx,String key,String value){
		PrefUtils.setString(ctx,key,value);
		//���Խ���������ļ��У��ļ�������MD5(url)���ļ�����json
	}
	
	/**
	 * ��ȡ����
	 */
	public static String getCache(Context ctx,String key){
		return PrefUtils.getString(ctx, key,null);
	}
}
