package com.sakura.supermarket.enums;

public enum CategoryEnum {
	
	TABELA("category"), ID("id_category"), DESCRIPTION("category_description");

	private final String name;

	CategoryEnum(String name) {
		this.name = name;

	}

	public String getNome() {
		return name;
	}
	

}
