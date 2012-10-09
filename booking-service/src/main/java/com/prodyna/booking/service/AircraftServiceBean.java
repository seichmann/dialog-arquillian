package com.prodyna.booking.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.entity.Aircraft;
import com.prodyna.booking.monitoring.Monitored;

@Stateless
@Monitored
@LocalBean
public class AircraftServiceBean implements AircraftService {

	@Inject
	private EntityManager em;

	@Override
	public void create(String aid) {
		em.persist(new Aircraft(aid));
	}

	@Override
	public void delete(String aid) {
		Aircraft a = em.find(Aircraft.class, aid);
		em.remove(a);
	}

	@Override
	public List<String> list() {
		return em.createQuery("select a.registration from Aircraft a",
				String.class).getResultList();
	}

}
