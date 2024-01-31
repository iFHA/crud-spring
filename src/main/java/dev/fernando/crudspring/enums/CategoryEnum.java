package dev.fernando.crudspring.enums;

public enum CategoryEnum {
	FRONT_END("front-end"), BACK_END("back-end");
	private String value;
	private CategoryEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
}
