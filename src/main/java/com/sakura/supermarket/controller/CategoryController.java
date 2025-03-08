package com.sakura.supermarket.controller;

import com.sakura.supermarket.dao.CategoryDAO;
import com.sakura.supermarket.service.CategoryService;

public class CategoryController {

	CategoryDAO categoryDAO = new CategoryService();

	// lista com as categorias
	public String[] allCategories() {

		return categoryDAO.allCategories();
	}

}
