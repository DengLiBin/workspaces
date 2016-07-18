package com.bin.baidumap;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import android.content.Context;
import android.widget.Toast;

public class DrivingRouteOverlayDemo {
	private RoutePlanSearch routePlanSearch;
	private Context context;
	private BaiduMap baiduMap;
	public DrivingRouteOverlayDemo(Context context,BaiduMap baiduMap,RoutePlanSearch routePlanSearch){
		this.context=context;
		this.baiduMap=baiduMap;
		this.routePlanSearch=routePlanSearch;
	}
	
	//�ݳ�·��
	public void search(LatLng starthmPos) {
		routePlanSearch = RoutePlanSearch.newInstance();
		routePlanSearch.setOnGetRoutePlanResultListener((OnGetRoutePlanResultListener) new MyListener());
		
		
		DrivingRoutePlanOption drivingOption = new DrivingRoutePlanOption();
		PlanNode from = PlanNode.withLocation(starthmPos);// �������
		PlanNode to = PlanNode.withLocation(new LatLng(40.065796,116.349868));// �����յ�
		drivingOption.from(from);// �������
		drivingOption.to(to);// �����յ�
		List<PlanNode> nodes = new ArrayList<PlanNode>();
		nodes.add(PlanNode.withCityNameAndPlaceName("����", "�찲��"));
		drivingOption.passBy(nodes);
		drivingOption.policy(DrivingRoutePlanOption.DrivingPolicy.ECAR_DIS_FIRST);// ���ò���
		routePlanSearch.drivingSearch(drivingOption);
	}
	
	class MyListener implements OnGetRoutePlanResultListener{

		@Override
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			if (result == null
					|| SearchResult.ERRORNO.RESULT_NOT_FOUND == result.error) {
				Toast.makeText(context, "δ���������", 0).show();
				return;
			}
			DrivingRouteOverlay overlay = new MyDrivingOverlay(baiduMap);
			baiduMap.setOnMarkerClickListener(overlay);// ���¼����ݸ�overlay
			overlay.setData(result.getRouteLines().get(0));// ������·Ϊ��һ��
			overlay.addToMap();
			overlay.zoomToSpan();
			
		}

		@Override
		public void onGetTransitRouteResult(TransitRouteResult result) {
			
		}

		@Override
		public void onGetWalkingRouteResult(WalkingRouteResult result) {
			
		}
		
	}
	class MyDrivingOverlay extends DrivingRouteOverlay{

		public MyDrivingOverlay(BaiduMap arg0) {
			super(arg0);
		}
		
		@Override
		public BitmapDescriptor getStartMarker() {
			//��д�˷����Ըı�Ĭ�����ͼ��
			return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
		}
		@Override
		public BitmapDescriptor getTerminalMarker() {
			//��д�˷����Ըı�Ĭ���յ�ͼ��
			return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
		}
		
	}
}
