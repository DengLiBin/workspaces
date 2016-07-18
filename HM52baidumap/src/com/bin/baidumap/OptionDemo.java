package com.bin.baidumap;

import java.util.ArrayList;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * ��Ӹ�����
 * @author Administrator
 *
 */
public class OptionDemo  {
	private MapView mapView;
	private TextView title;
	private View pop;
	public OptionDemo(){
		
	}
	public OptionDemo(MapView mapView,Context context,LatLng hmPos){
		this.mapView=mapView;
		initPop(context,hmPos);
	}
	//Բ������
	public void drawCircle(BaiduMap mBaiduMap,LatLng hmPos) {
		// ����һ��Բ
		// Բ�� + �뾶
		
		// ��ɫ + �Ƿ���� + Բ���߿�
		
		// ������Ĳ��� 
		// �� �����Լ�
		CircleOptions circleOptions = new CircleOptions();
		// �� ���Լ���������
		circleOptions.center(hmPos)// Բ��
		.radius(1000)// �뾶 ��λ����
		.fillColor(0x60FF0000);// ͸���� �� �� ��
//		.stroke(new Stroke(10, 0x600FF000));// �߿� ����1 �߿� ����2 ��ɫ
		// �� �Ѹ�������ӵ���ͼ��
		mBaiduMap.addOverlay(circleOptions);
	}
	
	//���ָ�����
	public void drawText(BaiduMap mBaiduMap,LatLng hmPos){
		TextOptions textOptions=new TextOptions();
		textOptions.fontColor(0x60FF0000)
		.text("�������Ա") // ��������
		.position(hmPos) // λ��
		.fontSize(24)// �����С
		.typeface(Typeface.SERIF);// ����
//		.rotate(30);// ��ת
		mBaiduMap.addOverlay(textOptions);
	}
	
	//mark������
	public void drawMark (BaiduMap mBaiduMap,LatLng hmPos){
		double latitude=hmPos.latitude;
		double longitude=hmPos.longitude;
		BitmapDescriptor bitmapDes = BitmapDescriptorFactory
				.fromResource(R.drawable.eat_icon);

		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(hmPos)// ����λ��
				.icon(bitmapDes)// ����ͼ��
				.draggable(true)// �����Ƿ������ק Ĭ���Ƿ�
				.title("����");// ���ñ���
		mBaiduMap.addOverlay(markerOptions);//���mark
		
		markerOptions = new MarkerOptions().title("��")
				.position(new LatLng(latitude + 0.001, longitude))
				.icon(bitmapDes);
		mBaiduMap.addOverlay(markerOptions);//���mark

		ArrayList<BitmapDescriptor> bitmaps = new ArrayList<BitmapDescriptor>();
		bitmaps.add(bitmapDes);
		bitmaps.add(BitmapDescriptorFactory.fromResource(R.drawable.icon_geo));
		markerOptions = new MarkerOptions().title("��")
				.position(new LatLng(latitude, longitude + 0.001))
				.icons(bitmaps)// ��ʾ���ͼƬ�����л� ֡����
				.period(10);// ���ö���֡ˢ��һ��ͼƬ��Դ��Marker�����ļ��ʱ�䣬ֵԽС����Խ��
		mBaiduMap.addOverlay(markerOptions);//���mark
		
		markerOptions = new MarkerOptions().title("������")
				.position(new LatLng(latitude - 0.001, longitude - 0.001))
				.icon(bitmapDes);
		mBaiduMap.addOverlay(markerOptions);//���mark
		
		mBaiduMap.setOnMarkerClickListener((OnMarkerClickListener) new MyListener());
	}
	class MyListener implements OnMarkerClickListener{

		@Override
		public boolean onMarkerClick(Marker result) {
			// �����ʱ ����pop��λ�� ����Ϊ��ʾ
			LayoutParams params = new MapViewLayoutParams.Builder()
			.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode)// ���վ�γ������λ��
			.position(result.getPosition())// ���ܴ�null
			.width(MapViewLayoutParams.WRAP_CONTENT)
			.height(MapViewLayoutParams.WRAP_CONTENT)
			.yOffset(-5)// ����position������ ��������ֵ �����Ǹ�ֵ
			.build();
			mapView.updateViewLayout(pop, params);
			pop.setVisibility(View.VISIBLE);//��ʾpop
			title.setText(result.getTitle());
			
			return true;
		}
		
	}
	private void initPop(Context context,LatLng hmPos) {
		// ����pop ��ӵ�mapview ����Ϊ����
		
		pop = View.inflate(context, R.layout.pop, null);
		LayoutParams params = new MapViewLayoutParams.Builder()
		.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode)// ���վ�γ������λ��
		.position(hmPos)// ���ܴ�null ����ΪmapModeʱ ��������position
		.width(MapViewLayoutParams.WRAP_CONTENT)
		.height(MapViewLayoutParams.WRAP_CONTENT)
		.build();
		mapView.addView(pop, params);
		pop.setVisibility(View.INVISIBLE);//Ĭ������
		title = (TextView) pop.findViewById(R.id.title);
	}
}
