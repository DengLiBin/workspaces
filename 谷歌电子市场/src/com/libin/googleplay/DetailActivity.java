package com.libin.googleplay;

import com.libin.googleplay.R;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DetailActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		ActionBar actionBar=getSupportActionBar();
		//actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);//actionBar��ʾ���Ͻǰ�ť
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   if(item.getItemId()==android.R.id.home);{//���Ͻǰ�ť�̶�д��
		   finish();//������ǰActivity��������һ��Activity
		   
		   //Ҳ�������嵥�ļ���Ϊ��Activity����һ��:android:parentActivityName="com.libingoogelplay.MainActivity" >
		   //���ԣ������actiionBar�ķ��ذ�ťʱ��������һ��activity���������Բ��ø�д�÷���
		   /*
		    * Ҫ���ݵͰ汾Android�������ã�
		    *  <meta-data
            android:name="android.support.PARENT_ACTIVITY"
            android:value="com.example.myfirstapp.MainActivity" />
		    */
	   }
	   return super.onOptionsItemSelected(item);
	}
	
}
