package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Value("${my.demo.env}")
	private String demo;
	@Value("${my.dirs}")
	private String dirs;
		
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Demo: "+demo);
		System.out.println("Dirs: "+dirs);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
