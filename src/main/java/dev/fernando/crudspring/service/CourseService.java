package dev.fernando.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import dev.fernando.crudspring.dto.CourseDTO;
import dev.fernando.crudspring.exception.RecordNotFoundException;
import dev.fernando.crudspring.mapper.CourseMapper;
import dev.fernando.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	private final CourseMapper mapper;

	public List<CourseDTO> getCourses() {
		var courses = this.courseRepository.findAll();
		return courses
			.stream()
			.map(mapper::toDTO)
			.toList();
	}

	public CourseDTO newCourse(@Valid @NotNull CourseDTO course) {
		var c = mapper.fromDTO(course);
		c.setStatus("ativo");
		return mapper.toDTO(this.courseRepository.save(c));
	}

	public CourseDTO updateCourse(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
		var dto = this.getCourseOrFail(id);
		var c = mapper.fromDTO(dto);
		c.setName(course.name());
		c.setCategory(course.category());
		this.courseRepository.save(c);
		return dto;
	
	}

	public CourseDTO getCourseOrFail(@NotNull @Positive Long id) {
		var course = this.courseRepository.findById(id);
		if (course.isEmpty()) {
			throw new RecordNotFoundException("Curso de id = %d não encontrado".formatted(id));
		}
		return mapper.toDTO(course.get());
	}

	public void deleteCourse(@NotNull @Positive Long id) {
		this.getCourseOrFail(id);
		this.courseRepository.deleteById(id);
	}
}
