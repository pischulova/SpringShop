package com.besttravelproject.service;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import com.besttravelproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

//    @Autowired
//    FlightRepository flightRepository;
//    @Autowired
//    CountryRepository countryRepository;
//
//    @PostConstruct
//    public void loadFlights() {
//        for (int i = 0; i < 100; i++) {
//            Flight flight = new Flight();
//            flight.setNameEn("flight" + i);
//            flight.setNameRu("авиабилет" + i);
//            Country country = countryRepository.findById(1 + (long) (Math.random() * ((10 - 1) + 1)));
//            flight.setCountry(country);
//            flight.setPrice(2000 + (int) (Math.random() * ((10000 - 2000) + 1)));
//            flightRepository.save(flight);
//        }
//    }

//    @PostConstruct
//    public void loadUsers() {
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
