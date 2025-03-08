package com.sakura.supermarket.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sakura.supermarket.dao.ProductDAO;
import com.sakura.supermarket.enums.ProductEnum;
import com.sakura.supermarket.model.ProductModel;
import com.sakura.supermarket.persistence.ProductPersistence;

public class ProductService implements ProductDAO {

	private ProductPersistence productPersistence = new ProductPersistence();
	private String message_error = "";
	
	@Override
	public void deleteProduct(int id) {
		
		productPersistence.deleteProduct(id);
	}

	@Override
	public boolean editProduct(ProductModel old_object, String name_product, String category, String quantity,
			String duration, String stock, String max_purchase, String purchase_date, boolean confirm) {
		
		//verificando se os campos obrigatórios foram preenchidos corretamente
		if (dataValidation(name_product, quantity, duration, stock, max_purchase, purchase_date)) {
			
			//capturando o id da categoria selecionada
			category = category.substring(0, (category.indexOf("-") - 1));

			//verificando se houve alteração no nome do item
			if (!old_object.getName().equals(name_product)) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.NAME.getNome(), name_product);
			}
			
			//verificando se houve alteração na categoria do item
			if (old_object.getId_category() != Integer.valueOf(category)) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.CATEGORY.getNome(),
						Integer.valueOf(category));
			}

			//verificando se houve alteração na quantidade do item
			if (!old_object.getQuantity().equals(quantity)) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.QUANTITY.getNome(),
						quantity);
			}
			
			//verificando se houve alteração na duração do item
			if (old_object.getDuration() != Integer.valueOf(duration)) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.DURATION.getNome(),
						Integer.valueOf(duration));
			}
			
			//verificando se houve alteração na quantiodade em estoque do item
			if (old_object.getStock_quantity() != Integer.valueOf(stock)) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.STOCK.getNome(), Integer.valueOf(stock));
			}
			
			//verificando se houve alteração no valor máximo do item
			if (old_object.getMax_purchase() != Integer.valueOf(max_purchase)) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.MAX_PURCHASE.getNome(),
						Integer.valueOf(max_purchase));
			}
			
			//verificando se houve alteração na data de compra do item
			if (!old_object.getPurchase_date().equals(purchase_date)) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.PURCHASE_DATE.getNome(), purchase_date);
			}
			
			//verificando se houve alteração na confirmação antes de colocar na lista do item
			if(old_object.isConfirm() != confirm) {
				productPersistence.editProduct(old_object.getId(), ProductEnum.CONFIRM.getNome(), String.valueOf(confirm));
			}

			return true;
		} else

			return false;

	}

	@Override
	public ProductModel getProduct(String id_product) {

		ProductModel product = new ProductModel();
		//pesquisando o item atráves do ID
		ResultSet rs = productPersistence.getProduct(Integer.parseInt(id_product));

		try {
			//verificando se a consulta teve resultado
			if(rs.next())
			//montando um objeto com as informações retornadas
			product = mountObject(rs);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return product;

	}

	@Override
	public boolean insertProduct(String name_product, String category, String quantity, String duration, String stock,
			String max_purchase, String purchase_date, boolean confirm) {
		//verificando se todos os campos obrgatórios foram preenchidos corretamente
		if (dataValidation(name_product, quantity, duration, stock, max_purchase, purchase_date)) {
			
			//capturando o id da categoria selecionada
			category = category.substring(0, (category.indexOf("-") - 1));

			//inserindo no banco
			productPersistence.insertProduct(generateID(), name_product, Integer.parseInt(category),
					quantity, Integer.valueOf(duration), Integer.valueOf(stock),
					Integer.valueOf(max_purchase), purchase_date, confirm);

			return true;
		} else

			return false;
	}

	@Override
	public List<ProductModel> listOfProducts(int type) {
		
		List<ProductModel> list = new ArrayList<ProductModel>();
		ResultSet rs = productPersistence.listOfProducts(type);

		try {
			while (rs.next()) {
				list.add(mountObject(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}
	
	@Override
	public Object[][] tableOfProducts(List<ProductModel> list) {
		Object[][] table = new Object[list.size()][2];
		
		for(int i = 0; i < list.size(); i++) {
			table[i][0] = list.get(i).getId();
			table[i][1] = list.get(i).getName();
		}

		return table;
	}

	@Override
	public String getMessageError() {

		return this.message_error;
	}

	@SuppressWarnings("deprecation")
	private boolean dataValidation(String name_product, String quantity, String duration, String stock,
			String max_purchase, String purchase_date) {
		
		this.message_error = "";

		if (name_product.replaceAll(" ", "").equals("") || quantity.equals("") || duration.equals("")
				|| stock.equals("") || max_purchase.equals("") || purchase_date.equals("")) {

			this.message_error += "Campos obrigatórios não podem ficar em branco. ";
		}

		// verificando se os dados informados são um horário válido
		try {
			Date.parse(purchase_date);

		} catch (Exception e) {
			this.message_error += "A data informada não é valida. ";
		}
		if (this.message_error.equals(""))
			return true;
		else
			return false;
	}

	private int generateID() {
		ResultSet rs = productPersistence.generateID();

		try {
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return 1;

	}

	private ProductModel mountObject(ResultSet rs) {

		ProductModel product = new ProductModel();

		try {
			//formatando a data
			// a data armazenda no banco é "yyyy-MM-dd", a apresentada ao usuário é "dd/MM/yyyy"
			 SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
		     SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		     Date date = null;
			try {
				date = input.parse(rs.getString(6));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			product.setId(rs.getInt(1));
			product.setName(rs.getString(2));
			product.setQuantity(rs.getString(3));
			product.setDuration(rs.getInt(4));
			product.setStock_quantity(rs.getInt(5));
			product.setPurchase_date(output.format(date));
			product.setId_category(rs.getInt(7));
			product.setDescription_category(rs.getString(8));
			product.setMax_purchase(rs.getInt(9));
			product.setConfirm(rs.getBoolean(10));
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return product;
	}

}
