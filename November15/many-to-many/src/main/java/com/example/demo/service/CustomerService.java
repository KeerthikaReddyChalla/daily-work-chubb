package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.request.Customer;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Customer save(Customer c) {
        return repo.save(c);
    }

    public Customer get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAll() {
        return (List<Customer>) repo.findAll();
    }

	
}
