package com.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GermanConverter implements MessageConverter {

	@Override
	public void translate(String input) {
		System.out.println("German: "+input);
	}
}
