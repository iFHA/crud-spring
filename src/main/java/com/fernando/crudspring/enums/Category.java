package com.fernando.crudspring.enums;

public enum Category {
	BACKEND("back-end"), FRONTEND("front-end");
	private String value; 
	private Category(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
