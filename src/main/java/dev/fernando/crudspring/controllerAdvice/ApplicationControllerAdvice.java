package dev.fernando.crudspring.controllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import dev.fernando.crudspring.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErroApi> handleRecordNotFoundException(RecordNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroApi(ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroApi> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult()
		.getFieldErrors()
		.stream()
		.map(error-> error.getField() + " " +error.getDefaultMessage())
		.collect(Collectors.joining("\n"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroApi(errorMessage));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErroApi> handleConstraintViolationException(ConstraintViolationException ex) {
		String errorMessage = ex.getConstraintViolations()
		.stream()
		.map(error->error.getPropertyPath() + " " + error.getMessage())
		.collect(Collectors.joining(("\n")));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroApi(errorMessage));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErroApi> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		String tipo = "";
		var classe = ex.getRequiredType();
		if (Objects.nonNull(classe)) {
			tipo = classe.getSimpleName();
		}
		
		String errorMessage = ex.getName() + " deve ser do tipo " + tipo;
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroApi(errorMessage));
	}
}
