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
import com.itheima.redbaby.bean.Cart;
import com.itheima.redbaby.bean.CartItem;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.CartShopppingEngine;
import com.itheima.redbaby.utils.Logger;

public class CartShopppingEngineImpl extends BaseEngine implements
		CartShopppingEngine {

	public CartShopppingEngineImpl() {
		super();
	}

	public Cart getCart(Context context, List<Product> productList) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			StringBuffer buffer = new StringBuffer();

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		String result = httpClient.sendPost(ConstantValue.URI
				+ ConstantValue.SHOPPINGCART, map);
		// String result = null;
		try {

			if (result != null) {
				return parseJson(result);

			}
			// else {
			// ByteArrayOutputStream os = null;
			// try {
			// InputStream is = context.getAssets().open("result.txt");
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

	private Cart parseJson(String result) {
		Cart cart = new Cart();
		ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
		try {
			JSONObject resultJson = new JSONObject(result);
			JSONObject cartJson = resultJson.getJSONObject("cart");
			JSONArray cartItemJson = cartJson.getJSONArray("cartitem");
			for (int i = 0; i < cartItemJson.length(); i++) {
				CartItem cartItem = new CartItem();

				Product pro = new Product();
				JSONObject item = (JSONObject) cartItemJson.get(i);
				JSONObject product = item.getJSONObject("product");

				pro.setId(product.getString("id"));
				pro.setName(product.getString("name"));
				pro.setPic(product.getString("pic"));
				pro.setPrice(Float.parseFloat(product.getString("price")));
				pro.setNumber(Integer.parseInt(product.getString("number")));
				pro.setUplimit(Integer.parseInt(product.getString("uplimit")));
				JSONArray jsonArray = product.getJSONArray("product_property");
				Map<String, String> property = new LinkedHashMap<String, String>();

				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject object = jsonArray.getJSONObject(j);

					property.put(object.getString("key"),
							object.getString("value"));
				}

				pro.setProduct_propertys(property);
				pro.setIsgigt(product.getBoolean("isgift"));
				cartItem.setProduct(pro);
				cartItem.setProdNum(item.getString("prodNum"));
				cartItems.add(cartItem);
			}
			cart.setCartItems(cartItems);
			JSONArray proms = cartJson.getJSONArray("prom");
			String[] promStr = new String[2];
			for (int k = 0; k < proms.length(); k++) {
				promStr[k] = proms.get(k).toString();
			}

			cart.setProm(promStr);
			cart.setTotalCount(cartJson.getInt("totalCount"));
			cart.setTotalPrice(cartJson.getInt("totalPrice"));
			cart.setTotalPoint(cartJson.getInt("totalPoint"));

			return cart;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cart getCart(Context context) {
		// TODO Auto-generated method stub
		return null;
	}
}
