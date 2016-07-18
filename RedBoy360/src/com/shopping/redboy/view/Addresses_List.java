package com.shopping.redboy.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
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
@Category(id = R.id.imgHome, title = "地址列表", leftbutton = "结算中心", rightbutton = "新增地址")
public class Addresses_List extends BaseView implements
		ButtonClickListener {

	private static final String TAG = "Address_AddressLists";

	@ResID(id = R.id.address_list_null_text)
	private TextView address_list_null_text;

	@ResID(id = R.id.address_list_list)
	private ListView address_list_list;

	private AddressListViewAdapter listAdapter;

	private List<Address> addresList;

	public Addresses_List(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		getAddressesFromServer();

		listAdapter = new AddressListViewAdapter();
		address_list_list.setAdapter(listAdapter);
		address_list_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				for(int i=0;i<addresList.size();i++){
					((ImageView)address_list_list.findViewWithTag(i)).setVisibility(View.INVISIBLE);
				}
				ImageView imageView=(ImageView) address_list_list.findViewWithTag(position);
				imageView.setVisibility(View.VISIBLE);
			}
		});
	}

	private void getAddressesFromServer() {
		HttpClientUtil client = new HttpClientUtil();
		String result = client.SendGet("/addresslist", null);
		addresList = new ArrayList<Address>();
		try {
			JSONObject json = new JSONObject(result);
			String response = json.getString("response");
			if (response.equals("addresslist")) {
				JSONArray jsonArray = json.getJSONArray("addresslist");
				for (int i = 0; i < response.length(); i++) {
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
			} else {
				Toast.makeText(context, "访问服务器出错", 0).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
						R.layout.address_list_items, null);
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
		UIManager.getInstance().changeView(SubmitTallyView.class);
	}

	@Override
	public void onRightButtonClicked() {
		// TODO Auto-generated method stub
		UIManager.getInstance().changeView(AddAddress.class);
	}

}
