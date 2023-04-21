package com.fernando.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.crudspring.model.Course;
import com.fernando.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping
	public List<Course> list() {
		return this.courseService.list();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Course store(@RequestBody @Valid Course course) {
		return this.courseService.store(course);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) {
		return this.courseService.findById(id)
								.map(course -> ResponseEntity.ok().body(course))
								.orElse(ResponseEntity.notFound().build());
	}
	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable @Positive Long id, @RequestBody @Valid Course course) {
		return this.courseService.update(id,course)
									.map(recordFound -> {
										return ResponseEntity.ok().body(recordFound);
									})
									.orElse(ResponseEntity.notFound().build());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> destroy(@PathVariable @Positive Long id) {
		return this.courseService.destroy(id) 
									? ResponseEntity.noContent().<Void>build()
									: ResponseEntity.notFound().build();
	}
}
