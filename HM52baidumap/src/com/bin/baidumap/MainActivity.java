package com.bin.baidumap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	//�����ͼ
	public void enterMap(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "baseMap");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//��������ͼ
	public void enterSatelliteMap(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "satelliteMap");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//���뽻ͨͼ
	public void enterTrafficMap(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "trafficMap");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//��Բ�θ�����ĵ�ͼ
	public void enterOptionsMap(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "optionMap");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//�����ָ�����ĵ�ͼ
	public void enterTextOptionsMap(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "textOptionMap");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//mark������
	public void enterMarkOptionsMap(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "markOptionMap");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//�������
	public void searchResult(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "searchResult");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//�ݳ�·��
	public void route(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "route");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//����·��
	public void walkRoute(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "walkRoute");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//����·��
	public void transitRoute(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "transitRoute");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}
	//��λ
	public void location(View v){
		Intent intent=new Intent();
		intent.putExtra("whatMap", "location");
		intent.setClass(MainActivity.this, HelloWord.class);
		startActivity(intent);
	}

}
