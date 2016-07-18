package com.bin.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Fragment03 fg3;
	Fragment02 fg2;
	private EditText et_main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_main=(EditText) this.findViewById(R.id.aet);
        fg3 = new Fragment03();
    	//��ȡfragment������
    	FragmentManager fm = getFragmentManager();
    	//������
    	FragmentTransaction ft = fm.beginTransaction();
    	//��������ʾ��֡����
    	ft.replace(R.id.fl, fg3);
    	//�ύ
    	ft.commit();
	}
	public void click1(View v){
		//��fragment01�Ľ�����ʾ��֡������
		//����fragment����
		Fragment01 fg1=new Fragment01();
		//��ȡfragment������
		FragmentManager fm=this.getFragmentManager();
		//������
		FragmentTransaction ft=fm.beginTransaction();
		//��������ʾ��֡����
		ft.replace(R.id.fl, fg1);
		//�ύ
		ft.commit();
	}
	 public void click2(View v){
	    	//��fragment01�Ľ�����ʾ��֡������
	    	//����fragment����
	    	fg2 = new Fragment02();
	    	//��ȡfragment������
	    	FragmentManager fm = getFragmentManager();
	    	//������
	    	FragmentTransaction ft = fm.beginTransaction();
	    	//��������ʾ��֡����
	    	ft.replace(R.id.fl, fg2);
	    	//�ύ
	    	ft.commit();
	    }
	    
	    public void click3(View v){
	    	//��fragment01�Ľ�����ʾ��֡������
	    	//��ȡfragment������
	    	FragmentManager fm = getFragmentManager();
	    	//������
	    	FragmentTransaction ft = fm.beginTransaction();
	    	//��������ʾ��֡����
	    	ft.replace(R.id.fl, fg3);
	    	//�ύ
	    	ft.commit();
	    }
	    //Activity�������ݵ�fragment������Fragment��setText()�������Զ���ķ�����
	    public void click4(View v){
	    	//��ȡ����
	    	String text=et_main.getText().toString();
	    	//��������
	    	fg2.setText(text);
	    }
	    public void setText(String text){
	    	et_main.setText(text);
	    }
	
}
