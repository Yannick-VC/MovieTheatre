package com.example.demo;

import com.example.demo.domain.UserRepository;
import com.example.demo.domain.MovieDAOImp;
import com.example.demo.domain.Movie;
import com.example.demo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalExApplication {

	private static final Logger log = LoggerFactory.getLogger(FinalExApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FinalExApplication.class, args);
	}

	@Bean
	public CommandLineRunner MovieDemo(MovieDAOImp moviedao, UserRepository userRepository) {
		return (args) -> {

			moviedao.save(new Movie("Ready Player One", 120, 10 ));
			moviedao.save(new Movie("Fast and the Furious", 160, 9 ));
			moviedao.save(new Movie("The Conjuring", 130, 8 ));
			moviedao.save(new Movie("Rien a déclarer", 140, 7));
			moviedao.save(new Movie("The Da Vinci Code ", 130,9));
			moviedao.save(new Movie("Journey 2", 150, 9));
			moviedao.save(new Movie("Interstellar", 180, 9));
			moviedao.save(new Movie("Inception", 150, 6));

			for (Movie movie : moviedao.findAll()) {
				log.info(movie.toString());
			}

			userRepository.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user.user@user.com"));
			userRepository.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin.admin@admin.com"));
			userRepository.save(new User("Yannick", "$2y$12$8fmxDCmPO1C.sJm/eRGlEO8wEDWEpjoCSSwmmqrdWNmowu/v48ZBG", "USER", "Yannick.vancampenhout@telenet.be"));
		};
	}


}
