package com.bin.baidumap;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.RoutePlanSearch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class HelloWord extends Activity {

	protected MyBaiduSdkReceiver mBaiduSdkReceiver;
	protected BaiduMap mBaiduMap;//��������ĳһ��MapView����ת���ƶ�������
	protected MapView mMapView;
	protected double latitude=40.050966;//γ��
	protected double longitude=116.303128;//����
	protected LatLng hmPos = new LatLng(latitude, longitude);//�������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initManager();
		
		setContentView(R.layout.activity_helloword);
		
		init();
	
		PoiSearch poiSearch=PoiSearch.newInstance();
		RoutePlanSearch routePlanSearch= RoutePlanSearch.newInstance();
		//�õ�������Activity��Activity������������
		Intent intent=getIntent();
		String value=intent.getStringExtra("whatMap");
		
		// ���õ�ͼ���� MAP_TYPE_NORMAL ��ͨͼ�� MAP_TYPE_SATELLITE ����ͼ
		if(value.equals("BaseMap")){
			//��ͼ
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			mBaiduMap.setTrafficEnabled(false);
		}else if(value.equals("satelliteMap")){
			// ����ͼ
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			mBaiduMap.setTrafficEnabled(false);
		}else if(value.equals("trafficMap")){
			// ��ͨͼ
			// �Ƿ�򿪽�ͨͼ��
			mBaiduMap.setTrafficEnabled(true);
			
		}else if(value.equals("optionMap")){//��Բ�θ�����
			 OptionDemo option=new OptionDemo();
			 option.drawCircle(mBaiduMap, hmPos);
			 
		}else if(value.equals("textOptionMap")){//���ָ�����
			 OptionDemo option=new OptionDemo();
			 option.drawText(mBaiduMap, hmPos);
			 
		}else if(value.equals("markOptionMap")){//mark������
			 OptionDemo option=new OptionDemo(mMapView,getApplicationContext(), hmPos);
			 option.drawMark(mBaiduMap, hmPos);
		}else if(value.equals("searchResult")){//poi����
			
			PoiSearchDemo poiSearchDemo=new PoiSearchDemo(poiSearch,getApplicationContext(),mBaiduMap);
			//poiSearcherDemo.search();//��Χ������
			//poiSearchDemo.searchNear(hmPos);//�ܱ�����
			poiSearchDemo.searchInCity("�ɶ�","ҽԺ");//��������
		}else if(value.equals("route")){
			//�ݳ�·��
			DrivingRouteOverlayDemo route=new DrivingRouteOverlayDemo(getApplicationContext(), mBaiduMap, routePlanSearch);
			route.search(hmPos);
		}else if(value.equals("walkRoute")){
			//����·��
			DrivingRouteOverlayDemo drivingRouteOverlayDemo=new DrivingRouteOverlayDemo(getApplicationContext(), mBaiduMap, routePlanSearch);
			drivingRouteOverlayDemo.search(hmPos);
			
		}else if(value.equals("transitRoute")){//����·��
			TransitRouteOverlayDemo transitRouteOverlayDemo=new TransitRouteOverlayDemo(routePlanSearch,getApplicationContext(), mBaiduMap);
			transitRouteOverlayDemo.search(hmPos);
		}else if(value.equals("location")){
			LocationDemo locationDemo=new LocationDemo(getApplicationContext(), mBaiduMap, routePlanSearch);
			locationDemo.locate();
		}
		
	}
	
	
	protected void init(){
		//���õ�ͼ����(V2.X 3-19; V1.X 3-18)
		
		mMapView = (MapView) findViewById(R.id.mv_map);
		mBaiduMap=mMapView.getMap();//�õ�BaiduMap����
		
		//������ͼ��Ҫ�����ı仯��ʹ�ù�����MapStatusUpdateFactory����
		MapStatusUpdate mapStatusUpdateLevel = MapStatusUpdateFactory.zoomTo(19);
		//�������ż���
		mBaiduMap.setMapStatus(mapStatusUpdateLevel);
		
		//�������ĵ�
		LatLng latng=new LatLng(latitude,longitude);//�������
		MapStatusUpdate mapStatusUpdatePoint=MapStatusUpdateFactory.newLatLng(latng);
		mBaiduMap.setMapStatus(mapStatusUpdatePoint);
		
		mMapView.showZoomControls(false);//Ĭ����true,��ʾ���Ű�ť
		mMapView.showScaleControl(false);//Ĭ����true,��ʾ���
		
	}
	/**
	 * ��ʼ����ͼ
	 */
	protected void initManager() {
		SDKInitializer.initialize(getApplicationContext());//������this,������ȫ�ֵ�Context
		mBaiduSdkReceiver = new MyBaiduSdkReceiver();
		
		IntentFilter filter=new IntentFilter();
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);//��������͹㲥
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);//KEYУ��ʧ�ܣ����͹㲥
		
		registerReceiver(mBaiduSdkReceiver, filter);//����ע��㲥������
	}
	/**
	 * �㲥������
	 *����BaiDuSDK���Ĺ㲥����������keyУ��ʧ�ܣ�
	 */
	class MyBaiduSdkReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String result=intent.getAction();
			if(result==SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR){
				//�������
				Toast.makeText(getApplicationContext(), "������", 0).show();
			}else if(result==SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR){
				//keyУ��ʧ��
				Toast.makeText(getApplicationContext(), "У��ʧ��", 0).show();
			}
		}
		
	}
	
	/**
	 * ���̰���������ģ������
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_1:
			//�Ŵ�
			// �Ŵ��ͼ���ż��� ÿ�ηŴ�һ������
			MapStatusUpdate zoomInStatus=MapStatusUpdateFactory.zoomIn();
			mBaiduMap.setMapStatus(zoomInStatus);
			break;
		case KeyEvent.KEYCODE_2:
			// ��С
			// ��С��ͼ���ż��� ÿ����Сһ������
			MapStatusUpdate zooOutStatus = MapStatusUpdateFactory.zoomOut();
			mBaiduMap.setMapStatus(zooOutStatus);
			break;
		case KeyEvent.KEYCODE_3:
			// ��һ����Ϊ���� ��ת
			MapStatus mapStatus = mBaiduMap.getMapStatus();// ��ȡ��ͼ�ĵ�ǰ״̬
			float rotate = mapStatus.rotate;
			// ��ת ��Χ0~360
			MapStatus rotateStatus = new  MapStatus.Builder().rotate(rotate+30).build();
			// �����µ�MapStatus
			MapStatusUpdate rotateStatusUpdate = MapStatusUpdateFactory.newMapStatus(rotateStatus);
			mBaiduMap.setMapStatus(rotateStatusUpdate);
			break;
		case KeyEvent.KEYCODE_4:
			// ��һ��ֱ�� Ϊ�� ��ת Overlooking ����
			MapStatus mapStatusOver = mBaiduMap.getMapStatus();// ��ȡ��ͼ�ĵ�ǰ״̬
			float overlook = mapStatusOver.overlook;
			// 0~45
			MapStatus overStatus = new MapStatus.Builder().overlook(overlook-5).build();
			MapStatusUpdate overStatusUpdate = MapStatusUpdateFactory.newMapStatus(overStatus);
			mBaiduMap.setMapStatus(overStatusUpdate);		
			break;
		case KeyEvent.KEYCODE_5:
			// �ƶ�
			MapStatusUpdate moveStatusUpdate = MapStatusUpdateFactory.newLatLng(new LatLng(40.065796,116.349868));
			// �������ĸ��µ�ͼ״̬ ��ʱ300����
			mBaiduMap.animateMapStatus(moveStatusUpdate);
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onResume() {
		mMapView.onResume();//�������ڷ���
		super.onResume();
	}
	@Override
	protected void onPause() {
		mMapView.onPause();//�������ڷ���
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		unregisterReceiver(mBaiduSdkReceiver);
		mMapView.onDestroy();//�������ڷ���
		super.onDestroy();
	}
	
	
}
