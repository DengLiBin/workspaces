package com.jayqqaa12.abase.expand;

import android.os.PowerManager;

import com.jayqqaa12.abase.core.Abase;
import com.jayqqaa12.abase.kit.ManageKit;
import com.jayqqaa12.abase.kit.common.*;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApi;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiListener;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiPOI;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiResult;

/***
 * 请加入  so  和相应的 jar
 * 
 * androidManifest.xml 配置
 *      <!-- key 配置, android:name="TencentMapSDK" android:value="您申请的Key" -->
        <meta-data
            android:name="TencentMapSDK"
            android:value="GUPBZ-TWJAS-OB5OS-6FYP7-TAGM5-J5FMZ" />
 * 
 * 使用 方式
		final TencentLoaction location =	new TencentLoaction();
		
		location.init(location.new LocListener(){
			@Override
			public void onLocationUpdate(TencentMapLBSApiResult locRes)
			{
				L.i(TencentLoaction.locResToString(locRes));
			}
		});
		
		使用后 location.stop
 * 
 * @author 12
 *
 */
public class TencentLoaction
{

	PowerManager.WakeLock mWakeLock;

	public void init(LocListener mListener)
	{
		PowerManager powerManager = ManageKit.getPowerManager();
		mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "LBSAPI");
		// 添加定位监听器
		int req = TencentMapLBSApi.getInstance().requestLocationUpdate(Abase.getContext(), mListener);
		TencentMapLBSApi.getInstance().setGPSUpdateInterval(5000);

