package com.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

interface NamesActivity {
	void doPerform(List<String> l);
}

public class Demo03_LambdaDemo {

//	public static void doTask(Consumer<List<String>> fi, List<String> list) {
//		fi.accept(list);
//	}

	public static void doTask(NamesActivity fi, List<String> list) {
		fi.doPerform(list);
	}

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Spiderman", "superman", "Hulk", "Antman", "Ironman", "Shaktiman","Hanuman");

		System.out.println("List: " + names);

		// to print all names ending with 'man'
		doTask((list) -> {
			for (String name : list) {
				if (name.endsWith("man")) {
					System.out.print(name);
					System.out.print(", ");
				}
			}
			System.out.println();
		}, names);

		// to print all names starting with either 's' or 'S'
		doTask((list) -> {
			for (String name : list) {
				if (name.startsWith("s") || name.startsWith("S")) {
					System.out.print(name);
					System.out.print(", ");
				}
			}
			System.out.println();
		}, names);

		// to print all names in uppercase
		doTask((list) -> {
			for (String name : list) {
				System.out.print(name.toUpperCase());
				System.out.print(", ");
			}
			System.out.println();
		}, names);

	}

}
