package com.besttravelproject.service;

import com.besttravelproject.domain.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> findAll(int limit, int offset);
    List<Flight> findByCountry(int limit, int offset, String country);
    Flight findById(Long id);
    boolean update(Flight flight);
    boolean setDisabled(Long id);
    void delete(Flight flight);
    void deleteById(Long id);
    long countAll();
    long countByCountry(String country);
}
