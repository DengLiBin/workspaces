package com.bin.c;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	static{
		//���ش����ϵ�so���
		System.loadLibrary("hello");//������ƣ���Anadroid.mk�ļ���ָ��
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View v){
		Toast.makeText(this, helloFromC(), 0).show();
	}
	
	public void click2(View v){
		Toast.makeText(this, "3+5�ĺ�Ϊ��"+add(3,5), 0).show();
	}
	 //����һ�����ط�������������c����ʵ��
	public native String helloFromC();
	
	public native int add(int i, int j);
}
