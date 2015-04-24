package com.besttravelproject.repository;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;

import java.util.List;

public interface UserRepository {
    Long save(User user);
    boolean delete(User user);
    boolean update(User user);
    User findByUsername(String username);
    List<User> findAll();
    List<User> findAllByRole(UserRole role);
    List<User> findAllByStatus(boolean status);
}
