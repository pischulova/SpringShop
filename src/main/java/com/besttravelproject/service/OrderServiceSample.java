package com.besttravelproject.service;

import com.besttravelproject.domain.Order;
import com.besttravelproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceSample implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Override
    public Long save(Order order) {
        order.setDate(new Date());
        order.setIsApproved(false);
        return repository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<Order> findByClientName(String name) {
        List<Order> orders = repository.findByClientName(name);
        if (null != orders) {
            orders.forEach(Order::getFlights);
        }
        return orders;
    }

    @Override
    public List<Order> findByClientId(Long id) {
        return null;
    }

    @Transactional
    @Override
    public Order findById(Long id) {
        Order order = repository.findById(id);
        if (null != order) {
            order.getFlights();
        }
        return order;
    }

    @Override
    public boolean update(Order order) {
        if (null != order) {
            return repository.update(order);
        }
        return false;
    }

    @Override
    public void delete(Order order) {

    }
}
