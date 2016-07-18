package com.itheima.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.itheima.httpclient.utils.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(MainActivity.this, (String)msg.obj, 0).show();
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void get(View v){
    	EditText et_name = (EditText) findViewById(R.id.et_name);
    	EditText et_pass = (EditText) findViewById(R.id.et_pass);
    	
    	final String name = et_name.getText().toString();
    	final String pass = et_pass.getText().toString();
    	
    	
    	Thread t = new Thread(){
    		@Override
    		public void run() {
    			String path = "http://192.168.13.13/Web/servlet/CheckLogin?name=" + URLEncoder.encode(name) + "&pass=" + pass;
    	    	//ʹ��httpClient�����get��ʽ�ύ
    	    	//1.����HttpClient����
    	    	HttpClient hc = new DefaultHttpClient();
    	    	
    	    	//2.����httpGet���󣬹��췽���Ĳ���������ַ
    	    	HttpGet hg = new HttpGet(path);
    	    	
    	    	//3.ʹ�ÿͻ��˶��󣬰�get��������ͳ�ȥ
    	    	try {
    				HttpResponse hr = hc.execute(hg);
    				//�õ���Ӧͷ�е�״̬��
    				StatusLine sl = hr.getStatusLine();
    				if(sl.getStatusCode() == 200){
    					//�õ���Ӧͷ��ʵ��
    					HttpEntity he = hr.getEntity();
    					//�õ�ʵ���е����ݣ���ʵ���Ƿ��������ص�������
    					InputStream is = he.getContent();
    					String text = Utils.getTextFromStream(is);
    					
    					//������Ϣ�������߳�ˢ��ui��ʾtext
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
    
    public void post(View v){
    	EditText et_name = (EditText) findViewById(R.id.et_name);
    	EditText et_pass = (EditText) findViewById(R.id.et_pass);
    	
    	final String name = et_name.getText().toString();
    	final String pass = et_pass.getText().toString();
    	
    	Thread t = new Thread(){
    		@Override
    		public void run() {
    			String path = "http://192.168.13.13/Web/servlet/CheckLogin";
    	    	//1.�����ͻ��˶���
    	    	HttpClient hc = new DefaultHttpClient();
    	    	//2.����post�������
    	    	HttpPost hp = new HttpPost(path);
    	    	
    	    	//��װform���ύ������
    	    	BasicNameValuePair bnvp = new BasicNameValuePair("name", name);
    	    	BasicNameValuePair bnvp2 = new BasicNameValuePair("pass", pass);
    	    	List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
    	    	//��BasicNameValuePair���뼯����
    	    	parameters.add(bnvp);
    	    	parameters.add(bnvp2);
    	    	
    	    	try {
    	    		//Ҫ�ύ�����ݶ��Ѿ��ڼ������ˣ��Ѽ��ϴ���ʵ�����
    		    	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
    		    	//����post��������ʵ�壬��ʵ���ǰ�Ҫ�ύ�����ݷ�װ��post������������
    		    	hp.setEntity(entity);
    		    	//3.ʹ�ÿͻ��˷���post����
    				HttpResponse hr = hc.execute(hp);
    				if(hr.getStatusLine().getStatusCode() == 200){
    					InputStream is = hr.getEntity().getContent();
    					String text = Utils.getTextFromStream(is);
    					
    					//������Ϣ�������߳�ˢ��ui��ʾtext
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
