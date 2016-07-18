package com.shopping.redboy.view;

import java.util.Map;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Address;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


@ResID(id=R.layout.address_manage_listitem)
@Category(id = R.id.imgHome, title = "修改地址", leftbutton = "地址管理", rightbutton = "保存")
public class AddressAddressUpdateItem extends BaseView implements ButtonClickListener {
	private static final String TAG = "UpdateAddressItem";
	@ResID(id=R.id.address_listitem_receiver_text)
	private TextView address_listitem_receiver_text;
	@ResID(id=R.id.address_listitem_phone_text)
	private TextView address_listitem_phone_text;
	@ResID(id=R.id.address_listitem_ads_text)
	private TextView address_listitem_ads_text;
	
	
	@ResID(id=R.id.address_manage_update_btn)
	private Button address_manage_update_btn;
	@ResID(id=R.id.address_manage_delete_btn)
	private Button address_manage_delete_btn;
	
	@ResID(id=R.id.address_listitem_layout)
	private LinearLayout address_listitem_layout;
	private String deleteId;
	private Address address;
	public AddressAddressUpdateItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().getMap().put("isDelete", false);
		UIManager.getInstance().changeView(AddressAddresses_Manager_List.class);
	}

	@Override
	public void onRightButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		address=new Address();
		showAddressItem();
	}

	private void showAddressItem() {
		address = (Address) UIManager.getInstance().getMap().get("address");
			if(address!=null){
				address_listitem_receiver_text.setText(address.getName());
				address_listitem_phone_text.setText(address.getPhonenumber());
				address_listitem_ads_text.setText(address.getProvince()+address.getCity()+address.getArea()+address.getAreadetail());
				deleteId = (String) UIManager.getInstance().getMap().get("position");
//			UIManager.getInstance().getMap().remove("address");
			}
	}
	public void onResume() {
		// TODO Auto-generated method stub
		showAddressItem();
		
	}
	
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		address_manage_update_btn.setOnClickListener(this);
		address_manage_delete_btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch(v.getId()){
		case R.id.address_manage_update_btn:
			UIManager.getInstance().changeView(AddAddress.class);
			UIManager.getInstance().getMap().put("address", address);
			break;
		case R.id.address_manage_delete_btn:
			Log.i(TAG, "shanchu");
//			address_listitem_layout.removeAllViews();
//			UIManager.getInstance().getMap().clear();
			UIManager.getInstance().getMap().put("deleteId", deleteId);
			UIManager.getInstance().changeView(AddressAddresses_Manager_List.class);
			Toast.makeText(context, "删除成功", 0).show();
			break;
		}
	}
}
