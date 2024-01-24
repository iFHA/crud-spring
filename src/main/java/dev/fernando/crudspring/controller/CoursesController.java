package dev.fernando.crudspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fernando.crudspring.model.Course;
import dev.fernando.crudspring.repository.CourseRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CoursesController {

	private final CourseRepository courseRepository;

	@GetMapping
	public ResponseEntity<List<Course>> getCourses() {
		return ResponseEntity.ok(this.courseRepository.findAll());
	}
	@PostMapping
	public ResponseEntity<Course> newCourse(@RequestBody Course course) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.courseRepository.save(course));
	}
	
}
