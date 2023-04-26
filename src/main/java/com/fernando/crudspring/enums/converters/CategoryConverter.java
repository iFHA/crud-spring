package com.fernando.crudspring.enums.converters;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.fernando.crudspring.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
@Component
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
	public String convertToDatabaseColumn(Category category) {
		if (category == null) {
			return null;
		}
		return category.getValue();
	}

	@Override
	public Category convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return Stream.of(Category.values())
					.filter(val->val.getValue().equals(value))
					.findFirst()
					.orElseThrow(IllegalArgumentException::new);
	}
	
}
