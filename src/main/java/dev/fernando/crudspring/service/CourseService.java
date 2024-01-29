package dev.fernando.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import dev.fernando.crudspring.exception.RecordNotFoundException;
import dev.fernando.crudspring.model.Course;
import dev.fernando.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseService {
	private final CourseRepository courseRepository;

	public CourseService(final CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getCourses() {
		return this.courseRepository.findAll();
	}

	public Course newCourse(@Valid Course course) {
		return this.courseRepository.save(course);
	}

	public Course updateCourse(@NotNull @Positive Long id, @Valid Course course) {
		var c = this.getCourseOrFail(id);
		c.setName(course.getName());
		c.setCategory(course.getCategory());
		this.courseRepository.save(c);
		return c;
	
	}

	public Course getCourseOrFail(@NotNull @Positive Long id) {
		var course = this.courseRepository.findById(id);
		if (course.isEmpty()) {
			throw new RecordNotFoundException("Curso de id = %d não encontrado".formatted(id));
		}
		return course.get();
	}

	public void deleteCourse(@NotNull @Positive Long id) {
		this.getCourseOrFail(id);
		this.courseRepository.deleteById(id);
	}
}
