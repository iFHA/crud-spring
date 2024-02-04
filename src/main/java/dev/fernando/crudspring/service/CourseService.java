package dev.fernando.crudspring.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import dev.fernando.crudspring.dto.CourseDTO;
import dev.fernando.crudspring.exception.RecordNotFoundException;
import dev.fernando.crudspring.mapper.CourseMapper;
import dev.fernando.crudspring.model.Course;
import dev.fernando.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Service
@Validated
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper mapper;
	
	public CourseService (final CourseRepository courseRepository, final CourseMapper mapper) {
		this.courseRepository =courseRepository;
		this.mapper = mapper;
	}

	public List<CourseDTO> getCourses() {
		var courses = this.courseRepository.findAll();
		return courses
			.stream()
			.map(mapper::toDTO)
			.toList();
	}

	public CourseDTO newCourse(@Valid @NotNull CourseDTO course) {
		var c = mapper.fromDTO(course);
		c.setId(null);
		return mapper.toDTO(this.courseRepository.save(c));
	}

	public void updateCourse(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
		var dbCourse = this.getCourseOrFail(id);
		var parsedCourse = mapper.fromDTO(courseDTO);
		dbCourse.setName(parsedCourse.getName());
		dbCourse.setCategory(parsedCourse.getCategory());
		dbCourse.getLessons().clear();
		dbCourse.getLessons().addAll(parsedCourse.getLessons());
		// mergeLessonsForUpdate(parsedCourse, dbCourse);
		this.courseRepository.save(dbCourse);
	}

	private void mergeLessonsForUpdate(Course parsedCourse, Course dbCourse) {
		dbCourse.getLessons()
			.removeIf(lesson->!parsedCourse.getLessons().stream().anyMatch(l->lesson.getId().equals(l.getId())));
		parsedCourse.getLessons().forEach(l->{
			if(Objects.isNull(l.getId())) {
				dbCourse.getLessons().add(l);
			} else {
				var lesson = dbCourse.getLessons().stream().filter(les->les.getId().equals(l.getId())).findFirst().orElseThrow(()->new RuntimeException("Aula não encontrada!"));
				lesson.setName(l.getName());
				lesson.setYoutubeUrl(l.getYoutubeUrl());
			}
		});
	}

	public Course getCourseOrFail(@NotNull @Positive Long id) {
		var course = this.courseRepository.findById(id);
		if (course.isEmpty()) {
			throw new RecordNotFoundException("Curso de id = %d não encontrado".formatted(id));
		}
		return course.get();
	}
	public CourseDTO getCourseDTOOrFail(@NotNull @Positive Long id) {
		return this.mapper.toDTO(this.getCourseOrFail(id));
	}

	public void deleteCourse(@NotNull @Positive Long id) {
		this.getCourseOrFail(id);
		this.courseRepository.deleteById(id);
	}
}
