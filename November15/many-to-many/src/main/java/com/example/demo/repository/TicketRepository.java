package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.request.Customer;
import com.example.demo.request.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
