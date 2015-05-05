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
    public List<Flight> findAll(int limit, int offset) {
        return repository.findAll(limit, offset);
    }

    @Override
    public List<Flight> findByCountry(int limit, int offset, String country) {
        return repository.findByCountry(limit, offset, country);
    }

    @Transactional
    @Override
    public Flight findById(Long id) {
        Flight flight = repository.findById(id);
        if (null != flight) {
            flight.getCountry();
        }
        return flight;
    }

    @Transactional
    @Override
    public boolean update(Flight flight) {
        if (null != flight) {
            return repository.update(flight);
        }
        return false;
    }

    @Transactional
    @Override
    public boolean setDisabled(Long id) {
        Flight flight = repository.findById(id);
        if (null != flight) {
            flight.setIsDisabled(true);
            repository.update(flight);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void delete(Flight flight) {
        if (null != flight) {
            repository.delete(flight);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (null != id) {
            repository.delete(findById(id));
        }
    }

    @Override
    public long countAll() {
        return repository.countAll();
    }

    @Override
    public long countByCountry(String country) {
        return repository.countByCountry(country);
    }
}
