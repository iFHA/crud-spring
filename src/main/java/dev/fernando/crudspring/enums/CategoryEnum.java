package dev.fernando.crudspring.enums;

import dev.fernando.crudspring.enums.validation.EnumString;

public enum CategoryEnum implements EnumString {
	FRONT_END("front-end"), BACK_END("back-end");
	private String value;
	private CategoryEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
}
