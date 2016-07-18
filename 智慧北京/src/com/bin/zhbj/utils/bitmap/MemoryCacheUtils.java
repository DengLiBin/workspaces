package com.bin.zhbj.utils.bitmap;

import java.util.HashMap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * �ڴ滺�棬�ڴ��з���Ķ���̫�࣬������ڴ����
 * @author Administrator
 *
 */
public class MemoryCacheUtils {
	//private HashMap<String,Bitmap> hashMap=new HashMap<String,Bitmap>();
	/* LruCache:least recentlly use �������ʹ���㷨
	�Ὣ�ڴ������һ���Ĵ�С��, �������ֵʱ���Զ�����, ������ֵ�������Լ���
	*/
	private LruCache<String,Bitmap> lruCache;//��HashMap����
	
	public MemoryCacheUtils(){
		long maxMemory=Runtime.getRuntime().maxMemory();//AndroidĬ�ϸ�ÿ��appֻ����16M���ڴ�
		lruCache=new LruCache<String,Bitmap>((int)maxMemory/8){//���� ռ���ڴ��С
			@Override
			protected int sizeOf(String key, Bitmap value) {
				int byteCount=value.getByteCount();// return getRowBytes() * getHeight();
				//int byteCount=value.getRowBytes()*value.getHeight();//��ȡͼƬռ���ڴ��С
				return byteCount;
			}
		};
	}
	/**
	 * ���ڴ��ȡ
	 */
	public Bitmap getBitmapFromMemory(String url){
		return lruCache.get(url);
	}
	/**
	 * д���ڴ�
	 */
	public void setBitmapToMemory(String url, Bitmap bitmap){
		lruCache.put(url, bitmap);
	}
}
