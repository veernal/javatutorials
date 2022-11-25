package com.demo;

public class EnglishCoverter implements MessageConverter {

	@Override
	public void translate(String input) {
		System.out.println("English: "+input);
	}
	
}
