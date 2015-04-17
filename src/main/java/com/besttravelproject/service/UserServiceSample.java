package com.besttravelproject.service;

import com.besttravelproject.domain.User;
import com.besttravelproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceSample implements UserService {
    private UserRepository repository;

    @Autowired
    public UserServiceSample(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public Long save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        User user = repository.findByUsername(username);
        if (null == user) {
            System.out.println("service: user null");
            return false;
        }
        boolean re = password.equals(user.getPassword());
        if (!re)
            System.out.println("service: password no good");
        return re;
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
}
