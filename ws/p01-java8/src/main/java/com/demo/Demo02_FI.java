package com.demo;

import java.util.function.BiConsumer;
import java.util.function.Function;

//@FunctionalInterface
//interface Greeting{
//	public String greet(String name);
//}
//
//@FunctionalInterface
//interface Greeting2 {
//	public String toString();
//	public void greet(String name, String lname);
//}
//
//class GreetingImpl2 implements Greeting2 {
//	@Override
//	public void greet(String name) {
//		System.out.println("Welcome "+name);
//	}
//}

public class Demo02_FI {

	public static void doTask(Function<String,String> g, String name) {
		g.apply(name);
	}

	public static void doTask(BiConsumer<String,String> g, String fname, String lname) {
		g.accept(fname, lname);
	}

	public static void main(String[] args) {
		
		Function<String,String> func = (name)-> "Welcome "+name.toLowerCase();
		BiConsumer<String,String> biCon = (name, lname)-> System.out.println("Welcome "+name.toLowerCase());
	
		doTask((name)-> "Welcome "+name, "Jaivik");
		doTask((name)-> "Benvenuto "+name, "Jaivik");

		doTask(func, "Maulik");
		doTask(biCon, "Maulik", "Kumar");
		
	}
}
