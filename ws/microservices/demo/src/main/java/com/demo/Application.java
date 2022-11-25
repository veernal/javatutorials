package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Value("${server.port}")
	Integer port;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(port);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
