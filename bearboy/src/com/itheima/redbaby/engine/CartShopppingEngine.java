package com.itheima.redbaby.engine;

import java.util.List;

import android.content.Context;

import com.itheima.redbaby.bean.Cart;
import com.itheima.redbaby.bean.Product;

public interface CartShopppingEngine {
	Cart getCart(Context context);
	Cart getCart(Context context, List<Product> shoppingList);
}
