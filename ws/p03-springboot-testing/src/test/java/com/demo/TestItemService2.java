//package com.demo;
//
//import java.sql.SQLException;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.demo.exceptions.OrderException;
//import com.demo.models.Order;
//import com.demo.repositories.ItemRepository;
//import com.demo.services.ItemService;
//
//@SpringBootTest
//public class TestItemService2 {
//
//	@Autowired // get object reference
//	private ItemService service;
//	
////	@MockBean
////	private ItemRepository repo;
//	
//	@Test
//	public void shouldPlaceOrder2() throws OrderException, SQLException {
//		Order order = new Order("Mobile", 19.2);
//		ItemRepository localMockRepository = Mockito.mock(ItemRepository.class);
//		Mockito
//			.when(localMockRepository.saveOrderInDb(order))
//			.thenReturn(new Order(192, "TV", 50));
//		
//		boolean isPlaced = service.placeOrder(order);
//		
//		Assertions.assertTrue(isPlaced);		
//	}
//}
