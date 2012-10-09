package com.prodyna.booking.test.util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class DatabaseCleaner {

	@Inject private EntityManager em;
	
	public void clean() {
		em.createQuery("delete from Aircraft a").executeUpdate();
	}
	
}

