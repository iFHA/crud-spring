package dev.fernando.crudspring.controllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroApi {
	private final String message;
}
