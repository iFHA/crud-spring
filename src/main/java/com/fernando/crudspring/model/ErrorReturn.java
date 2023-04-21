package com.fernando.crudspring.model;

import lombok.Data;

@Data
public class ErrorReturn {
	private String erro;
	public ErrorReturn(String erro) {
		this.erro = erro;
	}
}
