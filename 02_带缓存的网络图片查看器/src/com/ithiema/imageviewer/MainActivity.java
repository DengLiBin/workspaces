package com.ithiema.imageviewer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.ithiema.cacheimageviewer.R;

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
		//����ͼƬ
		//1.ȷ����ַ
		final String path = "http://192.168.13.13:8080/dd.jpg";
		final File file = new File(getCacheDir(), getFileName(path));
		//�жϣ��������Ƿ���ڸ��ļ�
		if(file.exists()){
			//���������ڣ��ӻ����ȡͼƬ
			System.out.println("�ӻ����ȡ��");
			Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
			iv.setImageBitmap(bm);
		}
		else{
			//������治���ڣ�����������
			System.out.println("���������ص�");
			Thread t = new Thread(){
				@Override
				public void run() {
					
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
							
							//��ȡ���������ص���������ݣ�������д�������ļ�����������
							
							FileOutputStream fos = new FileOutputStream(file);
							byte[] b = new byte[1024];
							int len = 0;
							while((len = is.read(b)) != -1){
								fos.write(b, 0, len);
							}
							fos.close();
							
							//��ȡ����������ݣ��������λͼ����
							//�����Ѿ�û��������
//							Bitmap bm = BitmapFactory.decodeStream(is);
							Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
							
							
							Message msg = new Message();
							//��Ϣ�������Я������
							msg.obj = bm;
							msg.what = 1;
							//����Ϣ���������̵߳���Ϣ����
							handler.sendMessage(msg);
							
						}
						else{
//							Toast.makeText(MainActivity.this, "����ʧ��", 0).show();
							
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
	
	public String getFileName(String path){
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}

}
