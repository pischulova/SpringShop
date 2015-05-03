package com.besttravelproject.repository;

import com.besttravelproject.domain.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("countryRepository")
public class JPACountryRepository implements CountryRepository {

    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Override
    public List<Country> findAll() {
        TypedQuery<Country> query = em.createNamedQuery("Country.findAll", Country.class);
        return query.getResultList();
    }

    @Override
    public Country findById(Long id) {
        return em.find(Country.class, id);
    }
}
