package com.customerservice.customer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.customerservice.customer.entity.Customer;
import com.customerservice.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public List<String> registerUser(@RequestBody Customer signUp) {


        Customer customer = new Customer();
        String registration = signUp.getEmailId();
            String status = customerService.findByCustomer(registration);
            if(status==null) {
                customer.setCustomername(signUp.getCustomername());
                customer.setPassword(signUp.getPassword());
                customer.setAddress(signUp.getAddress());
                customer.setState(signUp.getState());
                customer.setCountry(signUp.getCountry());
                customer.setEmailId(registration);
                customer.setPan(signUp.getPan());
                customer.setContactNo(signUp.getContactNo());
                customer.setDob(signUp.getDob());
                customer.setContactPreference(signUp.getContactPreference());

                customerService.save(customer);
                List<String> list = new ArrayList<String>();
                list.add("User registered successfully");
                return list;
            }
            else {
                List<String> list = new ArrayList<String>();
                list.add("User already registered, please login ");
                return list;
            }



    }

    @PostMapping("/login")
    public List<String> Login(@RequestBody Customer login) {
        String username = login.getEmailId();
        String password = login.getPassword();
        String pwd = customerService.authenticateuser(username, password);
        List<String> list = new ArrayList<String>();

        if (pwd != null) {
            if (pwd.equals(password)) {
                list.add("User logged in successfully");
                return list;

            } else {
                list.add("failed to login");
                return list;
            }
        } else {
            list.add("Username not exists");
            return list;
        }

    }

    @GetMapping("/getCustomers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomerId/{id}")
    public Optional<Customer> getCustomerByID(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/updateCustomerDetails/{id}")
    public Customer updatereq(@PathVariable int id,@RequestBody Customer customer) {
        Customer customerDetails =customerService.updateCustomerDetails(id, customer);
        return customerDetails;
    }
}
