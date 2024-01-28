package dev.fernando.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
@SQLRestriction("status = 'ativo'")
@SQLDelete(sql = "update courses set status = 'inativo' where id = ?")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("_id")
	private Long id;

	@NotBlank
	@Length(min = 5, max = 100)
	@Column(length = 100, nullable = false)
	private String name;

	@NotBlank
	@Length(max = 10)
	@Pattern(regexp = "back-end|front-end")
	@Column(length = 10, nullable = false)
	private String category;

	@NotBlank
	@Pattern(regexp = "ativo|inativo")
	@Column(length = 10, nullable = false)
	private String status = "ativo";
}
