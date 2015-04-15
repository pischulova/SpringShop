package com.besttravelproject.repository;

import com.besttravelproject.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("userRepository")
public class JPAUserRepository implements UserRepository {
    static final String FIND_ALL_USERS = "SELECT u FROM User u";

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
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery(FIND_ALL_USERS);
        return query.getResultList();
    }

//    @PostConstruct
//    public void createDB() {
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setName("name"+i);
//            user.setUsername("userName"+i);
//            user.setPassword("pass"+i);
//            user.setPhone("0"+i+9471104);
//            em.persist(user);
//        }
//    }

}
