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

import com.fernando.crudspring.dto.CourseDTO;
import com.fernando.crudspring.model.Lesson;
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
	public List<CourseDTO> list() {
		return this.courseService.list();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CourseDTO store(@RequestBody @Valid @NotNull CourseDTO courseDTO) {
		return this.courseService.store(courseDTO);
	}

	@GetMapping("/{id}")
	public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return this.courseService.findById(id);
	}
	@PutMapping("/{id}")
	public CourseDTO update(@PathVariable @Positive Long id, @RequestBody @Valid @NotNull CourseDTO course) {
		return this.courseService.update(id,course);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void destroy(@PathVariable @Positive Long id) {
		this.courseService.destroy(id);
	}
	@GetMapping("/{id}/lessons")
	public ResponseEntity<List<Lesson>> getLessons(@PathVariable Long id) {
		return ResponseEntity.ok(courseService.getAllPostLessons(id));
	}
}
