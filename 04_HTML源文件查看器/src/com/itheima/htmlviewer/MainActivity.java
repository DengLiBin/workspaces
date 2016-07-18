package com.itheima.htmlviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.itheima.htmlviewer.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			TextView tv = (TextView) findViewById(R.id.tv);
			tv.setText((String)msg.obj);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v){
		Thread t = new Thread(){
			@Override
			public void run() {
				String path = "http://192.168.13.13:8080/baidu.html";
				try {
					URL url = new URL(path);
					//��ȡ���Ӷ��󣬴�ʱ��δ��������
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					//�Ƚ������ӣ�Ȼ���ȡ��Ӧ��
					if(conn.getResponseCode() == 200){
						//�õ����������ص�����������������ݾ���html��Դ�ļ�
						InputStream is = conn.getInputStream();
						//��������ı�����ȡ����
						String text = Utils.getTextFromStream(is);
						
						//������Ϣ�������߳�ˢ��ui����ʾԴ�ļ�
						Message msg = handler.obtainMessage();
						msg.obj = text;
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
