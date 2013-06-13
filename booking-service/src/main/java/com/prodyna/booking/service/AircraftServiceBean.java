package com.prodyna.booking.service;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.model.Aircraft;
import com.prodyna.booking.monitoring.Monitored;
import org.slf4j.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
@Monitored
@LocalBean
public class AircraftServiceBean implements AircraftService {

    @Inject
    private Logger log;
    @Inject
    private EntityManager em;

    @Override
    public void create(String aid) {
        em.persist(new Aircraft(aid));
    }

    @Override
    public void delete(String aid) {
        Aircraft a = em.find(Aircraft.class, aid);
        log.info("Removing " + aid);
        em.remove(a);
    }

    @Override
    public List<Aircraft> list() {
        return em.createQuery("select a from Aircraft a",
                Aircraft.class).getResultList();
    }

}
