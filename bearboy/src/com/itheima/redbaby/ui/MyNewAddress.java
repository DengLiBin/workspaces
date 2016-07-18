package com.itheima.redbaby.ui;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.order.AddressInfo;
import com.itheima.redbaby.engine.AddressEngine;
import com.itheima.redbaby.net.HttpClientUtil;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.AddressDialog;
import com.itheima.redbaby.utils.BeanFactory;
/**
 * 新建地址和修改地址界面
 * @author fjh
 *
 */
@SuppressLint("NewApi")
public class MyNewAddress extends BaseView implements OnClickListener{
	
	protected static final String TAG = "MyNewAddress";
	
	public TextView dl_edittext_my_address_name;
	private TextView dl_edittext_my_address_mobile;
	private TextView edittext_my_address_phone;
	
	private String areaid;
	/**
	 * 三级城市点击事件
	 */
	private RelativeLayout layout_my_address_areas;
	/**
	 * 地区
	 */
	private TextView textview_my_address_areas_content;
	/**
	 * 详细地址
	 */
	private TextView edittext_my_address_detail;
	/**
	 * 邮编
	 */
	private TextView edittext_my_address_zip_code;
	
	
	public MyNewAddress(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	AddressInfo address = new AddressInfo();

	private AddressInfo info ;

	
	@Override
	protected void findViewById() {
		dl_edittext_my_address_name = (TextView) middleView.findViewById(R.id.dl_edittext_my_address_name);
		dl_edittext_my_address_mobile = (TextView) middleView.findViewById(R.id.dl_edittext_my_address_mobile);
		edittext_my_address_phone = (TextView) middleView.findViewById(R.id.edittext_my_address_phone);
		layout_my_address_areas = (RelativeLayout) middleView.findViewById(R.id.layout_my_address_areas);
		textview_my_address_areas_content = (TextView) middleView.findViewById(R.id.textview_my_address_areas_content);
		edittext_my_address_detail = (TextView) middleView.findViewById(R.id.edittext_my_address_detail);
		edittext_my_address_zip_code = (TextView) middleView.findViewById(R.id.edittext_my_address_zip_code);
			
	}
	
	@Override
	public void onResume() {
		super.onResume();
		info = (AddressInfo) bundle.get("address");
		if(info!=null){
//			AddressInfo info = (AddressInfo) this.getBundle().get("AddressInfo");
			dl_edittext_my_address_name.setText(info.getName());
			dl_edittext_my_address_mobile.setText(info.getPhonenumber());
			edittext_my_address_phone.setText(info.getFixedtel());
			textview_my_address_areas_content.setText(info.getAreaId());
			edittext_my_address_detail.setText(info.getAreaDetail());
			edittext_my_address_zip_code.setText(info.getZipCode());
			String id = info.getId();
			
//		}
		}else{
			dl_edittext_my_address_name.setText("");
			dl_edittext_my_address_mobile.setText("");
			edittext_my_address_phone.setText("");
			textview_my_address_areas_content.setText("");
			edittext_my_address_detail.setText("");
			edittext_my_address_zip_code.setText("");
			
		}
		
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dd_new_my_address, null);
		
		
	}

	@Override
	protected void setListener() {
		layout_my_address_areas.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_my_address_areas:
		case R.id.textview_my_address_areas_content:
			//dialog 后返回的地址
			AddressDialog.dialog(context,textview_my_address_areas_content);
			
			break;

		default:
			break;
		}
		
	}
	
	@Override
	protected void processEngine() {
		// TODO Auto-generated method stub
		
	}

	//提交按钮调用
	public void myProcessEngine() {
		AddressInfo address = new AddressInfo();
		String name = dl_edittext_my_address_name.getText().toString();
		String phonenumber = dl_edittext_my_address_mobile.getText().toString();
		String fixedtel = edittext_my_address_phone.getText().toString();
		String areaid = textview_my_address_areas_content.getText().toString();
		String areadetail = edittext_my_address_detail.getText().toString();
		String zipcode = edittext_my_address_zip_code.getText().toString();
		
		
		//判空
		if(StringUtils.isEmpty(name)){
			Toast.makeText(getContext(), "用户名不能为空", 0).show();
			return;
		}
		if(phonenumber.isEmpty()){
			Toast.makeText(getContext(), "手机号码不能为空", 0).show();
			return;
		}
		if(!phonenumber.matches("^1[34568]\\d{9}$")){
			Toast.makeText(getContext(), "手机号码格式不正确", 0).show();
			return;
		}
		
		if(StringUtils.isEmpty(areaid)){
			Toast.makeText(getContext(), "地址不能为空", 0).show();
			return;
		}
		if(StringUtils.isEmpty(areadetail)){
			Toast.makeText(getContext(), "详细地址不能为空", 0).show();
			return;
		}
		if(StringUtils.isEmpty(zipcode)){
			Toast.makeText(getContext(), "邮编不能为空", 0).show();
			return;
		}
			//封装进bean里面
		address.setName(name);
		address.setPhonenumber(phonenumber);
		address.setFixedtel(fixedtel);
		address.setAreaId(areaid);
		address.setAreaDetail(areadetail);
		address.setZipCode(zipcode);
		
		
		//判断网络连接,成功则将一个map发给服务器

			Map<String, String> newAddressMap = new HashMap<String, String>();
			newAddressMap.put("name", name);
			newAddressMap.put("phonenumber",phonenumber);
			newAddressMap.put("fixedtel", fixedtel);
			newAddressMap.put("areaid", areaid);
			newAddressMap.put("areadetail", areadetail);
			newAddressMap.put("zipcode", zipcode);
			
		
			
//		post请求			
			new MyHttpTask<Map<String, String>>() {

				@Override
				protected Object doInBackground(Map<String, String>... params) {
					AddressEngine addressEngine = BeanFactory.getFactory()
							.getInstance(AddressEngine.class);
					
					 return  addressEngine.serveAddress(params);
				}
				protected void onPostExecute(Object result) {
					String s = (String) result;

					if (s != null) {
						Toast.makeText(getContext(), "保存成功", 0).show();
						MiddleManager.getInstance().changeView(MyAddressList.class, null);
					}

				};
			}.executeProxy(newAddressMap);			
	}
	
	@Override
	protected int getID() {
		if(info==null){
			return ConstantValue.NEWADDRESS;
		}
		return ConstantValue.UPDATEADDRESS;
	}
	
}
