package dev.fernando.crudspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
	Long _id,
	
	@NotBlank
	@Length(min = 5, max = 100)
	String name,

	@NotBlank
	@Length(max = 10)
	@Pattern(regexp = "back-end|front-end")
	String category,

	List<LessonDTO> lessons
) {
}
