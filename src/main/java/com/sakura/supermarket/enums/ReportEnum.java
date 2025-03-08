package com.sakura.supermarket.enums;

public enum ReportEnum {

	TABELA("report"), ID_PRODUCT("id_product"), DATE_BUY("date_buy"), AMOUNT_PREVIOUS("amount_previous"), AMOUNT_CURRENT("amount_current"),
	AMOUNT_BUY("amount_buy"), DAYS("days");

	private final String name;

	ReportEnum(String name) {
		this.name = name;

	}

	public String getNome() {
		return name;
	}

}
