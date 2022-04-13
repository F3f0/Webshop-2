package com.example.webshop.controller;

import com.example.webshop.model.BuyOrder;
import com.example.webshop.model.Customer;
import com.example.webshop.model.Item;
import com.example.webshop.repository.BuyOrderRepository;
import com.example.webshop.repository.CustomerRepository;
import com.example.webshop.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepository mockRepository;

    @MockBean
    private BuyOrderRepository mockBuyOrderRepository;

    @MockBean
    private CustomerRepository mockCustomerRepository;

    @BeforeEach
    void setUp() {
        Item item1 = Item.builder().id(1L).name("MacBook Pro").build();
        Item item2 = Item.builder().id(2L).name("iPad").build();
        Item item3 = Item.builder().id(3L).name("iPhone").build();

        Customer customer = Customer.builder().name("Lennart Skoglund").address("Stockholm").id(1L).build();
        BuyOrder buyOrder = BuyOrder.builder().id(1L).customer(customer).items(List.of(item3)).build();

        when(mockRepository.findById(3L)).thenReturn(Optional.of(item3));
        when(mockRepository.findAll()).thenReturn(List.of(item1, item2, item3));
        when(mockRepository.findItemByName("iPhone")).thenReturn(item3);

        when(mockBuyOrderRepository.findById(1L)).thenReturn(Optional.of(buyOrder));
        when(mockCustomerRepository.findById(1L)).thenReturn(Optional.of(customer));
    }

    @Test
    void getAllItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id" : 1,
                                "name" : "MacBook Pro"
                            },
                            {
                                "id" : 2,
                                "name" : "iPad"
                            },
                            {
                                "id" : 3,
                                "name" : "iPhone"
                            }

                        ]"""));
    }

    @Test
    void getItemByNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items/iPhone")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 3,
                            "name": "iPhone"
                        }
                        """));
    }

    @Test
    void addNewItemTest() throws Exception {
        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name" : "Airpods"
                                }"""))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Saved")));
    }

    @Test
    void getOrdersByCustomerIdAndItemIdTest() throws Exception {
        mockMvc.perform(post("/items/buy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                       "customerId" : 1,
                                       "itemId" : 3
                                 }
                                 """))
                .andExpect(status().isCreated())
                /*.andExpect(content().json("""
                        {
                             "id": 1,
                             "customer": {
                                 "id": 1,
                                 "name": "Lennart Skoglund",
                                 "address": "Stockholm"
                             },
                             "items": [
                                 {
                                     "id": 3,
                                     "name": "iPhone"
                                 }
                             ]
                         }
                        """))*/;

    }
}