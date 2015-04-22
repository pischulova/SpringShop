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
    static final String FIND_BY_COUNTRY =
            "SELECT f FROM Flight f WHERE f.country.nameEn LIKE ?1 OR f.country.nameRu LIKE ?2";

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
        query.setParameter(1, country+"%");
        query.setParameter(2, country+"%");
        return query.getResultList();
    }

    @Override
    public List<Flight> findAll() {
        Query query = em.createQuery(FIND_ALL_FLIGHTS);
        return query.getResultList();
    }

    @Override
    public Flight findById(Long id) {
        return em.find(Flight.class, id);
    }
}
