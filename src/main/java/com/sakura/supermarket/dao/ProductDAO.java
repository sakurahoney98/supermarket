package com.sakura.supermarket.dao;

import java.util.List;

import com.sakura.supermarket.model.ProductModel;

public interface ProductDAO {

	void deleteProduct(int id);
	
	boolean editProduct(ProductModel old_object, String name_product, String category, String quantity, String duration,
			String stock, String max_purchase, String purchase_date, boolean confirm);

	ProductModel getProduct(String id_product);

	boolean insertProduct(String name_product, String category, String quantity, String duration,
			String stock, String max_purchase, String purchase_date, boolean confirm);

	List<ProductModel> listOfProducts(int type);
	
	Object[][] tableOfProducts(List<ProductModel> list);
	
	String getMessageError();

}
