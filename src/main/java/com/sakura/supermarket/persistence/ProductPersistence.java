package com.sakura.supermarket.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sakura.supermarket.SupermarketApplication;
import com.sakura.supermarket.enums.ProductEnum;

public class ProductPersistence {

	Connection connection = SupermarketApplication.getConnection();

	//deletar objeto do banco
	public void deleteProduct(int id) {

		Statement st;

		try {
			st = connection.createStatement();

			String sql = "DELETE FROM " + ProductEnum.TABELA.getNome() + " WHERE " + ProductEnum.ID.getNome() + " = "
					+ id;

			st.executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// atualizando um objeto do banco de dados cujo a tupla é do tipo charactery
	// varying
	// o nome da tupla a ser atualizada e o valor são passados no momento da
	// execução
	public void editProduct(int id_product, String column, String value) {

		Statement st;

		try {
			st = connection.createStatement();

			String sql = " UPDATE " + ProductEnum.TABELA.getNome() + " SET " + column + " = '" + value + "' WHERE "
					+ ProductEnum.ID.getNome() + " = " + id_product;

			st.executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// atualizando um objeto do banco de dados cujo a tupla é do tipo integer
	// o nome da tupla a ser atualizada e o valor são passados no momento da
	// execução
	public void editProduct(int id_product, String column, int value) {

		Statement st;

		try {
			st = connection.createStatement();

			String sql = " UPDATE " + ProductEnum.TABELA.getNome() + " SET " + column + " = " + value + " WHERE "
					+ ProductEnum.ID.getNome() + " = " + id_product;

			st.executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// gerando id para um objeto
	public ResultSet generateID() {
		Statement st;
		ResultSet rs = null;

		try {
			st = connection.createStatement();

			// Capturando o último ID cadastrado
			String sql = " SELECT MAX(" + ProductEnum.ID.getNome() + ") FROM " + ProductEnum.TABELA.getNome();

			rs = st.executeQuery(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;

	}

	// capturando um objeto do banco através do ID
	public ResultSet getProduct(int id_product) {

		Statement st;
		ResultSet rs = null;

		try {
			st = connection.createStatement();

			String sql = " SELECT * FROM " + ProductEnum.VIEW_ALL.getNome() + " WHERE " + ProductEnum.ID.getNome()
					+ " = " + id_product;

			rs = st.executeQuery(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;

	}

	// inserindo objeto no banco de dados
	public void insertProduct(int id_product, String name_product, int category, String quantity, int duration,
			int stock, int max_purchase, String purchase_date, boolean confirm) {

		// listando os campos para inserir valores
		String campos = ProductEnum.ID.getNome() + ", " + ProductEnum.NAME.getNome() + ", "
				+ ProductEnum.QUANTITY.getNome() + ", " + ProductEnum.DURATION.getNome() + ", "
				+ ProductEnum.STOCK.getNome() + ", " + ProductEnum.PURCHASE_DATE.getNome() + ", "
				+ ProductEnum.CATEGORY.getNome() + ", " + ProductEnum.MAX_PURCHASE.getNome() + ", "
				+ ProductEnum.CONFIRM.getNome();

		Statement st;

		try {
			st = connection.createStatement();

			String sql = " INSERT INTO " + ProductEnum.TABELA.getNome() + " (" + campos + ") VALUES (" + id_product
					+ ", '" + name_product + "', '" + quantity + "', " + duration + ", " + stock + ", '" + purchase_date
					+ "', " + category + " , " + max_purchase + ", " + confirm + ")";

			st.executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// capturando todos os objetos da tabela "product"
	// type = 1 -- > ordenar pelo nome
	// type = 2 -- > ordenar por categoria e nome
	public ResultSet listOfProducts(int type) {

		Statement st;
		ResultSet rs = null;

		try {
			st = connection.createStatement();

			String sql = " SELECT * FROM " + ProductEnum.VIEW_ALL.getNome();
			// consulta feita pela tela de visualização de produtos
			if (type == 1)
				sql += " ORDER BY " + ProductEnum.NAME.getNome();
			else
				// consulta feita pela tela de geração de lista de compras
				sql += " ORDER BY " + ProductEnum.CATEGORY.getNome() + " , " + ProductEnum.NAME.getNome();

			rs = st.executeQuery(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;

	}

}
