package com.bin.baidumap;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.search.route.RoutePlanSearch;

import android.content.Context;

public class LocationDemo {
	private RoutePlanSearch routePlanSearch;
	private Context context;
	private BaiduMap baiduMap;
	public LocationClient mLocationClient;
	public BDLocationListener myListener;
	private BitmapDescriptor geo;
	public LocationDemo(Context context,BaiduMap baiduMap,RoutePlanSearch routePlanSearch){
		this.context=context;
		this.baiduMap=baiduMap;
		this.routePlanSearch=routePlanSearch;
	}
	public  void locate() {
		mLocationClient = new LocationClient(context);
		myListener = new MyListener();
		mLocationClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// ���ö�λģʽ
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(5000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���
		mLocationClient.setLocOption(option);
		geo = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_geo);
		MyLocationConfiguration configuration = new MyLocationConfiguration(
				MyLocationConfiguration.LocationMode.FOLLOWING, true, geo);
		baiduMap.setMyLocationConfigeration(configuration);// ���ö�λ��ʾ��ģʽ
		baiduMap.setMyLocationEnabled(true);// �򿪶�λͼ��
	}
	class MyListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation result) {
			if (result != null) {
				MyLocationData data = new MyLocationData.Builder()
						.latitude(result.getLatitude())
						.longitude(result.getLongitude()).build();
				baiduMap.setMyLocationData(data);
			}
		}

	}
	/*
	 * @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_1:
			// ����
			baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.NORMAL, true, geo));// ���ö�λ��ʾ��ģʽ
			break;
		case KeyEvent.KEYCODE_2:
			// ����
			baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.COMPASS, true, geo));// ���ö�λ��ʾ��ģʽ
			break;
		case KeyEvent.KEYCODE_3:
			// ����
			baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.FOLLOWING, true, geo));// ���ö�λ��ʾ��ģʽ
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	*/
}
