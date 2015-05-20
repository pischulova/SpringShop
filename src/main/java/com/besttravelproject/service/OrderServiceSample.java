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
        Long sum = 0L;
        for (Map.Entry entry : cart.getFlights().entrySet()) {
            Flight flight = (Flight) entry.getKey();
            Integer quantity = (Integer) entry.getValue();
            OrderItem item = new OrderItem();
            item.setFlight(flight);
            item.setQuantity(quantity);
            order.addOrderItem(item);
            sum += quantity * flight.getPrice();
        }
        order.setSum(sum);
        return repository.save(order);
    }

    @Transactional
    @Override
    public List<Order> findAll(int limit, int offset) {
        return repository.findAll(limit, offset);
    }

    @Transactional
    @Override
    public List<Order> findByClientName(int limit, int offset, String name) {
        return repository.findByClientName(limit, offset, name);
    }

    @Transactional
    @Override
    public List<Order> findByClientId(int limit, int offset, Long id) {
        return repository.findByClientId(limit, offset, id);
    }

    @Transactional
    @Override
    public Order findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public boolean update(Order order) {
        if (null != order) {
            return repository.update(order);
        }
        return false;
    }

    @Override
    public long countAll() {
        return repository.countAll();
    }

    @Override
    public long countByClientName(String name) {
        return repository.countByClientName(name);
    }

    @Override
    public long countByClientId(Long id) {
        return repository.countByClientId(id);
    }
}
