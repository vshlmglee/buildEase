package com.examly.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class SpringappApplication {

	public static void main(String[] args) {
		log.info("Application started");
		SpringApplication.run(SpringappApplication.class, args);
	}


}
  