package com.sakura.supermarket.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sakura.supermarket.dao.CategoryDAO;
import com.sakura.supermarket.persistence.CategoryPersistence;

public class CategoryService implements CategoryDAO {

	CategoryPersistence categoryPersistence = new CategoryPersistence();

	// formantando a lista de categoria para o jComboBox
	public String[] allCategories() {

		// pegando as lista do banco
		ResultSet rs = categoryPersistence.allCategories();

		// criando um array para ajudar na organização
		List<String> list_aux = new ArrayList<String>();

		try {
			// verificando se a consulta retornou resultado e se possui próximo item
			while (rs.next()) {
				// concatenando o id da categoria com a descrição e armazenando no array
				list_aux.add(rs.getInt(1) + " - " + rs.getString(2));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// verificando se o array tem item
		if (list_aux.size() > 0) {
			// criando um vetor para passar o jComboBox
			String[] list = new String[list_aux.size()];

			// passando os valores do array para o vetor
			for (int i = 0; i < list_aux.size(); i++) {
				list[i] = list_aux.get(i);
			}

			return list;
		}
		return null;
	}

}
