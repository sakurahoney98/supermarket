package com.sakura.supermarket.controller;

import java.util.List;

import com.sakura.supermarket.dao.ProductDAO;
import com.sakura.supermarket.model.ProductModel;
import com.sakura.supermarket.service.ProductService;

public class ProductController {

	ProductDAO productDAO = new ProductService();
	
	public void deleteProduct(int id) {
		
		productDAO.deleteProduct(id);
	}

	// editar um item
	public boolean editProduct(ProductModel old_object, String name_product, String category, String quantity,
			String duration, String stock, String max_purchase, String purchase_date, boolean confirm) {

		return productDAO.editProduct(old_object, name_product, category, quantity, duration, stock, max_purchase,
				purchase_date, confirm);

	}

	// capturar um item
	public ProductModel getProduct(String id_product) {

		return productDAO.getProduct(id_product);

	}

	// inserir um item
	public boolean insertProduct(String name_product, String category, String quantity, String duration, String stock,
			String max_purchase, String purchase_date, boolean confirm) {

		return productDAO.insertProduct(name_product, category, quantity, duration, stock, max_purchase, purchase_date,
				confirm);
	}

	// listando todos os itens
	public List<ProductModel> listOfProducts(int type) {

		return productDAO.listOfProducts(type);
	}

	// tabela de itens
	public Object[][] tableOfProducts(List<ProductModel> list) {

		return productDAO.tableOfProducts(list);
	}

	// mensagem de erro
	public String getMessageError() {

		return productDAO.getMessageError();
	}

}
