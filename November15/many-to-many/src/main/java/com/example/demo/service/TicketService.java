package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.request.Ticket;
import com.example.demo.request.Customer;
@Service
public class TicketService {

    @Autowired
    private TicketRepository repo;

    @Autowired
    private CustomerRepository customerRepo;

    public Ticket save(Ticket ticket) {
        return repo.save(ticket);
    }

    public List<Ticket> getAll() {
        return repo.findAll();
    }

    public Ticket get(int id) {
        return repo.findById(id).orElseThrow();
    }

    public Ticket assignCustomerToTicket(int ticketId, int customerId) {
        Ticket t = repo.findById(ticketId).orElseThrow();
        Customer c = customerRepo.findById(customerId).orElseThrow();

        t.getCustomers().add(c);
        return repo.save(t);
    }
}
