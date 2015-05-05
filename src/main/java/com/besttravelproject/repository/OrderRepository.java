package com.besttravelproject.repository;

import com.besttravelproject.domain.Order;

import java.util.List;

public interface OrderRepository {
    Long save(Order order);
    boolean update(Order order);
    Order findById(Long id);
    List<Order> findByClientName(int limit, int offset, String name);
    List<Order> findByClientId(int limit, int offset, Long id);
    List<Order> findAll(int limit, int offset);
    long countAll();
    long countByClientName(String name);
    long countByClientId(Long id);
}
