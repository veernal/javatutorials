package com.demo.controllers;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.models.Order;
import com.demo.services.ItemService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestItemController2 {
    
    @LocalServerPort
	private int port;

    @Autowired
	private TestRestTemplate restTemplate;

    @Test
	public void shouldPlaceOrder() throws Exception {
        Order order = new Order("SSD", 12000);

        // Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
		// 		String.class)).contains("Hello, World");


        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:" + port + "/place", HttpMethod.POST, new HttpEntity<Order>(new Order("Ram", 4000)), new ParameterizedTypeReference<String>(){});

        Assertions.assertThat(responseEntity.getBody()).contains("success");

    }
}
