package com.shopping.redboy.engine.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.domain.ScheckOut;
import com.shopping.redboy.domain.Sinvoice;
import com.shopping.redboy.domain.SorderInfo;
import com.shopping.redboy.engine.SubmitEngie;
import com.shopping.redboy.util.HttpClientUtil;

public class SubmitEngieImpl implements SubmitEngie {
	private static final String TAG = "SubmitEngieImpl";

	/**
	 * 服务器返回的订单信息
	 */
	@Override
	public List<ScheckOut> getServiceCheckoutList() {
		List<ScheckOut> result1=new ArrayList<ScheckOut>();
		HttpClientUtil client=new HttpClientUtil();
		Map<String,String> params=new HashMap<String,String>();
		params.put("sku", "1112111111");
		String json=client.sendPost(ConstantValue.CHECKOUT, params);
		try {
			JSONObject object=new JSONObject(json);
			JSONObject orderinfo=object.getJSONObject("address_info");//地址信息
			JSONObject prymentinfo=object.getJSONObject("payment_info");//支付方式
			JSONObject deliveryinfo=object.getJSONObject("delivery_info");//送货时间
			JSONObject invoiceinfo=object.getJSONObject("invoice_info");//商品数量
			JSONObject checkoutaddup=object.getJSONObject("checkout _addup");//购买数量上限
			
			ScheckOut checkout=new ScheckOut();
			//地址信息
			checkout.setAddid(orderinfo.getString("id"));
			checkout.setAddname(orderinfo.getString("name"));
			checkout.setAddressArea(orderinfo.getString("addressArea"));
			checkout.setAddressDetail(orderinfo.getString("addressDetail"));
			
			
			result1.add(checkout);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result1;
	}
	/**
	 * 结算提交,返回的信息
	 * {"response":"orderdetail","orderinfo":
	 * {"orderid":"1112111111","paymenttype":"1","price":"230"}}
	 */
	@Override
	public List<SorderInfo> getServiceSorderInfoList() {
		List<SorderInfo> result2=new ArrayList<SorderInfo>();
		HttpClientUtil client=new HttpClientUtil();
		

		Map<String,String> params=new HashMap<String,String>();
		params.put("sku", "1112111111");
		params.put("addressid", "1001");
		params.put("paymentid", "1");
		params.put("deliveryid", "1");
		params.put("invoicetype", "1");
		params.put("invoicetitle", "北京红孩子互联网");
		params.put("invoicecontent", "1");
		
		String json=client.sendPost(ConstantValue.ORDERSUBMIT, null);
		if(json!=null){
			try {
			JSONObject object = new JSONObject(json);
				if(ConstantValue.ORDERDETAIL.equals(object.getString("response"))){
					JSONObject orderinfo=object.getJSONObject("orderinfo");
					SorderInfo orinfo=new SorderInfo();
					orinfo.setOrderid(orderinfo.getString("orderid"));
					orinfo.setPrice(orderinfo.getDouble("price"));
					orinfo.setPaymenttype(orderinfo.getInt("paymenttype"));
					result2.add(orinfo);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			Log.i(TAG, "请求失败");
		}
		return result2;
	}
	
	/**
	 * 发票
	 * {"response":"invoice",
	 * "invoice":[{"content":"图书","id":1},{"content":"音响","id":1},{"content":"游戏","id":1}]}
	 */
	@Override
	public List<Sinvoice> getServiceSinvoiceList() {
		List<Sinvoice> result3=new ArrayList<Sinvoice>();
		HttpClientUtil client=new HttpClientUtil();
		String json=client.sendPost(ConstantValue.INVOICE, null);
		if(json!=null){
			try {
				JSONObject object = new JSONObject(json);
				JSONArray jsoninfo=object.getJSONArray("invoice");
				Sinvoice invoice=new Sinvoice();
				for(int i=0;i<jsoninfo.length();i++){
					JSONObject info=(JSONObject) jsoninfo.get(i);
					invoice.setContent(info.getString("content"));
					invoice.setId(info.getInt("id"));
					result3.add(invoice);
				}
				System.out.println();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			Log.i(TAG, "请求失败！");
		}
		return result3;
	}
}
