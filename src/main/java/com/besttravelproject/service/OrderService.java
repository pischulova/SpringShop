package com.besttravelproject.service;


import com.besttravelproject.domain.Order;

import java.util.List;

public interface OrderService {
    Long save(Order order);
    List<Order> findAll();
    List<Order> findByClientName(String name);
    Order findById(Long id);
    boolean update(Order order);
    void delete(Order order);
}
