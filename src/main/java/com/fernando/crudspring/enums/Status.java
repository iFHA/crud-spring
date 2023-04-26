package com.fernando.crudspring.enums;

public enum Status {
	ATIVO("ativo"), INATIVO("inativo");
	private String value; 
	private Status(String value) {
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
