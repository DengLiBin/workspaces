package com.bin.baidumap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import android.content.Context;
import android.widget.Toast;

/**
 * ����·��
 * @author Administrator
 *
 */
public class TransitRouteOverlayDemo {
	private RoutePlanSearch routePlanSearch;
	private Context context;
	private BaiduMap baiduMap;
	public TransitRouteOverlayDemo(RoutePlanSearch routePlanSearch,Context context,BaiduMap baiduMap){
		this.routePlanSearch=routePlanSearch;
		this.context=context;
		this.baiduMap=baiduMap;
	}
	public void search(LatLng hmPos) {
		routePlanSearch = RoutePlanSearch.newInstance();
		routePlanSearch.setOnGetRoutePlanResultListener(new MyListener());
		
		TransitRoutePlanOption transitOption = new TransitRoutePlanOption();
		PlanNode from = PlanNode.withLocation(hmPos);// �������
		PlanNode to = PlanNode.withLocation(new LatLng(40.065796,116.349868));// �����յ�
		transitOption.from(from);
		transitOption.to(to);
		transitOption.city("����");
		transitOption.policy(TransitRoutePlanOption.TransitPolicy.EBUS_WALK_FIRST);
		routePlanSearch.transitSearch(transitOption);
	}
	
	class MyListener implements OnGetRoutePlanResultListener{

		@Override
		public void onGetDrivingRouteResult(DrivingRouteResult result) {}

		@Override
		public void onGetTransitRouteResult(TransitRouteResult result) {

			if (result == null
					|| SearchResult.ERRORNO.RESULT_NOT_FOUND == result.error) {
				Toast.makeText(context, "δ���������", 0).show();
				return;
			}
			TransitRouteOverlay overlay = new TransitRouteOverlay(baiduMap);
			baiduMap.setOnMarkerClickListener(overlay);// ���¼����ݸ�overlay
			overlay.setData(result.getRouteLines().get(0));// ������·Ϊ��һ��
			overlay.addToMap();
			overlay.zoomToSpan();
			
		
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
