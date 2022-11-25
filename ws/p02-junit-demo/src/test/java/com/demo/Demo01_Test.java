package com.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.utils.Calculator;

@SpringBootTest
public class Demo01_Test {
	
	Calculator calc;
	
	@BeforeEach
	public void setup() {
		calc = new Calculator();
	}
	@AfterEach
	public void tearDown() {
		calc = null;
	}
	
	@Test
	public void calculatorShouldAdd2Nums() throws Exception {
		int sum = calc.add(8, 4);
		Assertions.assertTrue(12==sum);
		Assertions.assertEquals(12,sum);
	}
	
	@Test
	public void calculatorShouldAdd2NegativeNums() throws Exception {
		int sum = calc.add(-4, -5);
		Assertions.assertTrue(-9==sum);
		Assertions.assertEquals(-9,sum);
	}
	
	@Test
	public void calculatorShouldAdd3Nums() throws Exception {
		int sum = calc.add(8, 4, 9);
		Assertions.assertTrue(21==sum);
		Assertions.assertEquals(21,sum);
	}
	
	@Test
	public void calculatorShouldAdd5Nums() throws Exception{
		int sum = calc.add(8, 4, 9, 5, 2);
		Assertions.assertTrue(28==sum);
		Assertions.assertEquals(28,sum);
	}
	
	@Test
	public void calculatorShouldNotAdd2MaxNums() throws Exception {
		// wait for exception
		Assertions.assertThrows(Exception.class, ()->{
			calc.add(Integer.MAX_VALUE, Integer.MAX_VALUE);			
		});
		
	}
	

	
}
