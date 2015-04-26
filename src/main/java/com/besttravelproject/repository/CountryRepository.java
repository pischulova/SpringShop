package com.besttravelproject.repository;

import com.besttravelproject.domain.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> findAll();
    Country findById(Long id);
}
