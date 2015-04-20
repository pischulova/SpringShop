package com.besttravelproject.repository;

import com.besttravelproject.domain.Flight;

import java.util.List;

public interface FlightRepository {
    Long save(Flight flight);
    boolean delete(Flight flight);
    boolean update(Flight flight);
    List<Flight> findByCountry(String country);
    List<Flight> findByCity(String city);
    List<Flight> findAll();
}
