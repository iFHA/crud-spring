package dev.fernando.crudspring;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.fernando.crudspring.model.Course;
import dev.fernando.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(final CourseRepository courseRepository) {
		return args -> {
			var c1 = new Course();
			c1.setName("Curso 1");
			c1.setCategory("front-end");
			var c2 = new Course();
			c2.setName("Curso 2");
			c2.setCategory("back-end");
			var c3 = new Course();
			c3.setName("Curso 3");
			c3.setCategory("front-end");

			courseRepository.deleteAll();
			courseRepository.saveAll(Arrays.asList(c1,c2,c3));
		};
	}

}
