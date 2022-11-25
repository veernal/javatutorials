package com.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Demo04_Stream {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Spiderman", "superman", "Hulk", "Antman", "Ironman", "Shaktiman","Hanuman", "Spiderman");
		System.out.println("Original List: "+names);

		
		List<String> newList = names.stream()
				.distinct()
				.filter(name->{return name.endsWith("man");})
				.filter(name->{return name.startsWith("s")||name.startsWith("S");})
				.map(name->{return name.toUpperCase();}).collect(Collectors.toList());
		System.out.println("NewList: "+newList);
		
		Set<String> setNames = names.stream()
				.filter(name->name.endsWith("man"))
				.filter(name->name.startsWith("s")||name.startsWith("S"))
				.map(name->name.toUpperCase())
				.collect(Collectors.toSet());
		
		System.out.println("Set: "+setNames);

	
		
		
	}
	
}
