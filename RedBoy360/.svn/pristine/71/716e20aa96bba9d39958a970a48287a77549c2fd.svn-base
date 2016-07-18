package com.shopping.redboy.engine.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.shopping.redboy.domain.Address;
import com.shopping.redboy.domain.Invoice;
import com.shopping.redboy.domain.Order;
import com.shopping.redboy.domain.OrderDetail;
import com.shopping.redboy.domain.OrderProd;
import com.shopping.redboy.engine.OrderDetailEngine;
import com.shopping.redboy.util.HttpClientUtil;

public class OrderDetailEngineImpl implements OrderDetailEngine {

	@Override
	public OrderDetail getOrderDetail() {
		String mapping = "/orderdetail";

		Map<String, String> params = new HashMap<String, String>();
		params.put("orderId", "111024344654");
		try {
			String json = new HttpClientUtil().SendGet(mapping, params);
			JSONObject object = new JSONObject(json);
			OrderDetail orderDetail = new OrderDetail();
			// 获取订单详情内的模块信息
			String orderInfoStr = object.getString("order_info");
			String addressInfoStr = object.getString("address_info");
			String paymentInfoStr = object.getString("payment_info");
			String deliveryInfoStr = object.getString("delivery_info");
			String invoiceInfoStr = object.getString("invoice_info");
			String productListStr = object.getString("productlist");
			String checkoutAddupStr = object.getString("checkout _addup");
			String checkoutPromStr = object.getString("checkout_prom");

			// 解析订单信息
			JSONObject orderInfo = new JSONObject(orderInfoStr);
			Order order = new Order();
			order.setOrderId(orderInfo.getString("orderid"));
			order.setStatus(orderInfo.getString("status"));
			order.setTime(orderInfo.getString("time"));
			order.setFlag(orderInfo.getInt("flag"));
			orderDetail.setOrderInfo(order);

			// 解析地址信息
			JSONObject addressInfo = new JSONObject(addressInfoStr);
			Address address = new Address();
			address.setId(addressInfo.getInt("id"));
			address.setName(addressInfo.getString("name"));
			address.setArea(addressInfo.getString("address_area"));
			address.setAreadetail(addressInfo.getString("address_detail"));
			orderDetail.setAddressInfo(address);

			// 解析支付方式
			JSONObject paymentInfo = new JSONObject(paymentInfoStr);
			orderDetail.setPayment_info(paymentInfo.getInt("type"));

			// 解析送货时间
			JSONObject deliveryInfo = new JSONObject(deliveryInfoStr);
			orderDetail.setDelivery_info(deliveryInfo.getInt("type"));

			// 解析发票信息
			JSONObject invoiceInfo = new JSONObject(invoiceInfoStr);
			Invoice invoice = new Invoice();
			invoice.setTitle(invoiceInfo.getString("title"));
			invoice.setContent(invoiceInfo.getString("content"));
			orderDetail.setInvoiceInfo(invoice);

			 //解析其它信息
			 Map<String,String> checkoutAddupMap = new HashMap<String, String>();
			 JSONObject checkoutAddupInfo = new JSONObject(checkoutAddupStr);
			 checkoutAddupMap.put("freight", checkoutAddupInfo.getString("freight"));
			 checkoutAddupMap.put("prom_cut", checkoutAddupInfo.getString("prom_cut"));
			 checkoutAddupMap.put("total_count", checkoutAddupInfo.getString("total_count"));
			 checkoutAddupMap.put("total_point", checkoutAddupInfo.getString("total_point"));
			 checkoutAddupMap.put("total_price", checkoutAddupInfo.getString("total_price"));			 
			 orderDetail.setCheckout_addup(checkoutAddupMap);
			 
			 //解析促销信息			 
			 JSONArray checkoutPromInfo = new JSONArray(checkoutPromStr);
			 List<String> checkoutPromList = new ArrayList<String>();
			 for(int i=0;i<checkoutPromInfo.length();i++){
				 checkoutPromList.add(checkoutPromInfo.getString(i));
			 }
			 orderDetail.setCheckout_prom(checkoutPromList);
			 
			 //商品信息解析
			 List<OrderProd> prodList = new ArrayList<OrderProd>();
			 JSONArray productList = new JSONArray(productListStr);
			 for(int i=0;i<productList.length();i++){
				 OrderProd prod = new OrderProd();
				 JSONObject product = (JSONObject) productList.get(i);
				 prod.setGoodsname(product.getString("name"));
				 prod.setGoodsimag(product.getString("pic"));
				 prod.setIsgift(product.getBoolean("isgift")?"是":"否");
				 prod.setGoodsnum(product.getInt("number"));
				 prod.setPrice(product.getDouble("price"));
				 prod.setBuyLimit(product.getInt("uplimit"));
				 prodList.add(prod);
			 }
			 orderDetail.setProductInfo(prodList);
			 
			return orderDetail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
