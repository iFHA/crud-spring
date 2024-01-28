package dev.fernando.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import dev.fernando.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;




@RestController
@Validated
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
	public ResponseEntity<Course> newCourse(@Valid @RequestBody Course course) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.courseRepository.save(course));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable @NotNull @Positive Long id) {
		return this.courseRepository.findById(id)
		.map(c->ResponseEntity.ok(c))
		.orElse(ResponseEntity.notFound().build());
	}
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable @NotNull @Positive Long id, @Valid @RequestBody Course course) {
		var c = this.courseRepository.getReferenceById(id);
		c.setName(course.getName());
		c.setCategory(course.getCategory());
		return ResponseEntity.ok(c);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable @NotNull @Positive Long id) {
		if(this.courseRepository.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		this.courseRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
