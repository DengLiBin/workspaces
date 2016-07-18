package com.jayqqaa12.mobilesafe.ui.space.lost;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.core.adapter.AbaseAdapter;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.domain.Contact;
import com.jayqqaa12.mobilesafe.engine.space.lost.ContactEngine;

public class SelectContactActivity extends AbaseActivity
{
	@FindView(id = R.id.lv,itemClick=true)
	private ListView lv;
	@FindView(id =R.id.title_1_tv,textId=R.string.contact_lable)
	private TextView tv_title;
	
	@FindEngine
	private ContactEngine engine;
	private List<Contact> contacts;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_contact);
		contacts = engine.getContactInfos();
		lv.setAdapter(new AbaseAdapter<Contact>(this,contacts, R.layout.lv_item_contact,new String[]{"name","number"},new int[]{R.id.lv_tv_1,R.id.lv_tv_2} ));

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		String phone = contacts.get(position).getNumber();
		Intent intent = new Intent(this, SetupGuide3Activity.class);
		intent.putExtra("phone",phone);
		setResult(0,intent);
		finish();
	}

}
