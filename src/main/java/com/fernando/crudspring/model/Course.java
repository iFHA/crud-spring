package com.fernando.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fernando.crudspring.enums.Category;
import com.fernando.crudspring.enums.converters.CategoryConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "update course set status = 'Inativo' where id = ?")
@Where(clause = "status='Ativo'")
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
	@Pattern(regexp = "Ativo|Inativo")
	@Length(max = 10)
	@Column(length = 10, nullable = false)
	private String status = "Ativo";
}
