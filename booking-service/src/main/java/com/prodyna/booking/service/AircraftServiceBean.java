package com.prodyna.booking.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.entity.Aircraft;

@Stateless
public class AircraftServiceBean implements AircraftService {

	@Inject
	private EntityManager em;
	
	@Override
	public void create(String aid) {
		em.persist( new Aircraft( aid ) );
	}

	@Override
	public void delete(String aid) {
		Aircraft a = em.find( Aircraft.class, aid );
		em.remove( a );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> list() {
		return em.createQuery("from Aircraft a").getResultList();
	}

}
