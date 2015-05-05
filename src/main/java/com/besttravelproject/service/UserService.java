package com.besttravelproject.service;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;

import java.util.List;

public interface UserService {
    Long save(User user);
    User login(String username, String password);
    boolean updateMakeBad(Long id, Boolean flag);
    List<User> findAllByRole(int limit, int offset, UserRole role);
    List<User> findAllByStatus(int limit, int offset, Boolean status);
    User findByUsername(String username);
    long countByRole(UserRole role);
    long countByStatus(Boolean status);
}
