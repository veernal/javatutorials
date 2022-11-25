package com.demo;

import org.springframework.stereotype.Component;

@Component
public class ItalianConverter implements MessageConverter {

	@Override
	public void translate(String input) {
		System.out.println("Italian: "+input);
	}
	
}
