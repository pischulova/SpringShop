package com.besttravelproject.service;

import com.besttravelproject.domain.*;
import com.besttravelproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceSample implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Override
    public Long makeOrder(User user, Cart cart) {
        Order order = new Order();
        order.setUser(user);
        order.setDate(new Date());
        order.setIsApproved(false);
        List<OrderItem> items = order.getItems();
        Long sum = 0L;
        for (Map.Entry entry : cart.getFlights().entrySet()) {
            OrderItem item = new OrderItem();
            item.setFlight((Flight) entry.getKey());
            item.setQuantity((Integer) entry.getValue());
            item.setOrder(order);
            items.add(item);
            sum += ((Flight)entry.getKey()).getPrice();
        }
        order.setSum(sum);
        return repository.save(order);
    }

    @Transactional
    @Override
    public List<Order> findAll() {
        List<Order> orders = repository.findAll();
        return orders;
    }

    @Transactional
    @Override
    public List<Order> findByClientName(String name) {
        List<Order> orders = repository.findByClientName(name);
        return orders;
    }

    @Transactional
    @Override
    public List<Order> findByClientId(Long id) {
        List<Order> orders = repository.findByClientId(id);
        return orders;
    }

    @Transactional
    @Override
    public Order findById(Long id) {
        Order order = repository.findById(id);
        return order;
    }

    @Transactional
    @Override
    public boolean update(Order order) {
        if (null != order) {
            return repository.update(order);
        }
        return false;
    }
}
