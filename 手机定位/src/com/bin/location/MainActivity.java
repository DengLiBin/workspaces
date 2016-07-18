package com.bin.location;

import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ȡϵͳ�Ķ�λ����
		LocationManager lm=(LocationManager) getSystemService(LOCATION_SERVICE);
		List<String> allProvider=lm.getAllProviders();//��ȡ���е�λ���ṩ��
		System.out.println(allProvider);
		MyLocationListener listener=new MyLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
	}
	
	class MyLocationListener implements LocationListener{
		//λ�÷����仯
		@Override
		public void onLocationChanged(Location location) {
			System.out.println("onLocatonChanged");
			String j="���ȣ�"+location.getLongitude();
			String w="γ�ȣ�"+location.getLatitude();
			
			String accuracy="��ȷ�ȣ�"+location.getAccuracy();
			String altitude="���Σ�"+location.getAltitude();
			System.out.println("���ȣ�"+j+"γ�ȣ�"+w+"��ȷ�ȣ�"+accuracy+"���Σ�"+altitude);
		}
		// �û��ر�gps
		@Override
		public void onProviderDisabled(String provider) {
			
		}
		// �û���gps
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		//λ���ṩ��״̬�����仯
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			System.out.println("onStatusChanged");
		}
		
	}
}
