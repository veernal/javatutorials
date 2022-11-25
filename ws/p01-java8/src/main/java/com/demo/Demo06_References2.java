package com.demo;

import java.util.function.BiFunction;

class Message{
	public Message(String s) {
		System.out.println("In message class cons: "+s);
	}
}

interface MessageActivity{
	public Message getMessage(String msg);
}

public class Demo06_References2 {

	public static void main(String[] args) {
		MessageActivity message = (msg)->{return new Message(msg);};
		
		// ref to cons
		MessageActivity message2 = Message::new; // new Message()
	}
	
}
