package com.customerservice.customer.service;


import java.util.List;
import java.util.Optional;

import com.customerservice.customer.entity.Customer;
import com.customerservice.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Customer save(Customer customer) {
        return repo.save(customer);
    }

    public String authenticateuser(String username, String password) {
        String pwd = repo.findByName(username);

        return pwd;
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return repo.findById(id);
    }

    public Customer updateCustomerDetails(int id, Customer customer) {
        Optional<Customer> custDetails=repo.findById(id);
        if(!custDetails.isEmpty()) {
            if(customer.getCustomername()!=null) {
                custDetails.get().setCustomername(customer.getCustomername());
            }
            if(customer.getPassword()!=null) {
                custDetails.get().setPassword(customer.getPassword());
            }
            if(customer.getAddress()!=null) {
                custDetails.get().setAddress(customer.getAddress());
            }
            if(customer.getContactNo()!=0) {
                custDetails.get().setContactNo(customer.getContactNo());
            }
            if(customer.getEmailId()!=null) {
                custDetails.get().setEmailId(customer.getEmailId());
            }
            if(customer.getCountry()!=null) {
                custDetails.get().setCountry(customer.getCountry());
            }
            if(customer.getState()!=null) {
                custDetails.get().setState(customer.getState());
            }
            if(customer.getPan()!=null) {
                custDetails.get().setPan(customer.getPan());
            }
            if(customer.getContactPreference()!=null) {
                custDetails.get().setContactPreference(customer.getContactPreference());
            }
            if(customer.getDob()!=null) {
                custDetails.get().setDob(customer.getDob());
            }
        }

        return repo.save(custDetails.get());
    }

    public String findByCustomer(String registration) {
        return repo.findByName(registration);
    }

}
