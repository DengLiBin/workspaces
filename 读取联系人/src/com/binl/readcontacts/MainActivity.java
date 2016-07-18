package com.binl.readcontacts;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ��ȡ��ϵ����Ϣ���õ���ContentResolver
 * @author DengLibin
 *
 */
public class MainActivity extends Activity {
	private ListView lvList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvList=(ListView) findViewById(R.id.lv_list);
		ArrayList<HashMap<String,String>> readContacts=readContacts();
		
		lvList.setAdapter(new SimpleAdapter(this,readContacts,R.layout.contact_list_item,
				new String[]{"name","phone"},new int[]{R.id.tv_name,R.id.tv_phone}));
	}
	private ArrayList<HashMap<String,String>> readContacts(){
		// ����,��raw_contacts�ж�ȡ��ϵ�˵�id("contact_id")
		// ���, ����contact_id��data���в�ѯ����Ӧ�ĵ绰�������ϵ������
		// Ȼ��,����mimetype�������ĸ�����ϵ��,�ĸ��ǵ绰����
		Uri rawContactsUri=Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri=Uri.parse("content://com.android.contacts/data");
		ContentResolver cr=getContentResolver();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Cursor rawContactsCursor=cr.query(rawContactsUri, new String[]{"contact_id"}, null, null, null);
		
		if(rawContactsCursor!=null){
			while(rawContactsCursor.moveToNext()){
				String contactId=rawContactsCursor.getString(0);//0��ʾcontact_id
				//System.out.println(contactId);
				// ����contact_id��data���в�ѯ����Ӧ�ĵ绰�������ϵ������, ʵ���ϲ�ѯ������ͼview_data
				Cursor dataCursor = cr.query(dataUri,
						new String[] { "data1", "mimetype" }, "contact_id=?",
						new String[] { contactId }, null);
				if (dataCursor != null){
					HashMap<String, String> map = new HashMap<String, String>();
					while(dataCursor.moveToNext()){
						String data1 = dataCursor.getString(0);//data1,�绰����
						String mimetype = dataCursor.getString(1);//mimetype
						System.out.println(contactId + ";" + data1 + ";"+ mimetype);
						
						if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
							map.put("phone", data1);
						} else if ("vnd.android.cursor.item/name"
								.equals(mimetype)) {
							map.put("name", data1);
						}
					}
					list.add(map);//һ��map��Ӧһ����ϵ�ˣ����ֺ͵绰��
					dataCursor.close();
					
				}
			}
			rawContactsCursor.close();
			
		}
		return list;
		
	}
}
