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
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

//    @PostConstruct
//    public void load() {
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setName("name"+i);
//            user.setUsername("user" + i);
//            user.setPassword("pass" + i);
//            user.setPhone("0" + i + 111111111);
//            user.setEmail("us"+i+"@m.co");
//            user.setUserRole(UserRole.CLIENT);
//            repository.save(user);
//        }
//        for (int i = 0; i < 2; i++) {
//            User user = new User();
//            user.setName("name"+i);
//            user.setUsername("admin" + i);
//            user.setPassword("pass" + i);
//            user.setPhone("0" + i + 111111111);
//            user.setEmail("adm"+i+"@m.co");
//            user.setUserRole(UserRole.ADMIN);
//            repository.save(user);
//        }
//    }
}
