package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.request.Ticket;
import com.example.demo.request.Customer;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @PostMapping("/{ticketId}/assign/{customerId}")
    public Ticket assignCustomer(
            @PathVariable int ticketId,
            @PathVariable int customerId
    ) {
        return ticketService.assignCustomerToTicket(ticketId, customerId);
    }
}
