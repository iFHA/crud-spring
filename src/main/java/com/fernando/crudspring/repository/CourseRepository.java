package com.fernando.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.crudspring.model.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{
	
}
