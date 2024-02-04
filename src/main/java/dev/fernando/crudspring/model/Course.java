package dev.fernando.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import dev.fernando.crudspring.enums.CategoryEnum;
import dev.fernando.crudspring.enums.StatusEnum;
import dev.fernando.crudspring.enums.converters.CategoryConverter;
import dev.fernando.crudspring.enums.converters.StatusConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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

	@NotNull
	@Column(length = 10, nullable = false)
	@Convert(converter = CategoryConverter.class)
	private CategoryEnum category;

	@NotNull
	@Convert(converter = StatusConverter.class)
	private StatusEnum status = StatusEnum.ATIVO;

	@NotNull
	@NotEmpty
	@Valid
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lesson> lessons = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
