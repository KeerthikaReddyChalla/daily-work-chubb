package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.request.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
