package com.example.webshop.controller;

import com.example.webshop.model.Item;
import com.example.webshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemsRepository;

    // GET
    // http://localhost:8080/items
    @RequestMapping
    public Iterable<Item> getAllItems() {
        return itemsRepository.findAll();
    }

    // GET
    // http://localhost:8080/items/1
    @RequestMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemsRepository.findById(id).orElseThrow(() ->
                new RuntimeException(
                        "Item with id: " + id + " could not be found"));
    }

    // POST
    // Postman
    @PostMapping
    public String addNewItem(@RequestBody Item item) {
        itemsRepository.save(item);
        return "Saved";
    }

}
