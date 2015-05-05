package com.besttravelproject.service;


import com.besttravelproject.domain.Cart;
import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.User;

import java.util.List;

public interface OrderService {
    Long makeOrder(User user, Cart cart);
    List<Order> findAll(int limit, int offset);
    List<Order> findByClientName(int limit, int offset, String name);
    List<Order> findByClientId(int limit, int offset, Long id);
    Order findById(Long id);
    boolean update(Order order);
    long countAll();
    long countByClientName(String name);
    long countByClientId(Long id);
}
