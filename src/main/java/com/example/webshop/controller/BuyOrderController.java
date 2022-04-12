package com.example.webshop.controller;

import com.example.webshop.model.BuyOrder;
import com.example.webshop.model.Customer;
import com.example.webshop.model.Item;
import com.example.webshop.repository.BuyOrderRepository;
import com.example.webshop.repository.CustomerRepository;
import com.example.webshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class BuyOrderController {

    @Autowired
    BuyOrderRepository buyOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository itemRepository;

    // GET
    // http://localhost:8080/orders
    @RequestMapping
    public Iterable <BuyOrder> getOrders(){
        return buyOrderRepository.findAll();
    }
    //POST
    // Postman
    //@PostMapping("/buy")
    //public BuyOrder getOrdersByCustomerId (@RequestParam Long customerId, @RequestParam Long itemId){
    //    Customer customer = customerRepository.getById(customerId);
    //    Item item = itemRepository.getById(itemId);
    //    BuyOrder buyOrder = BuyOrder.builder().customer(customer).items(List.of(item)).build();
    //    return buyOrder;
    //}

    // GET
    // http://localhost:8080/orders/customerId
    //@RequestMapping("/{id}")
    //public Iterable<BuyOrder> getOrdersByCustomerId (@PathVariable Long id){
    //    return
    //}

}
