package com.shopping.redboy.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Address;
import com.shopping.redboy.util.HttpClientUtil;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
@ResID(id = R.layout.add_address_activity)
@Category(id = R.id.imgHome, title = "增加地址", leftbutton = "地址管理", rightbutton = "保存")
public class AddAddress extends BaseView implements ButtonClickListener{
	protected static final String TAG = "AddAddress";

	@ResID(id = R.id.add_address_name_edit)
	private EditText add_address_name_edit;

	@ResID(id = R.id.add_address_mobile_edit)
	private EditText add_address_mobile_edit;

	@ResID(id = R.id.add_address_tel_edit)
	private EditText add_address_tel_edit;

	@ResID(id = R.id.add_address_province_name_text)
	private TextView add_address_province_name_text;

	@ResID(id = R.id.add_address_city_name_text)
	private TextView add_address_city_name_text;

	@ResID(id = R.id.add_address_area_name_text)
	private TextView add_address_area_name_text;

	@ResID(id = R.id.add_address_detail_edit)
	private EditText add_address_detail_edit;

	@ResID(id = R.id.add_address_zipcode_edit)
	private EditText add_address_zipcode_edit;

	@ResID(id = R.id.save_address_button)
	private Button save_address_button;

	@ResID(id = R.id.cancel_address_button)
	private Button cancel_address_button;
	/**
	 * 省份对应市的map
	 * 
	 * @param context
	 */

	private ListView provinceListView;

	private PopupWindow provincepop;

	private Map<String, Map<String, List<String>>> provinceMap;

	private Map<String, List<String>> cityMap;

	private List<String> town;

	private Map<String, Map<String, List<String>>> provinceMap1;

	private Map<String, List<String>> cityMap1;

	private List<String> town1;

	/**
	 * 所有的一级地址的Strign数组
	 */
	private String[] provinceList;

	/**
	 * 记录当前的一级地址在provinceList中的位置值
	 */
	private int provinceId;

	private String[] bjseconds;

	private String[] hnseconds;

	private String[] bj_hd_thirds;

	private String[] bj_cy_thirds;

	private String[] hn_zz_thirds;

	private String[] hn_ay_thirds;

	private String currentFirst;
	private String currentSecond;

	private int currentAdapter;
	public AddAddress(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		provinceList = new String[] { "北京", "河南" };
		bjseconds = new String[] { "海淀区", "朝阳区" };
		hnseconds = new String[] { "郑州市", "安阳市" };
		bj_hd_thirds = new String[] { "中关村", "上地" };
		bj_cy_thirds = new String[] { "通州区", "四惠" };
		hn_zz_thirds = new String[] { "新郑", "金水区" };
		hn_ay_thirds = new String[] { "文峰区", "开发区" };
		
		Address address=(Address) UIManager.getInstance().getMap().get("address");
		if(address!=null){
			Log.i(TAG, "dfddfdfdfdfdfdfdfdfddfd----------");
			add_address_name_edit.setText(address.getName());
			add_address_mobile_edit.setText(address.getPhonenumber());
			add_address_tel_edit.setText(address.getFixedtel());
			add_address_province_name_text.setText(address.getProvince());
			add_address_city_name_text.setText(address.getCity());
			add_address_area_name_text.setText(address.getArea());
			add_address_detail_edit.setText(address.getAreadetail());
			add_address_zipcode_edit.setText(address.getZipcode());
		}
	}
	private void initFirstAddressListView() {
		provinceListView = new ListView(context);
		MyAdapter adapter = new MyAdapter();
		/**
		 * 设置一级地址的 适配器
		 */
		provinceListView.setAdapter(adapter);
	}
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub

			return 2;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView view = new TextView(context);
			if (currentAdapter == 1) {
				currentFirst = provinceList[position];
				view.setText(currentFirst);
			} else if (currentAdapter == 2) {
				if (add_address_province_name_text.getText().toString()
						.equals("北京")) {
					currentSecond = bjseconds[position];
					view.setText(currentSecond);
				} else if (add_address_province_name_text.getText().toString()
						.equals("河南")) {
					currentFirst = hnseconds[position];
					view.setText(currentFirst);
				}
			} else if (currentAdapter == 3) {
				if (add_address_city_name_text.getText().toString()
						.equals("海淀区")) {
					String provinceName = bj_hd_thirds[position];
					view.setText(provinceName);
				} else if (add_address_city_name_text.getText().toString()
						.equals("朝阳区")) {
					String provinceName = bj_cy_thirds[position];
					view.setText(provinceName);
				} else if (add_address_city_name_text.getText().toString()
						.equals("郑州市")) {
					String provinceName = hn_zz_thirds[position];
					view.setText(provinceName);
				} else if (add_address_city_name_text.getText().toString()
						.equals("安阳市")) {
					String provinceName = hn_ay_thirds[position];
					view.setText(provinceName);
				}
			}

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// provinceId = position;;

