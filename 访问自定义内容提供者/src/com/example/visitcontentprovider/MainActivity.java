package com.example.visitcontentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void insert(View v){
    	//ͨ�������ṩ�ߣ������ݲ��뵽���Զ��������ṩ����Ŀ����people���ݿ�
    	//�õ�contentResolver
    	ContentResolver cr=getContentResolver();
    	ContentValues values=new ContentValues();
    	for(int i=1;i<=20;i++){
    		values.put("name", "����"+i);
//        	values.put("money","13000"+i*100);
 //       	cr.insert(Uri.parse("content://com.bin.people"), values);//����������ṩ�ߵ�insert����
    		//�����ݿ��teacher���в�������
            cr.insert(Uri.parse("content://com.bin.people/teacher"), values);//����������ṩ�ߵ�insert����(�Ƚ���uriƥ��)
        	
    	}
   
     }	
    public void delete(View v){
    	ContentResolver cr=this.getContentResolver();
    	ContentValues values=new ContentValues();
    	int i=cr.delete(Uri.parse("content://com.bin.people"), "name=?",new String[]{"��˧��"});
    	System.out.println("ɾ�� ��"+i+"��");
    }
    public void update(View v){
    	ContentResolver cr=this.getContentResolver();
    	ContentValues values=new ContentValues();
    	values.put("name", "sb־");
    	int i=cr.update(Uri.parse("content://com.bin.people"), values, "name=?", new String[]{"����4"});
    	System.out.println("�޸���"+i+"��");
    }
    public void query(View v){
    	ContentResolver cr=this.getContentResolver();
    //	Cursor cursor=cr.query(Uri.parse("content://com.bin.people"), null,null,null,null);
    //	Cursor cursor=cr.query(Uri.parse("content://com.bin.people//teacher"), null,null,null,null);
    	Cursor cursor=cr.query(Uri.parse("content://com.bin.people//person/4"), null,null,null,null);//����ͨ��URI�������ݣ�4��
    	while(cursor.moveToNext()){
    		String name=cursor.getString(1);
    		String money=cursor.getString(2);
    		System.out.println(name+"---"+money);
    	}
    	
    }
}
