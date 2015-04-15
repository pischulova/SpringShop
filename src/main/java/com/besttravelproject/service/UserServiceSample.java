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
    public User login(String username, String password) {
        return null;
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
