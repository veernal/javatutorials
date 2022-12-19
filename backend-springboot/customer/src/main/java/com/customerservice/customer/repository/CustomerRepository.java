package com.customerservice.customer.repository;

import com.customerservice.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("select a.password from Customer a where a.emailId=:username")
    public String findByName(String username);

}
