package com.bin.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends Activity {
	private View root;
	private PopupWindow popup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		root=this.getLayoutInflater().inflate(R.layout.popup,null);
		popup = new PopupWindow(root,280,360);
	}
	
	public void click1(View v){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("�Զ�����ͨ�Ի���");
		builder.setMessage("������ʾ��Ϣ");
		
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "�����ȷ����ť", Toast.LENGTH_LONG).show();
				
			}
		});
		
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "�����ȡ����ť", Toast.LENGTH_LONG).show();
			}
		});
		
		builder.create().show();
	}
	
	public void click2(View v){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("�Զ��嵥�б�Ի���");
		builder.setSingleChoiceItems(new String[]{"item1","item2","item3","item4"}, 2, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "item"+which+"ѡ����",Toast.LENGTH_SHORT).show();	
			}
		});
		
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "�����ȷ����ť", Toast.LENGTH_LONG).show();
				
			}
		});
	
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "�����ȡ����ť", Toast.LENGTH_LONG).show();
			}
		});
		builder.create().show();
	}
	
	
	public void click3(View v){
		popup.showAsDropDown(v);
		
		//ָ����ʾ��λ��
		popup.showAtLocation(findViewById(R.id.btn), Gravity.CENTER, 20, 20);
		Button close=(Button)root.findViewById(R.id.close);
		
		close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popup.dismiss();//�ر�
			}
		});
	}
}
