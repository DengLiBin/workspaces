package com.bin.screenadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * ���������ò���
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//�õ���Ļ���
		int width=getWindowManager().getDefaultDisplay().getWidth();
		int height=getWindowManager().getDefaultDisplay().getHeight();
		
		TextView tv1=(TextView)findViewById(R.id.tv_tv1);
		TextView tv2=(TextView) findViewById(R.id.tv_tv2);
		
		//��Ϊ���ؼ���LinearLayout,���Ե�android.widget.LinearLayout�����
		LayoutParams params=new LayoutParams(width/3,(int) (height*0.2));
		
		tv1.setLayoutParams(params);
		tv2.setLayoutParams(params);
		
		
	}


}
