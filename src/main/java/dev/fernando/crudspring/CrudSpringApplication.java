package dev.fernando.crudspring;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.fernando.crudspring.enums.CategoryEnum;
import dev.fernando.crudspring.model.Course;
import dev.fernando.crudspring.model.Lesson;
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
			c1.setCategory(CategoryEnum.FRONT_END);
			var l = new Lesson(null, "l1", "Nb4uxLxdvxo", c1);
			c1.getLessons().add(l);

			var c2 = new Course();
			c2.setName("Curso 2");
			c2.setCategory(CategoryEnum.BACK_END);
			var l2 = new Lesson(null, "l2", "Nb4uxLxdvxo", c2);
			c2.getLessons().add(l2);

			var c3 = new Course();
			c3.setName("Curso 3");
			c3.setCategory(CategoryEnum.FRONT_END);
			var l3 = new Lesson(null, "l3", "Nb4uxLxdvxo", c3);
			c3.getLessons().add(l3);

			courseRepository.deleteAll();
			// courseRepository.save(c1);
			courseRepository.saveAll(Arrays.asList(c1,c2,c3));
		};
	}

}
