package com.fernando.crudspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fernando.crudspring.exception.RecordNotFoundException;
import com.fernando.crudspring.model.ErrorReturn;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorReturn> handleRecordNotFoundException(RecordNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorReturn(exception.getMessage()));
	}
}
