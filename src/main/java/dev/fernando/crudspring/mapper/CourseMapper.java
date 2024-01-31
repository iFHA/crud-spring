package dev.fernando.crudspring.mapper;

import java.util.Objects;

import org.springframework.stereotype.Component;

import dev.fernando.crudspring.dto.CourseDTO;
import dev.fernando.crudspring.model.Course;

@Component
public class CourseMapper {
	public CourseDTO toDTO(Course course) {
		if (Objects.isNull(course)) {
			return null;
		}
		return new CourseDTO(course.getId(), course.getName(), course.getCategory());
	}
	public Course fromDTO(CourseDTO dto) {
		if (Objects.isNull(dto)) {
			return null;
		}
		var c = new Course();
		c.setId(dto._id());
		c.setName(dto.name());
		c.setCategory(dto.category());
		return c;
	}
}
