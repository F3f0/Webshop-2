package com.example.webshop.controller;

import com.example.webshop.model.BuyOrder;
import com.example.webshop.model.Customer;
import com.example.webshop.model.Item;
import com.example.webshop.repository.BuyOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BuyOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuyOrderRepository mockRepository;

    @BeforeEach
    void setUp() {
        Customer customer1 = Customer.builder().name("Lennart Skoglund").address("Stockholm").id(1L).build();
        Customer customer2 = Customer.builder().name("David Beckham").address("Manchester").id(2L).build();
        Customer customer3 = Customer.builder().name("Jorge Resurrección Merodio").address("Madrid").id(3L).build();

        Item item1 = Item.builder().id(1L).name("MacBook Pro").build();
        Item item2 = Item.builder().id(2L).name("iPad").build();
        Item item3 = Item.builder().id(3L).name("iPhone").build();

        BuyOrder buyOrder1 = BuyOrder.builder().id(1L).customer(customer1).items(List.of(item1, item2, item3)).build();
        BuyOrder buyOrder2 = BuyOrder.builder().id(2L).customer(customer2).items(List.of(item3)).build();
        BuyOrder buyOrder3 = BuyOrder.builder().id(3L).customer(customer3).items(List.of(item2, item3)).build();

        when(mockRepository.findById(1L)).thenReturn(Optional.of(buyOrder1));
        when(mockRepository.findAll()).thenReturn(List.of(buyOrder1, buyOrder2, buyOrder3));
        when(mockRepository.findAllByCustomerId(2L)).thenReturn(List.of(buyOrder2));
    }


    @Test
    void getOrdersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "customer": {
                                    "id": 1,
                                    "name": "Lennart Skoglund",
                                    "address": "Stockholm"
                                },
                                "items": [
                                    {
                                        "id": 1,
                                        "name": "MacBook Pro"
                                    },
                                    {
                                        "id": 2,
                                        "name": "iPad"
                                    },
                                    {
                                        "id": 3,
                                        "name": "iPhone"
                                    }
                                ]
                            },
                            {
                                "id": 2,
                                "customer": {
                                    "id": 2,
                                    "name": "David Beckham",
                                    "address": "Manchester"
                                },
                                "items": [
                                    {
                                        "id": 3,
                                        "name": "iPhone"
                                    }
                                ]
                            },
                            {
                                "id": 3,
                                "customer": {
                                    "id": 3,
                                    "name": "Jorge Resurrección Merodio",
                                    "address": "Madrid"
                                },
                                "items": [
                                    {
                                        "id": 2,
                                        "name": "iPad"
                                    },
                                    {
                                        "id": 3,
                                        "name": "iPhone"
                                    }
                                ]
                            }
                        ]"""));
    }

    @Test
    void getOrdersByCustomerIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 2,
                                "customer": {
                                    "id": 2,
                                    "name": "David Beckham",
                                    "address": "Manchester"
                                },
                                "items": [
                                    {
                                        "id": 3,
                                        "name": "iPhone"
                                    }
                                ]
                            }
                        ]"""));
    }

}