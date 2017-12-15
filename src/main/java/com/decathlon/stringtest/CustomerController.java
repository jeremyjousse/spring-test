package com.decathlon.stringtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/customers")

public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping()
    public @ResponseBody Iterable<Customer> getAllCustormers () {
        return customerRepository.findAll();

    }

    @PostMapping()
    public @ResponseBody Customer add(@RequestBody Customer input) {
        Customer customer = new Customer(input.firstName, input.lastName);
        Customer customerSaved = customerRepository.save(customer);
        return customerSaved;
    }
}
