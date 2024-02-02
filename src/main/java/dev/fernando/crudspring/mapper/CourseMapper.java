package dev.fernando.crudspring.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import dev.fernando.crudspring.dto.CourseDTO;
import dev.fernando.crudspring.dto.LessonDTO;
import dev.fernando.crudspring.enums.CategoryEnum;
import dev.fernando.crudspring.model.Course;
import dev.fernando.crudspring.model.Lesson;

@Component
public class CourseMapper {
	public CourseDTO toDTO(Course course) {
		if (Objects.isNull(course)) {
			return null;
		}
		var lessons = course.getLessons()
						.stream()
						.map(l->new LessonDTO(l.getId(), l.getName(), l.getYoutubeUrl()))
						.toList();
		return new CourseDTO(
			course.getId(), 
			course.getName(), 
			course.getCategory().getValue(), 
			lessons
		);
	}
	public Course fromDTO(CourseDTO dto) {
		if (Objects.isNull(dto)) {
			return null;
		}
		var c = new Course();
		c.setId(dto._id());
		c.setName(dto.name());
		c.setCategory(convertCategoryValue(dto.category()));
		List<Lesson> lessons = dto.lessons()
						.stream()
						.map(l->new Lesson(l._id(), l.name(), l.youtubeUrl(), c))
						.toList();
		c.setLessons(lessons);
		return c;
	}
	public CategoryEnum convertCategoryValue(String category) {
		return Stream.of(CategoryEnum.values())
			.filter(categoryEnum->categoryEnum.getValue().equals(category))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}
}
