package com.jayqqaa12.abase.util.comm;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;

import com.jayqqaa12.abase.core.AbaseUtil;
import com.jayqqaa12.abase.util.ManageUtil;

public class NetWorkUtil extends AbaseUtil
{

	/**
	 * 判断  wifi 是否 连接
	 * @param cm
	 * @return
	 */
	public static boolean isWifiConnecting(ConnectivityManager cm)
	{
		
		return cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
	}

	/**
	 * 判断  wifi 是否 连接
	 * @param cm
	 * @return
	 */
	public static boolean isWifiConnecting()
	{
		return ManageUtil.getConnectivtyManager().getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
	}
	
	
	/**
	 * 判断 mobile 网络 是否连接
	 * @param cm
	 * @return
	 */
	public static boolean isMobileConnecting(ConnectivityManager cm)
	{
		return cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
	}
	
	
	/**
	 * 判断 mobile 网络 是否连接
	 * @param cm
	 * @return
	 */
	public static boolean isMobileConnecting()
	{
		return ManageUtil.getConnectivtyManager().getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
	}
	
	
	/**
	 * 检测是否已经连接网络。
	 * 
	 * @return 当且仅当连上网络时返回true,否则返回false。
	 */
	public static boolean isConnectingToInternet()
	{
		ConnectivityManager connectivityManager = ManageUtil.getConnectivtyManager();
		if (connectivityManager == null) { return false; }
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		return (info != null) && info.isAvailable();
	}

	/**
	 *  获得 所有 网络的 流量 包括 发送和接受的
	 * 
	 * @return
	 */
	public static long getTotalBytes()
	{
		return getTotalRxBytes()+getTotalTxBytes();
	}


	/**
	 *  获取总的接受字节数，包含Mobile和WiFi等
	 * 
	 * @return
	 */
	public static long getTotalRxBytes()
	{
		return TrafficStats.getTotalRxBytes() == TrafficStats.UNSUPPORTED ? 0 : TrafficStats.getTotalRxBytes();
	}

	/**
	 *  总的发送字节数，包含Mobile和WiFi等
	 * @return
	 */
	public static long getTotalTxBytes()
	{ 
		return TrafficStats.getTotalTxBytes() == TrafficStats.UNSUPPORTED ? 0 : TrafficStats.getTotalTxBytes() ;
	}

	
	
	/**
	 *  Mobile总的发送和接受字节数，
	 * @return
	 */
	
	public static long getTotalMobileBytes()
	{ 
		return getMobileRxBytes()+getMobileTxBytes();
	}
	
	
	
	/**
	 *  wifi总的发送和接受字节数，
	 *  
	 * @return
	 */
	public static long getTotalWifiBytes()
	{ 
		return getTotalBytes()- getTotalMobileBytes();
	}
	
	

	
	/**
	 *   获取通过Mobile连接收到的字节总数，不包含WiFi
	 * 
	 */
	public static long getMobileRxBytes()
	{	
		return TrafficStats.getMobileRxBytes() == TrafficStats.UNSUPPORTED ? 0 : TrafficStats.getMobileRxBytes();
	}

	
	/**
	 *   获取通过Mobile连接收到的字节总数，不包含WiFi
	 * 
	 */
	public static long getMobileTxBytes()
	{	
		return TrafficStats.getMobileTxBytes() == TrafficStats.UNSUPPORTED ? 0 : TrafficStats.getMobileTxBytes() ;
	}

	
	
	
	//////////////////////////---------------UID--------- ////////////////

	/**
	 *  获得 uid 所有 网络的 流量 包括 发送和接受的
	 * 
	 * @return
	 */
	public static long getUidTotalBytes(int uid)
	{
		return getUidRxBytes(uid)+getUidTxBytes(uid);
	}

	
	

	
	
	/**
	 *  获取uid 的接受字节数，包含Mobile和WiFi等
	 * 
	 * @return
	 */
	public static long getUidRxBytes(int uid)
	{
		return TrafficStats.getUidRxBytes(uid) == TrafficStats.UNSUPPORTED ? 0 : TrafficStats.getUidRxBytes(uid) ;
	}


	/**
	 *  获取uid 的发送字节数，包含Mobile和WiFi等
	 * 
	 * @return
	 */
	public static long getUidTxBytes(int uid)
	{
		return TrafficStats.getUidTxBytes(uid) == TrafficStats.UNSUPPORTED ? 0 : TrafficStats.getUidTxBytes(uid);
	}





	
	
	
}
