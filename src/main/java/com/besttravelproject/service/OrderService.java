package com.besttravelproject.service;


import com.besttravelproject.domain.Cart;
import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.User;

import java.util.List;

public interface OrderService {
    Long makeOrder(User user, Cart cart);
    List<Order> findAll();
    List<Order> findByClientName(String name);
    List<Order> findByClientId(Long id);
    Order findById(Long id);
    boolean update(Order order);
}
