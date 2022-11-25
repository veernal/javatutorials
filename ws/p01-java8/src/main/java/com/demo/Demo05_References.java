package com.demo;

import java.util.function.BiFunction;

class Calculator{
	public static int add(int a, int b) {
		return a+b;
	}
	public int sum(int a, int b) {
		return a+b;
	}
}

public class Demo05_References {

	public static void main(String[] args) {
		BiFunction<Integer,Integer,Integer> biFunc = (a, b)-> a+b;
		// static method ref
		BiFunction<Integer,Integer,Integer> biFunc2 = Calculator::add;
		
		// instance method ref
		BiFunction<Integer,Integer,Integer> biFunc3 = new Calculator()::sum;
		
	}
	
}
