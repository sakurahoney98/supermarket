package com.sakura.supermarket.enums;

public enum ProductEnum {

	TABELA("product"), ID("id_product"), NAME("name_product"), QUANTITY("kg_unity"), DURATION("duration"),
	STOCK("stock_quantity"), PURCHASE_DATE("purchase_date"), CATEGORY("category"), MAX_PURCHASE("max_purchase"), CONFIRM("confirm"), VIEW_ALL("all_products");

	private final String name;

	ProductEnum(String name) {
		this.name = name;

	}

	public String getNome() {
		return name;
	}

}
