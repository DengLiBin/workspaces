package com.itheima.redbaby.engine.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.Invoice;
import com.itheima.redbaby.bean.PaymentInfo;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.bean.order.AddressInfo;
import com.itheima.redbaby.bean.order.Order;
import com.itheima.redbaby.engine.PaymentEngine;
import com.itheima.redbaby.utils.Logger;

public class PaymentEngineImpl extends BaseEngine implements PaymentEngine {
	public PaymentEngineImpl() {
		super();
	}

	public PaymentInfo getPaymentInfo(Context context) {

//		Map<String, String> map = new HashMap<String, String>();
//		try {
//			StringBuffer buffer = new StringBuffer();
//			List<Product> productList = paymentInfo.getProductlist();
//			for (Product product : productList) {
//				buffer.append(product.getId());
//				buffer.append(":");
//				buffer.append(product.getBuyCount());
//				buffer.append(":");
//				if (product.getProduct_propertys() != null) {
//					buffer.append(product.getProduct_propertys().get("颜色"));
//					buffer.append(",");
//					buffer.append(product.getProduct_propertys().get("大小"));
//				}
//				buffer.append("|");
//			}
//			String property = buffer.substring(0, buffer.length());
//			Logger.i("CartShopppingEngineImpl", property);
//			map.put("sku", property);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		try {

			String result = httpClient.sendPost(ConstantValue.URI
					+ ConstantValue.PAYMENT, null);
			// String result = null;
			if (result != null) {
				return parseJson(result);
			}
			// else {
			// ByteArrayOutputStream os = null;
			// try {
			// InputStream is = context.getAssets().open(
			// "jiesuanzhongxin.txt");
			// os = new ByteArrayOutputStream();
			//
			// byte[] buffer = new byte[1024 * 1024];
			//
			// while (is.read(buffer) != -1) {
			// os.write(buffer);
			// }
			//
			// result = os.toString("utf-8");
			// return parseJson(result);
			// } catch (Exception e1) {
			// e1.printStackTrace();
			// }
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PaymentInfo parseJson(String result) {
		PaymentInfo paymentInfo;
		try {
			paymentInfo = new PaymentInfo();
			JSONObject resultJson = new JSONObject(result);

			JSONObject addressJson = resultJson.getJSONObject("address_info");
			AddressInfo addressInfo = new AddressInfo();
			addressInfo.setId(addressJson.getString("id"));
			addressInfo.setName(addressJson.getString("name"));
			addressInfo.setAreaId(addressJson.getString("addressArea"));
			addressInfo.setAreaDetail(addressJson.getString("addressDetail"));
			paymentInfo.setAddressInfo(addressInfo);

			paymentInfo.setPayment_info(resultJson
					.getJSONObject("payment_info").getString("type"));
			paymentInfo.setDelivery_info(resultJson.getJSONObject(
					"delivery_info").getString("type"));

			JSONArray productlist = resultJson.getJSONArray("productlist");
			List<Product> products = new ArrayList<Product>();
			for (int i = 0; i < productlist.length(); i++) {
				Product product = new Product();

				product.setId(productlist.getJSONObject(i).getString("id"));
				product.setName(productlist.getJSONObject(i).getString("name"));
				product.setPic(productlist.getJSONObject(i).getString("pic"));
				product.setPrice(productlist.getJSONObject(i).getInt("price"));

				JSONArray proProperty = productlist.getJSONObject(i)
						.getJSONArray("product_property");
				Map<String, String> property = new LinkedHashMap<String, String>();

				for (int j = 0; j < proProperty.length(); j++) {
					JSONObject object = proProperty.getJSONObject(j);

					property.put(object.getString("key"),
							object.getString("value"));
				}
				product.setProduct_propertys(property);

				product.setNumber(productlist.getJSONObject(i).getInt("number"));
				product.setUplimit(productlist.getJSONObject(i).getInt(
						"uplimit"));
				product.setIsgigt(productlist.getJSONObject(i).getBoolean(
						"isgift"));
				products.add(product);
			}
			paymentInfo.setProductlist(products);
			JSONArray prom = resultJson.getJSONArray("checkout_prom");
			String[] proms = new String[2];
			for (int i = 0; i < prom.length(); i++) {
				proms[i] = prom.getString(i);
			}
			paymentInfo.setCheckout_prom(proms);
			JSONObject addup = resultJson.getJSONObject("checkout _addup");
			Map<String, String> addupMap = new HashMap<String, String>();
			addupMap.put("totalCount", addup.getString("totalCount"));
			addupMap.put("totalPrice", addup.getString("totalPrice"));
			addupMap.put("totalPoint", addup.getString("totalPoint"));
			addupMap.put("freight", addup.getString("freight"));
			addupMap.put("promCut", addup.getString("promCut"));
			paymentInfo.setCheckout_addup(addupMap);

			return paymentInfo;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Order submitOrder(Context context, PaymentInfo paymentInfo,Invoice invoice) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			StringBuffer buffer = new StringBuffer();
			List<Product> productList = paymentInfo.getProductlist();
			for (Product product : productList) {
				buffer.append(product.getId());
				buffer.append(":");
				buffer.append(product.getBuyCount());
				buffer.append(":");
				if (product.getProduct_propertys() != null) {
					buffer.append(product.getProduct_propertys().get("颜色"));
					buffer.append(",");
					buffer.append(product.getProduct_propertys().get("大小"));
				}
				buffer.append("|");
			}
			String property = buffer.substring(0, buffer.length());
			
			
			
			Logger.i("CartShopppingEngineImpl", property);
			map.put("sku", property);
			map.put("addressid", paymentInfo.getAddressInfo().getId());
			map.put("paymentid", paymentInfo.getPayment_info());
			map.put("deliveryid", paymentInfo.getDelivery_info());
			map.put("invoicetype", invoice.getType());
			map.put("invoicetitle", invoice.getTitle());
			map.put("invoicecontent", invoice.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			String result = httpClient.sendPost(ConstantValue.URI
					+ ConstantValue.SUBMITORDER, map);
			if (result != null) {
				return parseOrderJson(result);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	private Order parseOrderJson(String result) {
		try {
			JSONObject object = new JSONObject(result);
			
			JSONObject order = object.getJSONObject("orderinfo");
			
			Order orderInfo = new Order();
			orderInfo.setOrderid(order.getString("orderid"));
			orderInfo.setPrice(order.getString("price"));
			//orderInfo.setStatus(order.getString("paymenttype"));
			return orderInfo;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
