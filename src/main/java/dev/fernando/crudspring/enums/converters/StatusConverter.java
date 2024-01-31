package dev.fernando.crudspring.enums.converters;

import java.util.Objects;

import dev.fernando.crudspring.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<StatusEnum,String> {

	@Override
	public String convertToDatabaseColumn(StatusEnum status) {
		if (Objects.isNull(status)) {
			return null;
		}
		return status.getValue();
	}

	@Override
	public StatusEnum convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) {
			return null;
		}
		for (StatusEnum Status: StatusEnum.values()) {
			if (Status.getValue().equals(dbData)) {
				return Status;
			}
		}
		throw new IllegalArgumentException("Status do registro no banco de dados(%s) está inválido.".formatted(dbData));
	}
	
}
