package com.fernando.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fernando.crudspring.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO (
	@JsonProperty("_id") Long id,
	@NotBlank @NotNull @Length(min = 5, max = 100) String name,
	@NotBlank @NotNull @Length(max = 10) Category category
	) {
}

