package com.besttravelproject.service;

import com.besttravelproject.domain.Cart;
import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.OrderItem;
import com.besttravelproject.domain.User;
import com.besttravelproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Iterator;
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

        Map<OrderItem, Object> cartFlights = cart.getFlights();
        Iterator<Map.Entry<OrderItem, Object>> it = cartFlights.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<OrderItem, Object> entry = it.next();
            OrderItem item = entry.getKey();
            order.addOrderItem(item);
            sum += item.getQuantity() * item.getFlight().getPrice();
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
