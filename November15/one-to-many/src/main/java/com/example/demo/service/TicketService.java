package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TicketRepository;
import com.example.demo.request.Ticket;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repo;

    public Ticket save(Ticket t) {
        return repo.save(t);
    }

    public List<Ticket> getByCustomer(int customerId) {
        return repo.findByCustomerId(customerId);
    }
}
