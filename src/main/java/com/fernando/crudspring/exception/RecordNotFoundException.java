package com.fernando.crudspring.exception;

public class RecordNotFoundException extends RuntimeException {
	public RecordNotFoundException(Long id, String modelo) {
		super(modelo + " de id = " + id + " não encontrado");
	}
}