		if (req == -2) T.ShortToast("Key不正确. 请在manifest文件中设置正确的Key");
	}

	public void stop()
	{
		if (mWakeLock != null)
		{
			mWakeLock.setReferenceCounted(false);
			mWakeLock.release();
		}
		TencentMapLBSApi.getInstance().removeLocationUpdate();
		
	}

	/**
	 * 继承TencentMapLBSApiListener类，用来接收定位结果回调
	 */
	public class LocListener extends TencentMapLBSApiListener
	{
		/**
		 * @param reqGeoType
		 *            定位的坐标类型： TencentMapLBSApi.GEO_TYPE_GCJ02 偏转后的坐标
		 *            TencentMapLBSApi.GEO_TYPE_WGS84 原始经纬度坐标
		 * 
		 * @param reqLevel
		 *            定位结果级别： TencentMapLBSApi.REQ_LEVEL_GEO 定位结果只包含经纬度
		 *            TencentMapLBSApi.LEVEL_NAME 定位结果包含经纬度与地址名称
		 *            TencentMapLBSApi.LEVEL_ADMIN_AREA 定位结果包含经纬度和行政区域划分
		 * 
		 * @param reqDelay
		 *            定位频率： TencentMapLBSApi.REQ_DELAY_SLOW 较低频率进行定位
		 *            TencentMapLBSApi.DELAY_NORMAL 均速进行定位
		 *            TencentMapLBSApi.DELAY_FAST 较高频率进行定位
		 *            TencentMapLBSApi.DELAY_FASTEST 最高频率进行定位
		 */
		public LocListener(int reqGeoType, int reqLevel, int reqDelay)
		{
			super(reqGeoType, reqLevel, reqDelay);
		}

		public LocListener()
		{
			super(TencentMapLBSApi.GEO_TYPE_GCJ02, TencentMapLBSApi.LEVEL_ADMIN_AREA, TencentMapLBSApi.DELAY_FASTEST);
		}

		/**
		 * 位置更新的回调函数，当reqType为TencentMapLBSApi.REQ_TYPE_LOC时才需重载
		 * 当有GPS时，网络定位失败也会返回GPS所带的经纬度坐标
		 * ，因此，需检查返回定位结果的Info与isMars是否与请求时一致，如不一致请检查ErrorCode
		 * 
		 * @param TencentMapLBSApiResult
		 *            locRes为返回的定位结果。 int TencentMapLBSApiResult.Info
		 *            表示TencentMapLBSApiResult： TencentMapLBSApi.RES_LEVEL_NONE
		 *            非法的结果，此时检查TencentMapLBSApiResult.ErrorCode
		 *            TencentMapLBSApi.RES_LEVEL_GEO
		 *            只包含经纬度,Latitude、Longitude、Altitude、Accuracy字段有效
		 *            TencentMapLBSApi.RES_LEVEL_NAME 除GEO级有效的字段外，还包括Name，Addr字段
		 *            TencentMapLBSApi.RES_LEVEL_ADMIN_AREA
		 *            除GEO级有效的字段外，Name，Town，Village，Street，StreetNo字段有效
		 * 
		 *            int TencentMapLBSApiResult.ErrorCode 错误代码：
		 *            TencentMapLBSApi.LOC_ERROR_NOERROR 无错误
		 *            TencentMapLBSApi.LOC_ERROR_NETWORK 网络错误
		 *            TencentMapLBSApi.LOC_ERROR_UNKNOW 未知错误 double Latitude 经度
		 *            double Longitude 纬度 double Altitude 海拨高度 double Accuracy
		 *            定位精度
		 * 
		 *            String Name
		 *            当Info为TencentMapLBSApi.RES_LEVEL_NAME时有效，最接近的POI名称 String
		 *            Addr 当Info为TencentMapLBSApi.RES_LEVEL_NAME时有效，详细地址
		 * 
		 *            String Nation
		 *            当Info为TencentMapLBSApi.RES_LEVEL_ADMIN_AREA与TencentMapLBSApi
		 *            .RES_LEVEL_POI时有效，国家 String Province
		 *            当Info为TencentMapLBSApi
		 *            .RES_LEVEL_ADMIN_AREA与TencentMapLBSApi
		 *            .RES_LEVEL_POI时有效，省份，如为直辖市，则与City相同 String City
		 *            当Info为TencentMapLBSApi
		 *            .RES_LEVEL_ADMIN_AREA与TencentMapLBSApi.RES_LEVEL_POI时有效，城市
		 *            String District
		 *            当Info为TencentMapLBSApi.RES_LEVEL_ADMIN_AREA与TencentMapLBSApi
		 *            .RES_LEVEL_POI时有效，区、县 String Town
		 *            当Info为TencentMapLBSApi.RES_LEVEL_ADMIN_AREA与TencentMapLBSApi
		 *            .RES_LEVEL_POI时有效，镇 String Village
		 *            当Info为TencentMapLBSApi.RES_LEVEL_ADMIN_AREA与TencentMapLBSApi
		 *            .RES_LEVEL_POI时有效，村 String Street
		 *            当Info为TencentMapLBSApi.RES_LEVEL_ADMIN_AREA与TencentMapLBSApi
		 *            .RES_LEVEL_POI时有效，街道、道路 String StreetNo
		 *            当Info为TencentMapLBSApi
		 *            .RES_LEVEL_ADMIN_AREA与TencentMapLBSApi.RES_LEVEL_POI时有效，门号
		 * 
		 *            Name，Addr，Nation，Province，City，District，Town，Village，
		 *            Street，StreetNo字段无效时为null，定位精度无法达到该级别时为"Unknown"
		 * 
		 *            POIList 当Info为TencentMapLBSApi.RES_LEVEL_POI时有效，周边POI列表
		 */
		@Override
		public void onLocationUpdate(TencentMapLBSApiResult locRes)
		{

		}

		/**
		 * GPS与WIFI状态更新时的回调
		 * 
		 * @param state
		 *            GPS或Wifi的状态 TencentMapLBSApi.STATE_GPS_DISABLED GPS关闭
		 *            TencentMapLBSApi.STATE_GPS_ENABLED GPS打开
		 *            TencentMapLBSApi.STATE_WIFI_DISABLED WIFI关闭
		 *            TencentMapLBSApi.STATE_WIFI_ENABLED WIFI打开
		 */
		@Override
		public void onStatusUpdate(int state)
		{
			String strState = null;
			switch (state)
			{
			case TencentMapLBSApi.STATE_GPS_DISABLED:
				strState = "Gps Disabled";
				break;
			case TencentMapLBSApi.STATE_GPS_ENABLED:
				strState = "Gps Enabled";
				break;
			case TencentMapLBSApi.STATE_WIFI_DISABLED:
				strState = "Wifi Disabled";
				break;
			case TencentMapLBSApi.STATE_WIFI_ENABLED:
				strState = "Wifi Enabled";
				break;
			}
		}
	}

	public static String locResToString(TencentMapLBSApiResult res)
	{
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(res.Info).append(" ").append(res.ErrorCode).append(" ").append(res.isMars ? "Mars" : "WGS84").append(" ")
				.append(res.LocType == 0 ? "GPS" : "Network").append("\n");
		strBuilder.append(res.Latitude).append(" ").append(res.Longitude).append("\n");
		strBuilder.append(res.Altitude).append(" ").append(res.Accuracy).append("\n");
		strBuilder.append(res.Speed).append(" ").append(res.Dir).append("\n");

		if (res.Info == TencentMapLBSApi.LEVEL_NAME)
		{
			strBuilder.append(res.Name).append(" ").append(res.Address).append("\n");
		}

		if (res.Info == TencentMapLBSApi.LEVEL_ADMIN_AREA || res.Info == TencentMapLBSApi.LEVEL_POI)
		{
			strBuilder.append(res.Nation).append(" ").append(res.Province).append(" ").append(res.City).append(" ").append(res.District)
					.append(" ").append(res.Town).append(" ").append(res.Village).append(" ").append(res.Street).append(" ")
					.append(res.StreetNo).append("\n");
		}

		if (res.Info == TencentMapLBSApi.LEVEL_POI && res.POIList != null)
		{
			strBuilder.append(res.POIList.size()).append("\n");
			for (TencentMapLBSApiPOI poi : res.POIList)
			{
				strBuilder.append(poi.Name).append(",").append(poi.Addr).append(",").append(poi.Catalog).append(",").append(poi.Dist)
						.append(",").append(poi.Latitude).append(",").append(poi.Longitude).append("\n");
			}
		}
		return strBuilder.toString();
	}

}
