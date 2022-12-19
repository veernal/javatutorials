package com.customerservice.servicerequest.client;

import java.util.Optional;

import com.customerservice.servicerequest.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "customer", url = "http://localhost:9091")
public interface CustomerClient {

    @GetMapping("/customer/getCustomerId/{id}")
    Optional<Customer> getCustomerByID(@PathVariable int id);
}
