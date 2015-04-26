package com.besttravelproject.repository;

import com.besttravelproject.domain.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("countryRepository")
public class JPACountryRepository implements CountryRepository {
    static final String FIND_ALL_COUNTRIES = "SELECT c FROM Country c";

    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Override
    public List<Country> findAll() {
        Query query = em.createQuery(FIND_ALL_COUNTRIES);
        return query.getResultList();
    }

    @Override
    public Country findById(Long id) {
        return em.find(Country.class, id);
    }
}
