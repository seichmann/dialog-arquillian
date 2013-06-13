package com.prodyna.booking.service;

import com.prodyna.booking.FlightService;
import com.prodyna.booking.model.Aircraft;
import com.prodyna.booking.model.Flight;
import com.prodyna.booking.monitoring.Monitored;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
@Monitored
public class FlightServiceBean implements FlightService {

    @Inject
    private EntityManager em;

    @Override
    public void create(String aid, String fid) {
        Aircraft a = em.find(Aircraft.class, aid);
        Flight f = new Flight(a, fid);
        em.persist(f);
    }

    @Override
    public void delete(String fid) {
        Flight f = em.find(Flight.class, fid);
        em.remove(f);
    }

    @Override
    public List<String> list() {
        return em.createQuery("select f.flightNumber from Flight f", String.class)
                .getResultList();
    }

}
