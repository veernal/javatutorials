package com.demo;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.exceptions.OrderException;
import com.demo.models.Order;
import com.demo.repositories.ItemRepository;
import com.demo.services.ItemService;

@SpringBootTest
public class TestItemService {

	@Test
	@Disabled
	@Timeout(unit = TimeUnit.MINUTES, value = 2)
	public void shouldPlaceOrder() throws OrderException {
		Order order = new Order("Mobile", 19.2);
		boolean isPlaced = service.placeOrder(order);
		
		Assertions.assertTrue(isPlaced);		
	}

	
	@Autowired // get object reference
	private ItemService service;
	
	@MockBean
	private ItemRepository repo;
	
	@Test
	public void shouldPlaceOrder2() throws OrderException, SQLException {
		Order order = new Order("Mobile", 19.2);
		
		Mockito
			.when(repo.saveOrderInDb(order))
			.thenReturn(new Order(192, "TV", 50));
		
		boolean isPlaced = service.placeOrder(order);
		
		Assertions.assertTrue(isPlaced);		
	}
	
	
	@Test
	public void shouldNotPlaceOrder() throws OrderException, SQLException {
		Order order = new Order("Mobile", 19.2);
		
		Mockito
			.when(repo.saveOrderInDb(order))
			.thenReturn(null);
		
		boolean isPlaced = service.placeOrder(order);
		
		Assertions.assertFalse(isPlaced);		
	}
	
	
	@Test
	public void shouldNotPlaceOrder2() throws OrderException, SQLException {
		Order order = new Order("Mobile", 19.2);
		
		Mockito
			.when(repo.saveOrderInDb(order))
			.thenThrow(SQLException.class);
		
		
		Assertions.assertThrows(OrderException.class, ()->{
			service.placeOrder(order);			
		});		
	}
	
	
	
}
