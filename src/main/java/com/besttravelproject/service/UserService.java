package com.besttravelproject.service;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;

import java.util.List;

public interface UserService {
    Long save(User user);
    User login(String username, String password);
    boolean update(User user);
    boolean delete(User user);
    List<User> findAll();
    List<User> findAllByRole(UserRole role);
    List<User> findAllByStatus(Boolean status);
    User findByUsername(String username);
}
