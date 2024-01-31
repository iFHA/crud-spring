package dev.fernando.crudspring.enums.converters;

import java.util.Objects;

import dev.fernando.crudspring.enums.CategoryEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<CategoryEnum,String> {

	@Override
	public String convertToDatabaseColumn(CategoryEnum category) {
		if (Objects.isNull(category)) {
			return null;
		}
		return category.getValue();
	}

	@Override
	public CategoryEnum convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) {
			return null;
		}
		for (CategoryEnum category: CategoryEnum.values()) {
			if (category.getValue().equals(dbData)) {
				return category;
			}
		}
		throw new IllegalArgumentException("Categoria do registro no banco de dados(%s) está inválida.".formatted(dbData));
	}
	
}
