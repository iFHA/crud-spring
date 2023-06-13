package com.fernando.crudspring.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fernando.crudspring.dto.CourseDTO;
import com.fernando.crudspring.dto.LessonDTO;
import com.fernando.crudspring.enums.converters.CategoryConverter;
import com.fernando.crudspring.model.Course;

@Component
public class CourseMapper {
	public CategoryConverter categoryConverter;
	public CourseMapper(CategoryConverter categoryConverter) {
		this.categoryConverter = categoryConverter;
	}
	public CourseDTO toDTO (Course course) {
		if (course == null) {
			return null;
		}
		List<LessonDTO> lessonsDTO = course
										.getLessons()
										.stream()
										.map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
										.collect(Collectors.toList());
		return new CourseDTO(
			course.getId(),
			course.getName(),
			course.getCategory().getValue(),
			lessonsDTO
		);
	}
	public List<CourseDTO> toDTOList (List<Course> entitiesList) {
		return entitiesList
					.stream()
					.map(this::toDTO)
					.collect(Collectors.toList());
	}
	public Course toEntity (CourseDTO courseDTO) {
		if (courseDTO == null) {
			return null;
		}
		Course course = new Course();
		if (courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}
		course.setName(courseDTO.name());
		course.setCategory(this.categoryConverter.convertToEntityAttribute(courseDTO.category()));
		course.setLessons(courseDTO.lessons().stream().map(lesson -> lesson.toEntity()).collect(Collectors.toList()));
		return course;
	}
	public List<Course> toEntityList (List<CourseDTO> DTOList) {
		return DTOList
					.stream()
					.map(this::toEntity)
					.collect(Collectors.toList());
	}

 }
