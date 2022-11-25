package com.demo.exceptions;

public class OrderException extends Exception {
	
	public OrderException() {
	}

	public OrderException(String m) {
		super(m);
	}

	public OrderException(Exception e) {
		super(e);
	}

	public OrderException(String m, Exception e) {
		super(m, e);
	}
}
