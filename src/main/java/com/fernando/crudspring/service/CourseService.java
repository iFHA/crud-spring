package com.fernando.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

	public CourseService (CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> list() {
		return this.courseRepository.findAll();
	}

	public Course findById(@NotNull @Positive Long id) {
		return this.courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id, "Curso"));
	}
	
	public Course store(@Valid Course course) {
		return this.courseRepository.save(course);
	}

	public Course update(@NotNull @Positive Long id, Course course) {
		Course courseDb = this.findById(id);
		courseDb.setName(course.getName());
		courseDb.setCategory(course.getCategory());
		return this.courseRepository.save(courseDb);
	}

	public void destroy(@NotNull @Positive Long id) {
		this.courseRepository.delete(this.findById(id));
	}
}
