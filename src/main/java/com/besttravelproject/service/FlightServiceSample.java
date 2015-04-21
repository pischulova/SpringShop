package com.besttravelproject.service;

import com.besttravelproject.domain.Flight;
import com.besttravelproject.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("flightService")
public class FlightServiceSample implements FlightService{
    @Autowired
    private FlightRepository repository;

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }
}
