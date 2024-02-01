package dev.fernando.crudspring.mapper;

import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import dev.fernando.crudspring.dto.CourseDTO;
import dev.fernando.crudspring.enums.CategoryEnum;
import dev.fernando.crudspring.model.Course;

@Component
public class CourseMapper {
	public CourseDTO toDTO(Course course) {
		if (Objects.isNull(course)) {
			return null;
		}
		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), course.getLessons());
	}
	public Course fromDTO(CourseDTO dto) {
		if (Objects.isNull(dto)) {
			return null;
		}
		var c = new Course();
		c.setId(dto._id());
		c.setName(dto.name());
		c.setCategory(convertCategoryValue(dto.category()));
		c.setLessons(dto.lessons());
		return c;
	}
	public CategoryEnum convertCategoryValue(String category) {
		return Stream.of(CategoryEnum.values())
			.filter(categoryEnum->categoryEnum.getValue().equals(category))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}
}
