package dev.fernando.crudspring.model;

import java.util.List;

import dev.fernando.crudspring.dto.CourseDTO;

public record CoursePageDTO(List<CourseDTO> courses, int page, int size, long totalElements, int totalPages) {
}
