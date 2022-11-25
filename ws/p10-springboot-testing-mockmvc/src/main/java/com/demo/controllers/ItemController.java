package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exceptions.OrderException;
import com.demo.models.Order;
import com.demo.services.ItemService;

@RestController
public class ItemController {

    @Autowired
    private ItemService service; 

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order) throws OrderException{
        if(service.placeOrder(order)){
            return "Order is placed successfully";
        } else {
            return "Something bad happenned";
        }
    }    
}
