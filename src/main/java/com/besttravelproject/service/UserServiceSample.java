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

//    @Autowired
//    FlightRepository flightRepository;
//    @Autowired
//    CountryRepository countryRepository;
//    @Autowired
//    OrderRepository orderRepository;

//    @PostConstruct
//    public void loadFlights() {
//        for (int i = 0; i < 5000; i++) {
//            Flight flight = new Flight();
//            flight.setNameEn("flight" + i);
//            flight.setNameRu("авиабилет" + i);
//            Country country = countryRepository.findById(1 + (long) (Math.random() * ((10 - 1) + 1)));
//            flight.setCountry(country);
//            flight.setPrice(2000 + (int) (Math.random() * ((10000 - 2000) + 1)));
//            flightRepository.save(flight);
//        }
//    }
//
//    @PostConstruct
//    public void loadUsers() {
//        for (int i = 0; i < 5000; i++) {
//            User user = new User();
//            user.setName("name" + i);
//            user.setUsername("client" + i);
//            user.setPassword("pass" + i);
//            user.setPhone("0111111111");
//            user.setEmail("us" + i + "@m.com");
//            user.setUserRole(UserRole.CLIENT);
//            user.setIsBad(false);
//            repository.save(user);
//        }
//        for (int i = 0; i < 50; i++) {
//            User user = new User();
//            user.setName("name" + i);
//            user.setUsername("admin" + i);
//            user.setPassword("pass" + i);
//            user.setPhone("0999999999");
//            user.setEmail("adm" + i + "@m.com");
//            user.setUserRole(UserRole.ADMIN);
//            user.setIsBad(false);
//            repository.save(user);
//        }
//    }
//
//    @PostConstruct
//    public void loadOrders() {
//        for (int i = 0; i < 1000; i++) {
//            Order order = new Order();
//            order.setDate(new Date());
//            order.setIsApproved(false);
//            User user = repository.findById(1 + (long) (Math.random() * ((4999 - 1) + 1)));
//            order.setUser(user);
//
//            int flightNum = (int)(1 + Math.random() * ((10 - 1) + 1));
//            for (int q = 0; q < flightNum; q++) {
//                Flight f = flightRepository.findById(1 + (long) (Math.random() * ((4999 - 1) + 1)));
//                order.getFlights().put(f, 1);
//            }
//            orderRepository.save(order);
//        }
//    }

}
