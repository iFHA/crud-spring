package dev.fernando.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.fernando.crudspring.dto.CourseDTO;
import dev.fernando.crudspring.model.CoursePageDTO;
import dev.fernando.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CoursesController {

	private final CourseService courseService;

	public CoursesController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping
	public ResponseEntity<CoursePageDTO> getCourses(@RequestParam(defaultValue = "0") @PositiveOrZero int pageNumber, @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
		return ResponseEntity.ok(this.courseService.getCourses(pageNumber, pageSize));
	}
	@PostMapping
	public ResponseEntity<CourseDTO> newCourse(@RequestBody @Valid CourseDTO course) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.newCourse(course));
	}
	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
		return ResponseEntity.ok(this.courseService.getCourseDTOOrFail(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseDTO courseDTO) {
		this.courseService.updateCourse(id, courseDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		this.courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
	
}
