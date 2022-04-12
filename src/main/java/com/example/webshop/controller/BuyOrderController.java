package com.example.webshop.controller;

import com.example.webshop.error.CustomizedNotFoundException;
import com.example.webshop.model.BuyOrder;
import com.example.webshop.repository.BuyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class BuyOrderController {

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    // GET
    // http://localhost:8080/orders
    @RequestMapping
    public Iterable <BuyOrder> getOrders(){
        return buyOrderRepository.findAll();
    }

    // GET
    // http://localhost:8080/orders/customerId
    @RequestMapping("/{customerId}")
    public List<BuyOrder> getOrdersByCustomerId (@PathVariable Long customerId)
            throws CustomizedNotFoundException {
        List<BuyOrder> allByCustomerId = buyOrderRepository.findAllByCustomerId(customerId);
        if(allByCustomerId.isEmpty()){
            throw new CustomizedNotFoundException("There are no orders available for customer with customer id: " + customerId);
        }
        return allByCustomerId;
    }

}
