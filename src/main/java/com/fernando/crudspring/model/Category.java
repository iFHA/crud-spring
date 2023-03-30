package com.fernando.crudspring.model;

public enum Category {
	BACKEND("Back-End"), FRONTEND("Front-End");
	private String category; 
	private Category(String category) {
		this.category = category;
	}
}
