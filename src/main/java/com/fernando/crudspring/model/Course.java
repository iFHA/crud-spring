package com.fernando.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fernando.crudspring.enums.Category;
import com.fernando.crudspring.enums.Status;
import com.fernando.crudspring.enums.converters.CategoryConverter;
import com.fernando.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "update course set status = 'inativo' where id = ?")
@Where(clause = "status='ativo'")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("_id")
	private Long id;

	@NotBlank
	@NotNull
	@Length(min = 5, max = 100)
	@Column(length = 200, nullable = false)
	private String name;

	@NotNull
	@Convert(converter = CategoryConverter.class)
	@Column(length = 10, nullable = false)
	private Category category;

	@NotNull
	@Convert(converter = StatusConverter.class)
	@Column(length = 10, nullable = false)
	private Status status = Status.ATIVO;

	@OneToMany(
		mappedBy = "course",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Lesson> lessons = new ArrayList<>();

	public void addLesson(Lesson lesson) {
		getLessons().add(lesson);
		lesson.setCourse(this);
	}
	public void removeLesson(Lesson lesson) {
		getLessons().remove(lesson);
		lesson.setCourse(null);
	}
}
