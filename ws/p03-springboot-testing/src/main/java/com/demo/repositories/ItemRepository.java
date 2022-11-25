package com.demo.repositories;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.demo.models.Order;

@Repository
public class ItemRepository {

	public Order saveOrderInDb(Order order) throws SQLException {
		System.out.println("In Repository:");
		System.out.println("Saving order in database: "+order);
		
		// saving data in database
		// createdid -> 0
		// SQLExceptoin
		// returning null
		
		int createdId = 127;
		order.setId(createdId);
		
		System.out.println("Saved order in database");
		return order;
	}
	
}
