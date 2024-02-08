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
			
			courseRepository.deleteAll();
			
			for (int i = 0; i<200; i++) {
				var c1 = new Course();
				c1.setName("Curso %d".formatted(i));
				c1.setCategory(CategoryEnum.FRONT_END);
				var l = new Lesson(null, "l11111111%d".formatted(i), "%dNb4uxLxdvx".formatted(i).substring(0, 11), c1);
				var l4 = new Lesson(null, "l44444444%d".formatted(i), "%dNb4uxLxdvx".formatted(i).substring(0, 11), c1);
				c1.getLessons().add(l);
				c1.getLessons().add(l4);

				var c2 = new Course();
				c2.setName("Curso %d".formatted(i));
				c2.setCategory(CategoryEnum.BACK_END);
				var l2 = new Lesson(null, "l22222222%d".formatted(i), "Nb4u%dxLxdvxo".formatted(i).substring(0, 11), c2);
				c2.getLessons().add(l2);

				var c3 = new Course();
				c3.setName("Curso %d".formatted(i));
				c3.setCategory(CategoryEnum.FRONT_END);
				var l3 = new Lesson(null, "l33333333%d".formatted(i), "Nb4ux%dLxdvxo".formatted(i).substring(0, 11), c3);
				c3.getLessons().add(l3);

				// courseRepository.save(c1);
				courseRepository.saveAll(Arrays.asList(c1,c2,c3));
			}
		};
	}

}
