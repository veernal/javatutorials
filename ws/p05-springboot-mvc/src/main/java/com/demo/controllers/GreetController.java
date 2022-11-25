package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/users")
public class GreetController {

	// demos
	
//	@GetMapping("greet/{name}")
	@RequestMapping(method = RequestMethod.GET, value = "greet/{name}")
	@ResponseBody
	public String greetUser(@PathVariable String name) {
		return "hello "+name;
	}
	
	@GetMapping("greet")
	public String greetUser() {
		System.out.println("finding view");
		return "welcome";  // path + welcome.jsp or .html or ....
	}
	
	@PostMapping("login")
	public String handleLogin() {
		return "invalid";
	}
	
	@GetMapping("div/{num1}/{num2}")
	@ResponseBody
	public int divide(@PathVariable int num1, @PathVariable int num2) {
		return num1/num2;
	} 
	
}
