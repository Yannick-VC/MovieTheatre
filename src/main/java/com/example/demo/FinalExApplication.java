package com.example.demo;

import com.example.demo.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FinalExApplication {
	private static final Logger log = LoggerFactory.getLogger(MovieRepository.class);

	public static void main(String[] args) {
		SpringApplication.run(FinalExApplication.class, args);
	}

	@Bean
	public CommandLineRunner MovieDemo(MovieRepository MRepo, GenreRepository GRepo, UserRepository URepo) {
		return (args) -> {

			GRepo.save(new Genre("Action"));
			GRepo.save(new Genre("Adventure"));
			GRepo.save(new Genre("Comedie"));
			GRepo.save(new Genre("Horror"));
			GRepo.save(new Genre("Sci-fi"));
			GRepo.save(new Genre("Thriller"));

			for (Genre genre : GRepo.findAll()) {
				log.info(genre.toString());
			}

			MRepo.save(new Movie("Ready Player One", 120, 10, GRepo.findBygenre("Action").get(0)));
			MRepo.save(new Movie("Fast and the Furious", 160, 9, GRepo.findBygenre("Action").get(0)));
			MRepo.save(new Movie("The Conjuring", 130, 8, GRepo.findBygenre("Horror").get(0)));
			MRepo.save(new Movie("Rien a déclarer", 140, 7, GRepo.findBygenre("Comedie").get(0)));
			MRepo.save(new Movie("The Da Vinci Code ", 130, 7, GRepo.findBygenre("Thriller").get(0)));
			MRepo.save(new Movie("Journey 2", 150, 9, GRepo.findBygenre("Adventure").get(0)));
			MRepo.save(new Movie("Interstellar", 180, 9, GRepo.findBygenre("Sci-fi").get(0)));
			MRepo.save(new Movie("Inception", 150, 6, GRepo.findBygenre("Sci-fi").get(0)));

			for (Movie movie : MRepo.findAll()) {
				log.info(movie.toString());
			}

			URepo.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user.user@user.com"));
			URepo.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin.admin@admin.com"));
			URepo.save(new User("Yannick", "$2y$12$8fmxDCmPO1C.sJm/eRGlEO8wEDWEpjoCSSwmmqrdWNmowu/v48ZBG", "USER", "Yannick.vancampenhout@telenet.be"));
			for (User user : URepo.findAll()) {
				log.info(user.toString());
			}
		};
	}


}
