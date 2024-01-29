package dev.fernando.crudspring.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.fernando.crudspring.exception.RecordNotFoundException;

@RestControllerAdvice
public class CourseControllerAdvice {
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErroApi> handleRecordNotFoundException(RecordNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroApi(ex.getMessage()));
	}
}
