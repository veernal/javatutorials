package com.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.exceptions.GreetException;
import com.demo.utils.Greeting;

@SpringBootTest
public class Demo02_TestGreeting {

	Greeting greeting;
	
	@AfterEach
	public void tearDown() {
		greeting = null;
	}
	
	@BeforeEach
	public void setup() {
		greeting = new Greeting();
	}
	
	
	// should greet with valid name
	@Test
	public void shouldGreet() throws GreetException {
		String result = greeting.greet("aRuN");
		Assertions.assertEquals("Welcome Arun", result);
	}

	// should greet with valid name
	@Test
	public void shouldGreetWithNameContaining2Chars() throws GreetException {
		String result = greeting.greet("ak");
		Assertions.assertEquals("Welcome Ak", result);
	}

	// should not greet with empty string as name
	@Test
	public void shouldNotGreetWithEmptyString() {
		Assertions.assertThrows(GreetException.class, ()->{			
			greeting.greet("");
		});
	}
	
	// should not greet with null as name
	@Test
	public void shouldNotGreetWithNullAsName() {
		Assertions.assertThrows(GreetException.class, ()->{			
			greeting.greet(null);
		});
	}
	
	// should not greet with char as name == "A"
	@Test
	public void shouldNotGreetWithSingleCharInName() {
		Assertions.assertThrows(GreetException.class, ()->{			
			greeting.greet("K");
		});
	}
	
	// your job -> should not greet with invalid name = arun2745, arun@123

}
