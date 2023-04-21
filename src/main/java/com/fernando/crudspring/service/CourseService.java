package com.fernando.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

	public Optional<Course> findById(@NotNull @Positive Long id) {
		return this.courseRepository.findById(id);
	}
	
	public Course store(@Valid Course course) {
		return this.courseRepository.save(course);
	}

	public Optional<Course> update(@NotNull @Positive Long id, Course course) {
		return this.findById(id)
						.map(courseDb -> {
							courseDb.setName(course.getName());
							courseDb.setCategory(course.getCategory());
							return this.courseRepository.save(courseDb);
						});
	}

	public boolean destroy(@NotNull @Positive Long id) {
		return this.courseRepository.findById(id)
								.map(course -> {
									this.courseRepository.delete(course);
									return true;
								}).orElse(false);
	}
}
