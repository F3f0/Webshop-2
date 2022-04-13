package com.example.webshop.controller;

import com.example.webshop.model.Customer;
import com.example.webshop.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository mockRepository;


    @BeforeEach
    void setUp() {
        Customer customer1 = Customer.builder().name("Lennart Skoglund").address("Stockholm").id(1L).build();
        Customer customer2 = Customer.builder().name("David Beckham").address("Manchester").id(2L).build();
        Customer customer3 = Customer.builder().name("Jorge Resurrección Merodio").address("Madrid").id(3L).build();

        when(mockRepository.findById(1L)).thenReturn(Optional.of(customer1));
        when(mockRepository.findAll()).thenReturn(List.of(customer1, customer2, customer3));
    }

    @Test
    void getAllCustomersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "name" : "Lennart Skoglund",
                                "address" : "Stockholm"
                            },
                            {
                                "name" : "David Beckham",
                                "address" : "Manchester"
                            },
                            {
                                "name" : "Jorge Resurrección Merodio",
                                "address" : "Madrid"
                            }

                        ]"""));
    }
    @Test
    void getCustomerByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                       {
                            "id" : 1,
                           "name" : "Lennart Skoglund",
                           "address" : "Stockholm"
                       }
                        """));
    }

}