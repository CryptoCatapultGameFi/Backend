package com.catapult.backend;

import com.catapult.backend.model.UserModel;
import com.catapult.backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.web3j.protocol.Web3j;

import java.util.UUID;

@SpringBootApplication
public class BackendApplication {

	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
			userService.register(new UserModel("0x7a1a4ff2c76859ecd71883a8d4d547f2602f1de5", UUID.randomUUID().toString()));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
