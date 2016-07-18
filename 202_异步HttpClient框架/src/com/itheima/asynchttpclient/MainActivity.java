package com.itheima.asynchttpclient;

import java.net.URLEncoder;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

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
    	String url = "http://192.168.13.13/Web/servlet/CheckLogin?name=" + URLEncoder.encode(name) + "&pass=" + pass;
    	//�����첽httpclient
    	AsyncHttpClient ahc = new AsyncHttpClient();
    	
    	//����get�����ύ����
    	ahc.get(url, new MyResponseHandler());
    }
    
    public void post(View v){
    	EditText et_name = (EditText) findViewById(R.id.et_name);
    	EditText et_pass = (EditText) findViewById(R.id.et_pass);
    	
    	final String name = et_name.getText().toString();
    	final String pass = et_pass.getText().toString();
    	String url = "http://192.168.13.13/Web/servlet/CheckLogin";
    	
    	//�����첽httpclient
    	AsyncHttpClient ahc = new AsyncHttpClient();
    	
    	//����post�����ύ����
    	//��Ҫ�ύ�����ݷ�װ��RequestParams����
    	RequestParams params = new RequestParams();
    	params.add("name", name);
    	params.add("pass", pass);
    	ahc.post(url, params, new MyResponseHandler());
    }
    
    class MyResponseHandler extends AsyncHttpResponseHandler{

    	//����������ɹ�ʱ���˷�������
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				byte[] responseBody) {
			Toast.makeText(MainActivity.this, new String(responseBody), 0).show();
			
		}

		//����ʧ�ܴ˷�������
		@Override
		public void onFailure(int statusCode, Header[] headers,
				byte[] responseBody, Throwable error) {
			Toast.makeText(MainActivity.this, "����ʧ��", 0).show();
			
		}
    	
    }
    
}
