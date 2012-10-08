package com.prodyna.booking.producer;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.prodyna.booking.monitoring.Monitored;

@Monitored
public class EntityManagerProducer {

	@PersistenceContext private EntityManager em;
	
	@Produces
	public EntityManager produceEntityManager() {
		return em;
	}
	
}
