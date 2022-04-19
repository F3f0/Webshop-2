package com.example.webshop.controller;

import com.example.webshop.error.CustomizedNotFoundException;
import com.example.webshop.model.Customer;
import com.example.webshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.constant.Constable;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // GET
    // http://localhost:8080/customers
    @RequestMapping
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET
    // http://localhost:8080/customers/1
    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id)
            throws CustomizedNotFoundException {
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomizedNotFoundException(
                        "Customer with id: " + id + " could not be found"));
    }

    // POST
    // Postman
    @PostMapping
    public String add(@RequestBody Customer c) {
        customerRepository.save(c);
        return "Saved";
    }

}
