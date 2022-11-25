package com.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("greet")
    public String greet(){
        return "Welcome";
    }

    @GetMapping("greet/{name}")
    public String greet(@PathVariable String name){
        return "Welcome "+name;
    }
    
}
