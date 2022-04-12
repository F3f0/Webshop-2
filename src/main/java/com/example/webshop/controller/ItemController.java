package com.example.webshop.controller;

import com.example.webshop.error.CustomizedNotFoundException;
import com.example.webshop.model.BuyOrder;
import com.example.webshop.model.Customer;
import com.example.webshop.model.Item;
import com.example.webshop.repository.BuyOrderRepository;
import com.example.webshop.repository.CustomerRepository;
import com.example.webshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;


    // GET
    // http://localhost:8080/items
    @RequestMapping
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // GET
    // http://localhost:8080/items/1
    @RequestMapping("/{name}")
    public Item getItemByName(@PathVariable String name)
            throws CustomizedNotFoundException {
        Item itemByName = itemRepository.findItemByName(name);
        if(itemByName == null){
                   throw new CustomizedNotFoundException(
                            "The item: " + name + " could not be found");
        }
        return itemByName;
    }

    // POST
    // Postman
    @PostMapping
    public String addNewItem(@RequestBody Item item) {
        itemRepository.save(item);
        return "Saved";
    }

    //POST
    // Postman
    @PostMapping("/buy")
    public BuyOrder getOrdersByCustomerId(@RequestParam Long customerId,
                                          @RequestParam Long itemId)
            throws CustomizedNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new CustomizedNotFoundException(
                        "Customer with id: " + customerId + " could not be found"));
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new CustomizedNotFoundException(
                        "Item with id: " + itemId + " could not be found"));
        BuyOrder buyOrder = BuyOrder.builder().customer(customer).items(List.of(item)).build();
        buyOrderRepository.save(buyOrder);
        return buyOrder;
    }


}
