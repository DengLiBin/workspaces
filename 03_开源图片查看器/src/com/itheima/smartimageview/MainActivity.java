package com.itheima.smartimageview;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v){
		//����ͼƬ
		//1.ȷ����ַ
		String path = "http://192.168.13.13:8080/dd.jpg";
		//2.�ҵ�����ͼƬ�鿴������
		SmartImageView siv = (SmartImageView) findViewById(R.id.iv);
		//3.���ز���ʾͼƬ
		siv.setImageUrl(path);
	}

}
