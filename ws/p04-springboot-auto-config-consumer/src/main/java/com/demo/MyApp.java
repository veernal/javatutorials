package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApp implements CommandLineRunner {

	@Autowired
	MessageConverter converter;
	
	@Autowired
	ItalianConverter ic;
	
	@Override
	public void run(String... args) throws Exception {
		converter.translate("Checking");
		ic.translate("demo");
	}	
	
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
