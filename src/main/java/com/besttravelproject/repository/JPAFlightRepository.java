package com.besttravelproject.repository;

import com.besttravelproject.domain.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("flightRepository")
public class JPAFlightRepository implements FlightRepository {
    static final String FIND_ALL_FLIGHTS = "SELECT f FROM Flight f";
    static final String FIND_BY_COUNTRY = "SELECT f FROM Flight f WHERE f.country.nameEn = ?1 OR f.country.nameRu = ?1";
    static final String FIND_BY_CITY = "SELECT f FROM Flight f WHERE f.nameEn = ?1 OR f.nameRu = ?1";

    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Override
    public Long save(Flight flight) {
        return null;
    }

    @Override
    public boolean delete(Flight flight) {
        return false;
    }

    @Override
    public boolean update(Flight flight) {
        return false;
    }

    @Override
    public List<Flight> findByCountry(String country) {
        Query query = em.createQuery(FIND_BY_COUNTRY);
        query.setParameter(1, country);
        return query.getResultList();
    }

    @Override
    public List<Flight> findByCity(String city) {
        Query query = em.createQuery(FIND_BY_CITY);
        query.setParameter(1, city);
        return query.getResultList();
    }

    @Override
    public List<Flight> findAll() {
        return null;
    }
}
