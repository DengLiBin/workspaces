package com.ithiema.imageviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	static ImageView iv;
	static MainActivity ma;
	static Handler handler = new Handler(){
		//�˷��������߳��е��ã���������ˢ��ui
		public void handleMessage(android.os.Message msg) {
			//������Ϣʱ����Ҫ֪�������ǳɹ�����Ϣ������ʧ�ܵ���Ϣ
			switch (msg.what) {
			case 1:
				//��λͼ������ʾ��imageview
				iv.setImageBitmap((Bitmap)msg.obj);
				break;

			case 0:
				Toast.makeText(ma, "����ʧ��", 0).show();
				break;
			}
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		ma = this;
	}

	public void click(View v){
		Thread t = new Thread(){
			@Override
			public void run() {
				//����ͼƬ
				//1.ȷ����ַ
				String path = "http://192.168.13.13:8080/dd.jpg";
				try {
					//2.����ַ��װ��һ��url����
					URL url = new URL(path);
					//3.��ȡ�ͻ��˺ͷ����������Ӷ��󣬴�ʱ��û�н�������
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					//4.�����Ӷ�����г�ʼ��
					//�������󷽷���ע���д
					conn.setRequestMethod("GET");
					//�������ӳ�ʱ
					conn.setConnectTimeout(5000);
					//���ö�ȡ��ʱ
					conn.setReadTimeout(5000);
					//5.�����������������������
					conn.connect();
					//�����Ӧ��Ϊ200��˵������ɹ�
					if(conn.getResponseCode() == 200){
						//��ȡ��������Ӧͷ�е�������������ݾ��ǿͻ������������
						InputStream is = conn.getInputStream();
						//��ȡ����������ݣ��������λͼ����
						Bitmap bm = BitmapFactory.decodeStream(is);
						
//						ImageView iv = (ImageView) findViewById(R.id.iv);
//						//��λͼ������ʾ��imageview
//						iv.setImageBitmap(bm);
						
						Message msg = new Message();
						//��Ϣ�������Я������
						msg.obj = bm;
						msg.what = 1;
						//����Ϣ���������̵߳���Ϣ����
						handler.sendMessage(msg);
						
					}
					else{
//						Toast.makeText(MainActivity.this, "����ʧ��", 0).show();
						
						Message msg = handler.obtainMessage();
						msg.what = 0;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
		
	}

}
