package com.example.demo;

import com.example.demo.controller.MovieController;
import com.example.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FinalExApplicationTests {

	@Autowired
	private MovieController movieController;

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() throws Exception {
		assertThat(movieController).isNotNull();
	}

	@Test
	void contextTest() throws Exception {
		assertThat(userController).isNotNull();
	}

}
