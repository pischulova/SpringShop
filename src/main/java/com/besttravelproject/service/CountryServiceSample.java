package com.besttravelproject.service;

import com.besttravelproject.domain.Country;
import com.besttravelproject.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryService")
public class CountryServiceSample implements CountryService {
    @Autowired
    private CountryRepository repository;

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }
}
