package com.prodyna.booking.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.booking.FlightService;
import com.prodyna.booking.entity.Aircraft;
import com.prodyna.booking.entity.Flight;
import com.prodyna.booking.monitoring.Monitored;

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
