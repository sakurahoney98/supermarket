package com.sakura.supermarket.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sakura.supermarket.SupermarketApplication;
import com.sakura.supermarket.enums.CategoryEnum;

public class CategoryPersistence {

	Connection connection = SupermarketApplication.getConnection();

	// capturando todas as categorias do banco
	public ResultSet allCategories() {

		Statement st;
		ResultSet rs = null;

		try {
			st = connection.createStatement();

			String sql = " SELECT * FROM " + CategoryEnum.TABELA.getNome();

			rs = st.executeQuery(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;

	}

}
