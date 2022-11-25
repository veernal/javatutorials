package com.demo.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.exceptions.OrderException;
import com.demo.models.Order;
import com.demo.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repo;

	public boolean placeOrder(Order order) throws OrderException {

		try {
			Order savedOrder = repo.saveOrderInDb(order);// null, order with id 0, exception
			System.out.println("SERVICE: " + savedOrder);

			if (savedOrder == null) {
				System.out.println("Saved order is null");
				return false;
			} else if (savedOrder.getId() == 0) {
				System.out.println("Saved order does not contain id");
				return false;
			} else {
				System.out.println("order is placed successfully");
				return true;
			}
		} catch (SQLException e) {
			throw new OrderException(e.getMessage());
		}

	}

}
