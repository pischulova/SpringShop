package com.besttravelproject.service;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import com.besttravelproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceSample implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Long save(User user) {
        user.setUserRole(UserRole.CLIENT);
        user.setIsBad(false);
        user.setOrderAmount(0);
        return repository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = repository.findByUsername(username);
        if (null == user || !password.equals(user.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllByRole(UserRole role) {
        if (null == role) {
            return null;
        }
        return repository.findAllByRole(role);
    }

    @Override
    public List<User> findAllByStatus(Boolean status) {
        if (null == status) {
            return null;
        }
        return repository.findAllByStatus(status);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
//
//    @PostConstruct
//    public void load() {
//        for (int i = 0; i < 100; i++) {
//            User user = new User();
//            user.setName("name"+i);
//            user.setUsername("client" + i);
//            user.setPassword("pass" + i);
//            user.setPhone("0" + i + 11111111);
//            user.setEmail("us"+i+"@m.com");
//            user.setUserRole(UserRole.CLIENT);
//            user.setIsBad(false);
//            user.setOrderAmount(0);
//            repository.save(user);
//        }
//        for (int i = 0; i < 50; i++) {
//            User user = new User();
//            user.setName("name"+i);
//            user.setUsername("adm" + i);
//            user.setPassword("pass" + i);
//            user.setPhone("0" + i + 11111111);
//            user.setEmail("adm"+i+"@m.co");
//            user.setUserRole(UserRole.ADMIN);
//            user.setIsBad(false);
//            user.setOrderAmount(0);
//            repository.save(user);
//        }
//    }
}
