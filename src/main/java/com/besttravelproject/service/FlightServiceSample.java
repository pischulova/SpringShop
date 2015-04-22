package com.besttravelproject.service;

import com.besttravelproject.domain.Flight;
import com.besttravelproject.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("flightService")
public class FlightServiceSample implements FlightService{
    @Autowired
    private FlightRepository repository;

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Flight> findByCountry(String country) {
        return repository.findByCountry(country);
    }

    @Transactional
    @Override
    public Flight findById(Long id) {
        Flight flight = repository.findById(id);
        flight.getCountry();
        return flight;
    }
}
