package dev.fernando.crudspring.exception;

public class RecordNotFoundException extends RuntimeException {
	public RecordNotFoundException(String msg) {
		super(msg);
	}
}
