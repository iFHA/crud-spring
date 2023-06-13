package com.fernando.crudspring.dto;

import com.fernando.crudspring.model.Lesson;

public record LessonDTO (
	Long id,
	String name,
	String youtubeUrl
) {
	public Lesson toEntity() {
		Lesson l = new Lesson();
		l.setId(id);
		l.setName(name);
		l.setYoutubeUrl(youtubeUrl);
		return l;
	}
}
