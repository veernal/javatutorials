package com.demo;

import java.util.List;

public class Demo07_ParallelStream {

	public static void main(String[] args) {
		List<String> list = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z");
		
		System.out.println(list);
		
//		list.stream().forEach(element->{System.out.println(element);});
		list.stream().forEach(System.out::print);
		System.out.println();
		list.parallelStream().forEach(System.out::print);
	}

}
