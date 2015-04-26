package com.besttravelproject.repository;

import com.besttravelproject.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {
    static final String FIND_ALL = "SELECT o FROM Order o";
    static final String FIND_BY_CLIENT_NAME = "SELECT o FROM Order o WHERE o.user.name LIKE ?1";

    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Transactional
    @Override
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
        if (null != em.merge(order))
            return true;
        return false;
    }

    @Transactional
    @Override
    public Order findById(Long id) {
        Order order = em.find(Order.class, id);
        order.getFlights().size();
        return order;
    }

    @Override
    public List<Order> findByClientName(String name) {
        Query query = em.createQuery(FIND_BY_CLIENT_NAME);
        query.setParameter(1, "%"+ name +"%");
        return query.getResultList();
    }

    @Override
    public List<Order> findAll() {
        Query query = em.createQuery(FIND_ALL);
        return query.getResultList();
    }
}
