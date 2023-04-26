package com.fernando.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fernando.crudspring.dto.CourseDTO;
import com.fernando.crudspring.dto.mapper.CourseMapper;
import com.fernando.crudspring.exception.RecordNotFoundException;
import com.fernando.crudspring.model.Course;
import com.fernando.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public CourseService (CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}
	
	public List<CourseDTO> list() {
		return this.courseMapper.toDTOList(this.courseRepository.findAll());
	}

	public CourseDTO findById(@NotNull @Positive Long id) {
		return	this.courseRepository.findById(id)
					.map(courseMapper::toDTO)
					.orElseThrow(() -> new RecordNotFoundException(id, "Curso"));
	}
	
	public CourseDTO store(@Valid @NotNull CourseDTO course) {
		return this.courseMapper.toDTO(this.courseRepository.save(this.courseMapper.toEntity(course)));
	}

	public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
		Course courseDb = this.courseMapper.toEntity(this.findById(id));
		courseDb.setName(course.name());
		courseDb.setCategory(this.courseMapper.categoryConverter.convertToEntityAttribute(course.category()));
		return this.courseMapper.toDTO(this.courseRepository.save(courseDb));
	}

	public void destroy(@NotNull @Positive Long id) {
		this.courseRepository.delete(this.courseMapper.toEntity(this.findById(id)));
	}
}
