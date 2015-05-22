package com.besttravelproject.service;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import com.besttravelproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserServiceSample implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Override
    public Long save(User user) {
        User foundUser = repository.findByUsername(user.getUsername());

        if (null != foundUser) {
            throw new IllegalArgumentException();
        }
        user.setUserRole(UserRole.CLIENT);
        user.setIsBad(false);
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getUsername()));

        return repository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = repository.findByUsername(username);

        if (null == user) {
            return null;
        }

        String encodedPass = passwordEncoder.encodePassword(user.getPassword(), user.getUsername());

        if (!encodedPass.equals(user.getPassword())) {
            return null;
        }

        return user;
    }

    @Transactional
    @Override
    public boolean updateMakeBad(Long id, Boolean flag) {
        User user = repository.findById(id);
        if (null == user) {
            return false;
        }
        user.setIsBad(flag);
        return repository.update(user);
    }

    @Override
    public List<User> findAllByRole(int limit, int offset, UserRole role) {
        if (null == role) {
            return null;
        }
        return repository.findAllByRole(limit, offset, role);
    }

    @Override
    public List<User> findAllByStatus(int limit, int offset, Boolean status) {
        if (null == status) {
            return null;
        }
        return repository.findAllByStatus(limit, offset, status);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public long countByRole(UserRole role) {
        return repository.countByRole(role);
    }

    @Override
    public long countByStatus(Boolean status) {
        return repository.countByStatus(status);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  repository.findByUsername(username);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), user.getAuthorities());

        return userDetails;
    }
}
