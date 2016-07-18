package com.bin.zhbj.utils.bitmap;

import com.bin.zhbj.R;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MyBitmapUtils {
	NetCacheUtils mNetCacheUtils;
	LocalCacheUtils mLocalCacheUtils;
	MemoryCacheUtils mMemoryCacheUtils;
	public MyBitmapUtils(){
		mLocalCacheUtils=new LocalCacheUtils();
		mMemoryCacheUtils=new MemoryCacheUtils();
		mNetCacheUtils=new NetCacheUtils(mLocalCacheUtils,mMemoryCacheUtils);
	}
	public void display(ImageView ivPic,String url){
		Bitmap bitmap=null;
		ivPic.setImageResource(R.drawable.news_pic_default);//����Ĭ�ϼ��ص�ͼƬ
		//���ڴ��ȡ
		 bitmap=mMemoryCacheUtils.getBitmapFromMemory(url);
		 if(bitmap!=null){
				ivPic.setImageBitmap(bitmap);
				System.out.println("���ڴ��ȡͼƬ");
				return;
			}else{
				System.out.println("�ڴ�Ϊ��");
			}
		//�ӱ��ض�ȡ
		 bitmap=mLocalCacheUtils.getBitmapFromLocal(url);
		if(bitmap!=null){
			ivPic.setImageBitmap(bitmap);
			System.out.println("�ӱ��ض�ȡͼƬ");
			System.out.println("��ͼƬ�ӱ��ر��浽�ڴ���");
			mMemoryCacheUtils.setBitmapToMemory(url, bitmap);//��ͼƬ���浽�ڴ�
			if(mMemoryCacheUtils.getBitmapFromMemory(url)==null){
				System.out.println("ͼƬû�б��浽�ڴ���");
			}else{
				System.out.println("ͼƬ�Ѿ����浽�ڴ���");
			}
			return;//�ӱ��ض�ȡ��ͼƬ�����ǲ��÷���������
		}
		//�������ȡ
		mNetCacheUtils.getBitmapFromNet(ivPic,url);
	}
}
