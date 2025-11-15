package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.request.Customer;
import com.example.demo.request.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
	List<Ticket> findByCustomerId(int customerId);
}
