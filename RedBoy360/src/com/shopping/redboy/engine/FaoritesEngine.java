package com.shopping.redboy.engine;

import java.util.List;

import com.shopping.redboy.domain.Product;

public abstract interface FaoritesEngine {

	public abstract List<Product> getServiceProducts(int offset, int maxnumber);
}
