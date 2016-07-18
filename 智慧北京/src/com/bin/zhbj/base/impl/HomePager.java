package com.bin.zhbj.base.impl;

import java.io.File;
import java.io.FileOutputStream;

import com.bin.zhbj.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class HomePager extends BasePager {
	public HomePager(Activity activity) {
		super(activity);
		
	}
	
//ContentFragment�е���	
	@Override
	public void initData() {
		super.setSlidingMenuEnable(false);
		imgBtn.setVisibility(View.INVISIBLE);//���ذ�ť
		tvTitle.setText("�ǻ۱���");
		TextView text=new TextView(mActivity);
		text.setText("��ҳ");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		flContent.addView(text);//��textView��ӵ�frameLayout��
		
		String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/zhbj_cache_52";
		//String path="/storage/sdcard1/zhbj_cache_52";
		System.out.println("path:"+path);
		File file=new File(path,"denglibin.txt");
		File parentFile=file.getParentFile();
		if(!parentFile.exists()){//����ļ��в����ڣ������ļ���
			parentFile.mkdir();
		}
		try{
			FileOutputStream out=new FileOutputStream(file);
			out.write("�ַ���".getBytes());
			out.close();
			System.out.println("·������");
		}catch(Exception e){
			System.out.println("·��������");
			e.printStackTrace();
		}
		
	}

}
