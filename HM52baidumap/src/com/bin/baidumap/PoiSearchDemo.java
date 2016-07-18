package com.bin.baidumap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.content.Context;
import android.widget.Toast;

/**
 * ����
 * @author Administrator
 *
 */
public class PoiSearchDemo {
	private PoiSearch poiSearch;
	private Context context;
	private BaiduMap mBaiduMap;
	public  PoiSearchDemo(PoiSearch poiSearch, Context context,BaiduMap mBaiduMap){
		this.context=context;
		this.poiSearch=poiSearch;
		this.mBaiduMap=mBaiduMap;
	}
	//��Χ������
	public void serach(){
		poiSearch=PoiSearch.newInstance();
		poiSearch.setOnGetPoiSearchResultListener(new MyListener());
		
		PoiBoundSearchOption boundOption = new PoiBoundSearchOption();
		LatLngBounds latlngBounds = new LatLngBounds.Builder()
		.include(new LatLng(40.049233, 116.302675))// �����ĵ�
		.include(new LatLng(40.050645, 116.303695))// ���ϵĵ�
		.build();
		boundOption.bound(latlngBounds);// ����������Χ
		boundOption.keyword("����վ");// �����ؼ���
		poiSearch.searchInBound(boundOption);// ֻ�ǰѷ�Χ����������poi����Ϊ ��ͼ�����ģ�ͬʱ����������Χ���poi
	}
	
	//�ܱ�����
	public void searchNear(LatLng hmPos){
		poiSearch.setOnGetPoiSearchResultListener(new MyListener());
		
		PoiNearbySearchOption nearbyOption = new PoiNearbySearchOption();
		nearbyOption.location(hmPos);// �������ĵ�
		nearbyOption.radius(1000);// ���ð뾶 ��λ����
		nearbyOption.keyword("����վ");// �ؼ���
		poiSearch.searchNearby(nearbyOption);
	}
	//����������
	public void searchInCity(String city,String keyword){
		poiSearch.setOnGetPoiSearchResultListener(new SearchCityListener());
		int currentPageIndex = 0;
		PoiCitySearchOption cityOption = new PoiCitySearchOption();
		cityOption.city(city);
		cityOption.keyword(keyword);
		cityOption.pageNum(currentPageIndex);
		poiSearch.searchInCity(cityOption);
	}
	class MyListener implements OnGetPoiSearchResultListener{

		@Override
		public void onGetPoiDetailResult(PoiDetailResult arg0) {
			
		}

		@Override
		public void onGetPoiResult(PoiResult result) {
			if(result==null||SearchResult.ERRORNO.RESULT_NOT_FOUND==result.error){
				Toast.makeText(context, "δ���������", 0).show();
				return;
			}
			PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);// ����poi�ĸ�����
			mBaiduMap.setOnMarkerClickListener(overlay);// ���¼��ַ���overlay��overlay���ܴ������¼�
			overlay.setData(result);// ���ý��
			overlay.addToMap();// �������Ľ����ӵ���ͼ��
			overlay.zoomToSpan();// ���ŵ�ͼ��ʹ����Overlay���ں��ʵ���Ұ�� ע�� �÷���ֻ��Marker���͵�overlay��Ч
		}
		
	}
	
	class SearchCityListener implements OnGetPoiSearchResultListener {

		@Override
		public void onGetPoiDetailResult(PoiDetailResult result) {

		}

		@Override
		public void onGetPoiResult(PoiResult result) {
			if (result == null
					|| SearchResult.ERRORNO.RESULT_NOT_FOUND == result.error) {
				Toast.makeText(context, "δ���������", 0).show();
				return;
			}
			String text = "��" + result.getTotalPageNum() + "ҳ����"
					+ result.getTotalPoiNum() + "������ǰ��"
					+ result.getCurrentPageNum() + "ҳ����ǰҳ"
					+ result.getCurrentPageCapacity() + "��";
			
			Toast.makeText(context, text, 1).show();
			mBaiduMap.clear();// ��յ�ͼ���е� Overlay �������Լ� InfoWindow
			PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);// ����poi�ĸ�����
			mBaiduMap.setOnMarkerClickListener(overlay);// ���¼��ַ���overlay��overlay���ܴ������¼�
			overlay.setData(result);// ���ý��
			
			overlay.addToMap();// �������Ľ����ӵ���ͼ��
			overlay.zoomToSpan();// ���ŵ�ͼ��ʹ����Overlay���ں��ʵ���Ұ�� ע��
									// �÷���ֻ��Marker���͵�overlay��Ч
		}
	}
	class MyPoiOverlay extends PoiOverlay {

		public MyPoiOverlay(BaiduMap arg0) {
			super(arg0);
		}
		
		@Override
		public boolean onPoiClick(int index) {
			PoiResult poiResult = getPoiResult();
			PoiInfo poiInfo = poiResult.getAllPoi().get(index);// �õ�������Ǹ�poi��Ϣ
			String text = poiInfo.name + "," + poiInfo.address;
			Toast.makeText(context, text, 0).show();
			return super.onPoiClick(index);
		}
		
	}

}
