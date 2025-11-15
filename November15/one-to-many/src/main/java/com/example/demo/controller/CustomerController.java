package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.request.Customer;
import com.example.demo.request.Ticket;
import com.example.demo.service.CustomerService;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TicketService ticketService;

    // Create Customer
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer c) {
        return customerService.save(c);
    }

    // Create Ticket under a Customer
    @PostMapping("/customers/{customerId}/tickets")
    public Ticket createTicket(@PathVariable int customerId, @RequestBody Ticket t) {
        Customer customer = customerService.get(customerId);
        t.setCustomer(customer);
        return ticketService.save(t);
    }

    // Get all tickets for a customer
    @GetMapping("/customers/{customerId}/tickets")
    public List<Ticket> getTickets(@PathVariable int customerId) {
        return ticketService.getByCustomer(customerId);
    }
}
