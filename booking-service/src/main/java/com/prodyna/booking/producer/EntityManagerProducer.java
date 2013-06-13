package com.prodyna.booking.producer;

import com.prodyna.booking.monitoring.Monitored;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Monitored
public class EntityManagerProducer {

    @PersistenceContext
    private EntityManager em;

    @Produces
    public EntityManager produceEntityManager() {
        return em;
    }

}
