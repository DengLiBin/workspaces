package com.itheima.sendzdy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View v){
    	//�����Զ���㲥
    	Intent intent = new Intent();
    	//�㲥�е�actionҲ���Զ����
    	intent.setAction("com.itheima.zdy");
    	sendBroadcast(intent);
    }
    
}
