package dev.fernando.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record LessonDTO(
	Long _id,
	@NotBlank
	@Length(min = 5, max = 100)
	String name,
	@NotBlank
	@Length(min = 10, max = 11)
	String youtubeUrl
) {
}
