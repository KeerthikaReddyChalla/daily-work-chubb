package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.request.Order1;

@Repository
public interface OrderRepository extends CrudRepository<Order1, Integer>{

}
