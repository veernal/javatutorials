package com.demo;

interface Greeting{
	public String greet(String name);
}

class GreetingImpl implements Greeting {
	@Override
	public String greet(String name) {
		return "Welcome "+name;
	}
}

public class Demo01_Greeting {
	
	
	/*
	 Greeting g = (name)-> {
	System.out.println("Input: "+name);
	return "Welcome "+name.toUpperCase();
}
*/
	public static void doTask(Greeting g, String name) {
		System.out.println(g.greet(name));
	}

	public static void main(String[] args) {
		Greeting greeting_1 = new GreetingImpl();
		doTask(greeting_1, "Arun");
		
		Greeting greeting_2 = new Greeting() {

			@Override
			public String greet(String name) {
				return "Welcome "+name;
			}
		};
		doTask(greeting_2, "Jyoti");

		Greeting greeting_3 = new Greeting() {

			@Override
			public String greet(String name) {
				return "Welcome "+name.toUpperCase();
			}
		};
		doTask(greeting_3, "Jaivik");

//		==================>
		
		Greeting greeting_4 = (name)-> "Welcome "+name.toUpperCase();
		doTask(greeting_4, "Maulik");

		doTask((name)-> "Welcome "+name.toLowerCase(), "Maulik");
		
		doTask((name)-> "Dear: "+name.toLowerCase(), "Maulik");

		Greeting greeting_5 = (name)-> {
			System.out.println("Input: "+name);
			return "Welcome "+name.toUpperCase();
		};
		doTask(greeting_5, "Maulik");


		doTask((name)-> {
			System.out.println("Input: "+name);
			return "Welcome "+name.toUpperCase();
		}, "Demo");
	}
}
