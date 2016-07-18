package com.bin.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewActivity extends Activity {
    /** Called when the activity is first created. */
	int count=1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button=(Button) findViewById(R.id.button01);
        
        button.setOnClickListener(new onClickListener());
    }
    private final class onClickListener implements View.OnClickListener{

		public void onClick(View v) {
			LinearLayout ll=(LinearLayout) findViewById(R.id.lla);//��ȡ���Բ��ֶ���
			String msg=ViewActivity.this.getResources().getString(R.string.button);//��ȡ�ַ���
			Button tempButton=new Button(ViewActivity.this);//����һ����ť
			tempButton.setText(msg+(count++));//��ť����ʾ��Ϣ
			tempButton.setWidth(80);//���ð�ť�Ŀ��
			ll.addView(tempButton);//����ť��ӵ����в�����
		}
    	
    }
}