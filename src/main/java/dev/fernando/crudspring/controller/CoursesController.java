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
import org.springframework.web.bind.annotation.RestController;

import dev.fernando.crudspring.model.Course;
import dev.fernando.crudspring.service.CourseService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CoursesController {

	private final CourseService courseService;

	@GetMapping
	public ResponseEntity<List<Course>> getCourses() {
		return ResponseEntity.ok(this.courseService.getCourses());
	}
	@PostMapping
	public ResponseEntity<Course> newCourse(@RequestBody Course course) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.newCourse(course));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable Long id) {
		return ResponseEntity.ok(this.courseService.getCourseOrFail(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
		var c = this.courseService.getCourseOrFail(id);
		c.setName(course.getName());
		c.setCategory(course.getCategory());
		return ResponseEntity.ok(c);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		this.courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
	
}
