package com.demo.utils;

import com.demo.exceptions.GreetException;

public class Greeting {

	public String greet(String name) throws GreetException {
		
		if(name == null) {
			throw new GreetException("Name can not be null");
		} else if(name.length()==0) {
			throw new GreetException("Name can not be empty");
		} else if(name.length()==1) {
			throw new GreetException("Name can not contain single char");
		}
		
		name = (name.charAt(0)+"").toUpperCase() + name.substring(1).toLowerCase();
		return "Welcome "+name;
	}
	
}
