package com.shopping.redboy.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Address;
import com.shopping.redboy.util.HttpClientUtil;
import com.shopping.redboy.view.AddAddress;
import com.shopping.redboy.view.BaseView;
import com.shopping.redboy.view.SubmitTallyView;

@ResID(id = R.layout.address_list_activity)
@Category(id = R.id.imgHome, title = "地址管理", leftbutton = "返回", rightbutton = "新增地址")
public class AddressAddresses_Manager_List extends BaseView implements
		ButtonClickListener {

	private static final String TAG = "Address_AddressLists";

	protected static final int ERRORNET = 0;

	protected static final int ERRORSERVER = 1;

	protected static final int ERRORJSON = 2;

	protected static final int SUCCESS = 3;

	@ResID(id = R.id.address_list_null_text)
	private TextView address_list_null_text;

	@ResID(id = R.id.address_list_list)
	private ListView address_list_list;

	private AddressListViewAdapter listAdapter;

	private List<Address> addresList;

	public AddressAddresses_Manager_List(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	Handler hanlder=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case ERRORNET:
				Toast.makeText(context, "访问网络出错", 0).show();
				break;
			case ERRORSERVER:
				Toast.makeText(context, "服务器出错", 0).show();
				break;
			case ERRORJSON:
				Toast.makeText(context, "JSON解析出错", 0).show();
				break;
			case SUCCESS:
				initAllPage();
				break;
			}
			
		}
		
		
		
	};
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		getAddressesFromServer();
		
	}
	private void initAllPage() {
		listAdapter = new AddressListViewAdapter();
		address_list_list.setAdapter(listAdapter);
		address_list_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map addressMap=UIManager.getInstance().getMap();
				addressMap.put("address", addresList.get(position));
				addressMap.put("position", position+"");
				UIManager.getInstance().changeView(AddressAddressUpdateItem.class);
			}
		});
	}
	@Override
	public void onResume() {
			
		if(UIManager.getInstance().getMap().get("position")!=null && UIManager.getInstance().getMap().get("isDelete")==null){
//			address_list_list.removeViewAt(Integer.parseInt((String) UIManager.getInstance().getMap().get("position")));
			addresList.remove(Integer.parseInt((String) UIManager.getInstance().getMap().get("position")));
			UIManager.getInstance().getMap().remove("position");
			listAdapter.notifyDataSetChanged();
			UIManager.getInstance().getMap().remove("isDelete");
		}
		if(UIManager.getInstance().getMap().get("address_to_address_manager")!=null){
			addresList.add((Address) UIManager.getInstance().getMap().get("address_to_address_manager"));
			UIManager.getInstance().getMap().remove("address_to_address_manager");
			listAdapter.notifyDataSetChanged();
		}
//		UIManager.getInstance().getMap().clear();
		super.onResume();
	}
	private void getAddressesFromServer() {
		new Thread(){
			public void run() {
				Message  msg=Message.obtain();
				HttpClientUtil client = new HttpClientUtil();
				String result = client.SendGet("/addresslist", null);
				if(result==null){
					msg.what=ERRORNET;
					return;
				}else{
					addresList = new ArrayList<Address>();
					try {
						JSONObject json = new JSONObject(result);
						String response = json.getString("response");
						if (response.equals("addresslist")) {
							JSONArray jsonArray = json.getJSONArray("addresslist");
							for (int i = 0; i < jsonArray.length(); i++) {
								Address address = new Address();
								address.setArea(jsonArray.getJSONObject(i).get("area")
										.toString());
								address.setAreadetail(jsonArray.getJSONObject(i)
										.get("areadetail").toString());
								address.setCity(jsonArray.getJSONObject(i).get("city")
										.toString());
								address.setFixedtel(jsonArray.getJSONObject(i)
										.get("fixedtel").toString());
								address.setId(Integer.parseInt(jsonArray.getJSONObject(i)
										.get("id").toString()));
								address.setName(jsonArray.getJSONObject(i).get("name")
										.toString());
								address.setPhonenumber(jsonArray.getJSONObject(i)
										.get("phonenumber").toString());
								address.setProvince(jsonArray.getJSONObject(i)
										.get("province").toString());
								address.setZipcode(jsonArray.getJSONObject(i)
										.get("zipcode").toString());
								addresList.add(address);
							}
							msg.what=SUCCESS;
						} else {
							msg.what=ERRORSERVER;
							Toast.makeText(context, "访问服务器出错", 0).show();
							return;
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						msg.what=ERRORJSON;
					}finally{
						hanlder.sendMessage(msg);
					}
				}
				
			};
		}.start();
		
	}

	private class AddressListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return addresList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context,
						R.layout.address_manage_items, null);
				holder.addressnameTV = (TextView) convertView
						.findViewById(R.id.addressnameTV);
				holder.phoneTV = (TextView) convertView
						.findViewById(R.id.phoneTV);
				holder.address0TV = (TextView) convertView
						.findViewById(R.id.address0TV);
				holder.addressdetilTV = (TextView) convertView
						.findViewById(R.id.addressdetilTV);
				holder.ok = (ImageView) convertView.findViewById(R.id.ok);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.addressnameTV.setText(addresList.get(position).getName());
			holder.phoneTV.setText(addresList.get(position).getPhonenumber());
			holder.address0TV.setText(addresList.get(position).getProvince()
					+ addresList.get(position).getCity());
			holder.addressdetilTV.setText(addresList.get(position)
					.getProvince()
					+ addresList.get(position).getCity()
					+ addresList.get(position).getArea()
					+ addresList.get(position).getAreadetail());
			holder.ok.setTag(position);
			return convertView;
		}

	}

	class ViewHolder {
		TextView addressnameTV;
		TextView phoneTV;
		TextView address0TV;
		TextView addressdetilTV;
		ImageView ok;
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeftButtonClicked() {
		// TODO Auto-generated method stub
		UIManager.getInstance().changeView(MoreView.class);
	}

	@Override
	public void onRightButtonClicked() {
		// TODO Auto-generated method stub
		UIManager.getInstance().getMap().put("addaddress", "addaddress");
		UIManager.getInstance().changeView(AddAddress.class);
	}

}