					if (currentAdapter == 1) {
						currentFirst = provinceList[position];
						add_address_province_name_text.setText(currentFirst);
					} else if (currentAdapter == 2) {
						if (add_address_province_name_text.getText().toString()
								.equals("北京")) {
							currentSecond = bjseconds[position];
							add_address_city_name_text.setText(currentSecond);
						} else {
							currentFirst = hnseconds[position];
							add_address_city_name_text.setText(currentFirst);
						}
					} else if (currentAdapter == 3) {
						if (add_address_city_name_text.getText().toString()
								.equals("海淀区")) {
							String provinceName = bj_hd_thirds[position];
							add_address_area_name_text.setText(provinceName);
						} else if (add_address_city_name_text.getText()
								.toString().equals("朝阳区")) {
							String provinceName = bj_cy_thirds[position];
							add_address_area_name_text.setText(provinceName);
						} else if (add_address_city_name_text.getText()
								.toString().equals("郑州市")) {
							String provinceName = hn_zz_thirds[position];
							add_address_area_name_text.setText(provinceName);
						} else if (add_address_city_name_text.getText()
								.toString().equals("安阳市")) {
							String provinceName = hn_ay_thirds[position];
							add_address_area_name_text.setText(provinceName);
						}
					}
					provincepop.dismiss();
				}

			});
			return view;
		}

	}
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		save_address_button.setOnClickListener(this);
		add_address_province_name_text.setOnClickListener(this);
		add_address_city_name_text.setOnClickListener(this);
		add_address_area_name_text.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.save_address_button:
			checkAddressInfoIsNull();
			break;
		case R.id.cancel_address_button:

			break;
		case R.id.add_address_province_name_text:
			currentAdapter = 1;
			initFirstAddressListView();
			showPopwindow();
			if (!StringUtils.isEmpty(add_address_city_name_text.getText()
							.toString())) {
				add_address_city_name_text.setText("请选择");
				add_address_area_name_text.setText("请选择");
			}
			break;
		case R.id.add_address_city_name_text:
			if (!StringUtils.isEmpty(add_address_province_name_text.getText()
					.toString())) {
				currentAdapter = 2;
				initFirstAddressListView();
				showPopwindow();
			}
			if (!StringUtils.isEmpty(add_address_area_name_text.getText()
								.toString())) {
				add_address_area_name_text.setText("请选择");
			}
			break;
		case R.id.add_address_area_name_text:
			if (!StringUtils.isEmpty(add_address_area_name_text.getText()
					.toString())
					&& !StringUtils.isEmpty(add_address_province_name_text
							.getText().toString())) {
				currentAdapter = 3;
				initFirstAddressListView();
				showPopwindow();
			}
			break;
		}
	}
	
	private void showPopwindow() {
		provincepop = new PopupWindow(context);
		provincepop.setHeight(100);
		provincepop.setContentView(provinceListView);
		provincepop.setOutsideTouchable(true);// 点击popwindow之外的区域popupwindow自动关闭
		if (currentAdapter == 3) {
			provincepop.setWidth(add_address_area_name_text.getWidth());
			provincepop.showAsDropDown(add_address_area_name_text, 0, 0);
		} else if (currentAdapter == 2) {
			provincepop.setWidth(add_address_city_name_text.getWidth());
			provincepop.showAsDropDown(add_address_city_name_text, 0, 0);
		} else if (currentAdapter == 1) {
			provincepop.setWidth(add_address_province_name_text.getWidth());
			provincepop.showAsDropDown(add_address_province_name_text, 0, 0);
		}
	}

	private void checkAddressInfoIsNull() {
		if (StringUtils.isEmpty(add_address_name_edit.getText())
				|| StringUtils.isEmpty(add_address_mobile_edit.getText())
				|| StringUtils.isEmpty(add_address_tel_edit.getText())
				|| StringUtils
						.isEmpty(add_address_province_name_text.getText())
				|| StringUtils.isEmpty(add_address_city_name_text.getText())
				|| StringUtils.isEmpty(add_address_area_name_text.getText())
				|| StringUtils.isEmpty(add_address_detail_edit.getText())
				|| StringUtils.isEmpty(add_address_zipcode_edit.getText())) {
			Toast.makeText(context, "请填写为完整的地址信息", 0).show();
			
		} else {
			Address address_to_address_manager=new Address();
			address_to_address_manager.setArea(add_address_area_name_text.getText().toString());
			address_to_address_manager.setAreadetail(add_address_detail_edit.getText().toString());
			address_to_address_manager.setCity(add_address_city_name_text.getText().toString());
			address_to_address_manager.setFixedtel(add_address_tel_edit.getText().toString());
			address_to_address_manager.setName(add_address_name_edit.getText().toString());
			address_to_address_manager.setPhonenumber(add_address_mobile_edit.getText().toString());
			address_to_address_manager.setProvince(add_address_province_name_text.getText().toString());
			address_to_address_manager.setZipcode(add_address_zipcode_edit.getText().toString());
			UIManager.getInstance().getMap().put("address_to_address_manager", address_to_address_manager);
			UIManager.getInstance().changeView(AddressAddresses_Manager_List.class);
			Toast.makeText(context, "保存成功", 0).show();
//			saveAddressToServer();
		}
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	private void saveAddressToServer() {
		// TODO Auto-generated method stub
		HttpClientUtil client = new HttpClientUtil();
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", add_address_name_edit.getText().toString());
		params.put("phonenumber", add_address_mobile_edit.getText().toString());
		params.put("fixedtel", add_address_tel_edit.getText().toString());
		params.put("areaid", "010305");
		params.put("areadetail", add_address_detail_edit.getText().toString());
		params.put("zipcode", add_address_zipcode_edit.getText().toString());
		String result = client.sendPost("/addresssave", params);
		if (result != null) {
			Toast.makeText(context, "保存成功", 0).show();
		} else {
			Toast.makeText(context, "保存失败！！！", 0).show();
		}
	}
	@Override
	public void onLeftButtonClicked() {
		// TODO Auto-generated method stub
		checkAddressInfoIsNull();
		UIManager.getInstance().changeView(AddressAddresses_Manager_List.class);
	}

	@Override
	public void onRightButtonClicked() {
		// TODO Auto-generated method stub
		
	}

}
