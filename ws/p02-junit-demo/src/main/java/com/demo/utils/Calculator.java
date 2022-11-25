package com.demo.utils;

public class Calculator {
	public int add(int ...nums) throws Exception {
		int sum = 0;
		for(int n:nums) {
			if(n==Integer.MAX_VALUE) {
				throw new Exception("Can not add max value");
			}
			sum += n;
		}
		return sum;
	}
	
}
