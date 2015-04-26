package com.besttravelproject.repository;

import com.besttravelproject.domain.Order;

import java.util.List;

public interface OrderRepository {
    Long save(Order order);
    boolean delete(Order order);
    boolean update(Order order);
    Order findById(Long id);
    List<Order> findByClientName(String name);
    List<Order> findAll();
}
