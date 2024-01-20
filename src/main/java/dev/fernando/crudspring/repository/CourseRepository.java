package dev.fernando.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fernando.crudspring.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
