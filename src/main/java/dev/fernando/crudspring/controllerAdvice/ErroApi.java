package dev.fernando.crudspring.controllerAdvice;

public class ErroApi {
	private final String message;

	public ErroApi(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
	
}
