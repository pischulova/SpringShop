package com.besttravelproject.repository;

import com.besttravelproject.domain.User;

import java.util.List;

public interface UserRepository {
    Long save(User user);
    boolean delete(User user);
    boolean update(User user);
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
}
