package com.besttravelproject.service;

import com.besttravelproject.domain.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> findAll();
    List<Flight> findByCountry(String country);
    Flight findById(Long id);
}
