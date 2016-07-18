package com.itheima.redbaby.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

import com.itheima.redbaby.engine.AddressEngine;
import com.itheima.redbaby.ui.MyNewAddress;

public  class AddressDialog {
	//1.点击进来,首先判断网络状况,有网络后发送get请求0;返回省份的Map
	private static String cityID;
	private static Map<String,String> cityMap;
	private static String [] cityIDs;
	private static String [] citys;
	static String address_area ;
	static String address_area_id ;
	private static StringBuffer values;
	private static StringBuffer keys;
	 
	
	/**
	 * 
	 * @param context
	 * @return Map<地区ID, 省+市+地区>
	 */
	
	public static String dialog(final Context context,final TextView tv) {
		address_area = "";
		address_area_id = "";
		/**
		 * 返回用map
		 */
		Map<String, String> address_areaMap = new LinkedHashMap<String, String>();
		//用工厂类调用联网方法,并解析
		cityID="0";//省 的请求	ID为0
		cityMap = BeanFactory.getInstance(AddressEngine.class).getAddressCity(context, cityID);
		values = new StringBuffer();
		 keys = new StringBuffer();
		if(!cityMap.isEmpty()){
			Set<String> keySet = cityMap.keySet();
			for(String key : keySet){
				String value = (String) cityMap.get(key);
				keys.append(key+",");
				values.append(value+",");
			}
			String string = keys.toString();
			String string2 = values.toString();
			 cityIDs = StringUtils.split(string, ",");
			 citys = StringUtils.split(string2, ",");
			 
//		cityIDs	= (String[])keys.toString();
//		citys  = (String[]) values.toString();

		
		final AlertDialog.Builder builder = new Builder(context);

		builder.setTitle("请选择省份");

		builder.setSingleChoiceItems(citys, -1,new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, int which) {
				
				// 保存选择参数
				cityID =cityIDs[which];
				address_area += citys[which];
				address_area_id += cityID;
				// ****************************************************
				cityMap = BeanFactory.getInstance(AddressEngine.class).getAddressCity(context, cityID);
				values = new StringBuffer();
				 keys = new StringBuffer();
				
//				cityMap = null;
				if (!cityMap.isEmpty()) {// 判断网络是否连接
					Set<String> keySet = cityMap.keySet();
					for(String key : keySet){
						String value = (String) cityMap.get(key);
						keys.append(key+",");
						values.append(value+",");
					}
					String string = keys.toString();
					String string2 = values.toString();
					 cityIDs = StringUtils.split(string, ",");
					 citys = StringUtils.split(string2, ",");
							
//					String[] items2 = { "北京2", "山西2", "陕西2", "辽宁2","河南2" };
					AlertDialog.Builder builder = new Builder(context);
					builder.setTitle("请选择城市");
					builder.setSingleChoiceItems(citys, -1,new DialogInterface.OnClickListener() {
						@Override
						public void onClick(final DialogInterface dialog2,int which) {
							// 保存选择参数
							cityID =cityIDs[which];				
							address_area += citys[which];
							address_area_id += cityID;
							// ****************************************************
							cityMap = BeanFactory.getInstance(AddressEngine.class).getAddressCity(context, cityID);
							values = new StringBuffer();
							 keys = new StringBuffer();
							if (!cityMap.isEmpty()) {// 判断网络是否连接
								
								Set<String> keySet = cityMap.keySet();
								for(String key : keySet){
									String value = (String) cityMap.get(key);
									keys.append(key+",");
									values.append(value+",");
								}
								String string = keys.toString();
								String string2 = values.toString();
								 cityIDs = StringUtils.split(string, ",");
								 citys = StringUtils.split(string2, ",");

							AlertDialog.Builder builder = new Builder(context);
							builder.setTitle("请选择地区");
							builder.setSingleChoiceItems(citys,-1,new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog3,int which) {

									// 保存选择参数
									cityID =cityIDs[which];		
									address_area += citys[which];
									address_area_id += cityID;
																
									// ****************************************************
									if(cityID!=null&&address_area!=null){
										
										tv.setText(address_area);
									}
									dialog3.dismiss();
//									dialog2.dismiss();
//									dialog.dismiss();

								}

							});

								builder.setNegativeButton("cancel", null);
								builder.show();
							}
							if(cityID!=null&&address_area!=null){
								
								tv.setText(address_area);
							}
							dialog2.dismiss();
							
					}

				});

					builder.setNegativeButton("cancel", null);
					builder.show();
			}
				dialog.dismiss();
				
				}
		});
		builder.setNegativeButton("cancel", null);
		builder.show();
		address_areaMap.put(address_area_id, address_area);
	
//		return address_areaMap;
		return address_area;
		}
		return null;

	}

}
