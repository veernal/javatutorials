package com.demo.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.models.Order;
import com.demo.services.ItemService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestItemController {
    
    @Autowired
	private MockMvc mockMvc;

	// @MockBean
	// private ItemService service;

    @Test
	public void shouldPlaceOrder() throws Exception {
        // Order order = new Order("SSD", 12000);

		// Mockito.when(service.placeOrder(order)).thenReturn(true);
	
        String jsonOrder = "{\"item\": \"SSD\",\"price\": 12000}";

        this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/place")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonOrder)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(
                MockMvcResultMatchers
                    .content()
                    .string(
                        Matchers
                            .containsString("success")
                        )
                );
	}

}
