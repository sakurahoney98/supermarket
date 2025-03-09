package com.sakura.supermarket.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.sakura.supermarket.controller.ProductController;
import com.sakura.supermarket.model.ProductModel;

public class View_5_0_List {

	private static int param = 0;

	public static void main(String[] args) {
		ProductController productController = new ProductController();
		List<ProductModel> list = productController.listOfProducts(2);
		List<ProductModel> list_confirm = new ArrayList<ProductModel>();
		List<ProductModel> new_list = new ArrayList<ProductModel>();

		// Pegando a lista de todos os produtos cadastrados no sistema
		for (ProductModel p : list) {

			// Verificando se a quantidade de produtos no estoque é 0
			// Caso seja ele automaticamente inclui na lista de compra ou na confirmação de
			// compra
			if (p.getStock_quantity() > 0) {
				int buy = 0;

				// Verificando se a duração do produto é maior que 29 dias
				if (p.getDuration() < 30) {

					// Verificando se a lista deve ser feita baseada em:
					// 0 - Quantidade no estoque
					// 1 - Cálculo de quantidade necessária por mês
					if (param == 0) {
						
						if((p.getStock_quantity() * p.getDuration()) < 35) {
							buy = p.getMax_purchase() - p.getStock_quantity();
						}
						
					} else {
						buy = (int) Math.ceil(30 / (double) p.getDuration()) - p.getStock_quantity();
					}

					// Caso a quantidade recomendada de compra seja maior que 0 o produto é
					// adicionado na lista de compra ou confirmação de compra
					if (buy > 0) {
						p.setBuy(buy);
						if (!p.isConfirm()) {
							new_list.add(p);
						} else {
							list_confirm.add(p);
						}
					}

				} 
				//Para produtos que a duração é maior que 29 dias é necessário verificar se a quantidade de duração já foi atingida
				else {

					try {
						if(param == 0) {
							if(p.getMax_purchase() - p.getStock_quantity() > 0) {
								p.setBuy(p.getMax_purchase() - p.getStock_quantity());
								if (!p.isConfirm()) {
									new_list.add(p);
								} else {
									list_confirm.add(p);
								}
							}
						}else {
						DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						
						LocalDate date = LocalDate.parse(p.getPurchase_date(), format);

						LocalDate today =  LocalDate.now();
						
						
						//Verificando quantos dias se passaram desde a data da última compra
						

						long days = ChronoUnit.DAYS.between(date, today);

						//Verificando se a quantidade de dias passados já é o suficiente para comprar um novo produto
						//Caso seja necessário comprar um novo produto adicionar na lista de compra ou de confirmação de compra
						if (days >= (p.getDuration() * p.getStock_quantity())) {
							p.setBuy(p.getMax_purchase() - p.getStock_quantity());
							
							if (!p.isConfirm()) {
								new_list.add(p);
							} else {
								list_confirm.add(p);
							}
						}
						}

					} catch (Exception e) {

						e.printStackTrace();
					}

				}
			} else {
				int buy = 0;
				if (p.getDuration() < 30) {
					// Verificando se a lista deve ser feita baseada em:
					// 0 - Quantidade no estoque
					// 1 - Cálculo de quantidade necessária por mês
					if (param == 0) {
						buy = p.getMax_purchase() - p.getStock_quantity();
					} else {
						buy = (int) Math.ceil(30 / (double) p.getDuration()) - p.getStock_quantity();
					}
					p.setBuy(buy);
				}
				if (!p.isConfirm()) {
					new_list.add(p);
				} else {
					list_confirm.add(p);
				}
			}

		}

		View_5_1_List.setList(list_confirm);
		View_5_2_List.setListProducts(new_list);
		if (!list_confirm.isEmpty())
			new View_5_1_List().setVisible(true);
		else
			View_5_2_List.main(args);
	}

}
