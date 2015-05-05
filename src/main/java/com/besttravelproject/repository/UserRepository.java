package com.besttravelproject.repository;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;

import java.util.List;

public interface UserRepository {
    Long save(User user);
    boolean update(User user);
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAllByRole(int limit, int offset, UserRole role);
    List<User> findAllByStatus(int limit, int offset, boolean status);
    long countByRole(UserRole role);
    long countByStatus(boolean status);

}
