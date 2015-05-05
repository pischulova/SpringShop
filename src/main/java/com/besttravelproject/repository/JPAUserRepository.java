package com.besttravelproject.repository;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository("userRepository")
public class JPAUserRepository implements UserRepository {
    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Transactional
    @Override
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public boolean update(User user) {
        User updated = em.merge(user);
        if (null != updated)
            return true;
        return false;
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
        query.setParameter(1, username);
        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {}
        return result;
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAllByRole(int limit, int offset, UserRole role) {
        TypedQuery<User> query = em.createNamedQuery("User.findByRole", User.class);
        query.setParameter(1, role);
        return query.setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    @Override
    public List<User> findAllByStatus(int limit, int offset, boolean status) {
        TypedQuery<User> query = em.createNamedQuery("User.findClientsByStatus", User.class);
        query.setParameter(1, status);
        query.setParameter(2, UserRole.CLIENT);
        return query.setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    @Override
    public long countByRole(UserRole role) {
        Query query = em.createNamedQuery("User.countByRole");
        query.setParameter(1, role);
        return (long)query.getSingleResult();
    }

    @Override
    public long countByStatus(boolean status) {
        Query query = em.createNamedQuery("User.countClientsByStatus");
        query.setParameter(1, status);
        query.setParameter(2, UserRole.CLIENT);
        return (long)query.getSingleResult();
    }
}
