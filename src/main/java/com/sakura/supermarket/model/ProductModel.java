package com.sakura.supermarket.model;

public class ProductModel {

	private static final int DEFAULT_ID = 1;
	private static final String DEFAULT_NAME = "NAME";
	private static final String DEFAULT_QUANTITY = "1kg";
	private static final int DEFAULT_DURATION = 1;
	private static final int DEFAULT_STOCK_QUANTITY = 0;
	private static final String DEFAULT_PURCHASE_DATE = "01/01/1900";
	private static final int DEFAULT_ID_CATEGORY = 1;
	private static final String DEFAULT_DESCRIPTION_CATEGORY = "CATEGORY";
	private static final int DEFAULT_MAX_PURCHASE = 1;
	private static final boolean DEFAULT_CONFIRM = false;
	private static final int DEFAULT_BUY = 1;

	private int id = DEFAULT_ID;
	private String name = DEFAULT_NAME;
	private String quantity = DEFAULT_QUANTITY;
	private int duration = DEFAULT_DURATION;
	private int stock_quantity = DEFAULT_STOCK_QUANTITY;
	private String purchase_date = DEFAULT_PURCHASE_DATE;
	private int id_category = DEFAULT_ID_CATEGORY;
	private String description_category = DEFAULT_DESCRIPTION_CATEGORY;
	private int max_purchase = DEFAULT_MAX_PURCHASE;
	private boolean confirm = DEFAULT_CONFIRM;
	private int buy = DEFAULT_BUY;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public String getDescription_category() {
		return description_category;
	}

	public void setDescription_category(String description_category) {
		this.description_category = description_category;
	}

	public int getMax_purchase() {
		return max_purchase;
	}

	public void setMax_purchase(int max_purchase) {
		this.max_purchase = max_purchase;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}
	
	

}

