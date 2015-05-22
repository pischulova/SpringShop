package com.besttravelproject.repository;

import com.besttravelproject.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Transactional
    @Override
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    @Override
    public boolean update(Order order) {
        if (null != em.merge(order)) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Order findById(Long id) {
        Order order = em.find(Order.class, id);
        if (null != order) {
            order.getItems().size();
        }
        return order;
    }

    @Override
    public List<Order> findByClientName(int limit, int offset, String name) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findByClientName", Order.class);
        query.setParameter(1, "%"+ name +"%");
        return query.setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    @Override
    public List<Order> findByClientId(int limit, int offset, Long id) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findByClientId", Order.class);
        query.setParameter(1, id);
        return query.setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    @Override
    public List<Order> findAll(int limit, int offset) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findAll", Order.class);
        return query.setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    @Override
    public long countAll() {
        TypedQuery<Long> query = em.createNamedQuery("Order.countAll", Long.class);
        return query.getSingleResult();
    }

    @Override
    public long countByClientName(String name) {
        TypedQuery<Long> query = em.createNamedQuery("Order.countByClientName", Long.class);
        query.setParameter(1, "%"+ name +"%");
        return query.getSingleResult();
    }

    @Override
    public long countByClientId(Long id) {
        TypedQuery<Long> query = em.createNamedQuery("Order.countByClientId", Long.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }
}
