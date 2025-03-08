package com.sakura.supermarket.model;

import java.util.Comparator;

public class ProductModelComparator implements Comparator<ProductModel> {

	@Override
	public int compare(ProductModel p1, ProductModel p2) {
		
		return p1.getName().compareTo(p2.getName());
	}
}
