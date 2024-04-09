package com.sakura.supermarket.model;

public class CategoryModel {
	
	private static final int ID_CATEGORY = 1;
	private static final String DESCRIPTION = "DESCRIPTION";
	
	
	private int id = ID_CATEGORY;
	private String description = DESCRIPTION;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
