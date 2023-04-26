package com.fernando.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fernando.crudspring.enums.Category;
import com.fernando.crudspring.model.Course;
import com.fernando.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			Course c = new Course();
			c.setName("Angular");
			c.setCategory(Category.FRONTEND);
			courseRepository.deleteAll();
			courseRepository.save(c);
		};
	}

}
