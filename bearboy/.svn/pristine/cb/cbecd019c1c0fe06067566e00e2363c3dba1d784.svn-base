package com.itheima.redbaby.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.itheima.redbaby.bean.Brand;
import com.itheima.redbaby.bean.order.AddressInfo;

/**
 * 地址接口
 * @author Administrator
 *
 */
public interface AddressEngine {
	/**
	 * 获取地址列表
	 * @param params
	 * @return
	 */
	public List<AddressInfo> getAddress();
	/**
	 * 三级地址
	 * @param context
	 * @param cityID
	 * @return
	 */
	public Map<String, String> getAddressCity(Context context,String cityID);
	/**
	 * 保存地址
	 */
	public String  serveAddress(Map<String, String>[] params);
	/**
	 * 删除地址
	 */
	public String  deleteAddress(String id);
}
