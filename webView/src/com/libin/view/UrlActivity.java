package com.libin.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UrlActivity extends Activity {
	private final String HOME_PAGE="http://www.2345.com";
	private String currentUrl=HOME_PAGE,url;
	private EditText etUrl;
	private Button btnEnter,btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر�����per.onCreate(savedInstanceState);
		setContentView(R.layout.activity_url);
		url=getIntent().getStringExtra("currentUrl");
		initView();
		
		
	}
	//�õ������ļ�
		public void initView(){
			etUrl = (EditText) findViewById(R.id.et_url);
			etUrl.setText(url);
			btnEnter = (Button) findViewById(R.id.btn_enter);
			btnEnter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					currentUrl=etUrl.getText().toString();
					System.out.println("currentUrl:"+currentUrl);
					if(TextUtils.isEmpty(currentUrl)){
						Toast.makeText(UrlActivity.this, "��������ַ", Toast.LENGTH_SHORT);
					}else{
						if(!(currentUrl.startsWith("http://")||currentUrl.startsWith("https://"))){
							currentUrl="http://"+currentUrl;
						}
							//������ʹ��Intent����
					        Intent intent = new Intent();
					        //�ѷ������ݴ���Intent
					        intent.putExtra("url",currentUrl);
					        //���÷�������
					        UrlActivity.this.setResult(RESULT_OK, intent);
							//�ر�Activity
					        UrlActivity.this.finish();
						
					}
				}	
			});
			
			btnBack=(Button) findViewById(R.id.btn_back);
			btnBack.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					//������ʹ��Intent����
			        Intent intent = new Intent();
			        //�ѷ������ݴ���Intent
			        intent.putExtra("url",currentUrl);
			        //���÷�������
			        UrlActivity.this.setResult(RESULT_OK, intent);
					//�ر�Activity
			        UrlActivity.this.finish();
				}
				
			});
		}
	
		
}
