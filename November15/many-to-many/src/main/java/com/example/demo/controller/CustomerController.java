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

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer c) {
        return customerService.save(c);
    }

    @PostMapping("/customers/{customerId}/tickets/{ticketId}")
    public Customer assignTicketToCustomer(@PathVariable int customerId, @PathVariable int ticketId) {

        Customer customer = customerService.get(customerId);
        Ticket ticket = ticketService.get(ticketId);

        customer.getTickets().add(ticket);
        ticket.getCustomers().add(customer);

        customerService.save(customer);
        ticketService.save(ticket);

        return customer;
    }


    @GetMapping("/customers/{customerId}/tickets")
    public List<Ticket> getTickets(@PathVariable int customerId) {
        return customerService.get(customerId).getTickets();
    }

}
