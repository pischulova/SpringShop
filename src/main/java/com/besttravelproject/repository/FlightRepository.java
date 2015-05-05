package com.besttravelproject.repository;

import com.besttravelproject.domain.Flight;

import java.util.List;

public interface FlightRepository {
    Long save(Flight flight);
    void delete(Flight flight);
    boolean update(Flight flight);
    List<Flight> findByCountry(int limit, int offset, String country);
    List<Flight> findAll(int limit, int offset);
    Flight findById(Long id);
    long countAll();
    long countByCountry(String country);
}
