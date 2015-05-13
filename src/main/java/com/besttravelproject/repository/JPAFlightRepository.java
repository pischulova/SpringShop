package com.besttravelproject.repository;

import com.besttravelproject.domain.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("flightRepository")
public class JPAFlightRepository implements FlightRepository {

    @PersistenceContext(name = "unit1")
    private EntityManager em;

    @Transactional
    @Override
    public Long save(Flight flight) {
        em.persist(flight);
        return flight.getId();
    }

    @Override
    public void delete(Flight flight) {
        em.remove(flight);
    }

    @Override
    public boolean update(Flight flight) {
        if (null != em.merge(flight))
            return true;
        return false;
    }

    @Override
    public List<Flight> findByCountry(int limit, int offset, String country) {
        TypedQuery<Flight> query = em.createNamedQuery("Flight.findByCountry", Flight.class);
        query.setParameter("name", country+"%");
        return query.setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    @Override
    public List<Flight> findAll(int limit, int offset) {
        TypedQuery<Flight> query = em.createNamedQuery("Flight.findAll", Flight.class);
        return query.setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    @Override
    public Flight findById(Long id) {
        return em.find(Flight.class, id);
    }

    @Override
    public long countAll() {
        Query query = em.createNamedQuery("Flight.countAll");
        return (long)query.getSingleResult();
    }

    @Override
    public long countByCountry(String country) {
        Query query = em.createNamedQuery("Flight.countByCountry");
        query.setParameter(1, country+"%");
        query.setParameter(2, country+"%");
        return (long)query.getSingleResult();
    }
}
