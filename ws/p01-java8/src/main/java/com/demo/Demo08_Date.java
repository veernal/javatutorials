package com.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class Demo08_Date {
	public static void main(String[] args) {
		System.out.println(new Date());
		System.out.println(new Date(2000, 0, 1));
		
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.of(2000, Month.JANUARY, 1));

		
		
		System.out.println(LocalDateTime.now());
		
	}
}
