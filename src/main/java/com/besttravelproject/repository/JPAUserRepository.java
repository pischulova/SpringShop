package com.besttravelproject.repository;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("userRepository")
public class JPAUserRepository implements UserRepository {
    static final String FIND_ALL_USERS = "SELECT u FROM User u";
    static final String FIND_ALL_BY_STATUS = "SELECT u FROM User u WHERE u.isBad = ?1";
    static final String FIND_ALL_BY_ROLE = "SELECT u FROM User u WHERE u.userRole = ?1";
    static final String FIND_BY_USERNAME = "SELECT u FROM User u WHERE u.username = ?1";

    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Transactional
    @Override
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }


    @Override
    public User findByUsername(String username) {
        Query query = em.createQuery(FIND_BY_USERNAME);
        query.setParameter(1, username);
        User result = null;
        try {
            result = (User) query.getSingleResult();
        } catch (NoResultException e) {}
        return result;
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery(FIND_ALL_USERS);
        return query.getResultList();
    }

    @Override
    public List<User> findAllByRole(UserRole role) {
        Query query = em.createQuery(FIND_ALL_BY_ROLE);
        query.setParameter(1, role);
        return query.getResultList();
    }

    @Override
    public List<User> findAllByStatus(boolean status) {
        Query query = em.createQuery(FIND_ALL_BY_STATUS);
        query.setParameter(1, status);
        return query.getResultList();
    }
}
