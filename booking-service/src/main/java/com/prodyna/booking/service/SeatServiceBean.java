package com.prodyna.booking.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.prodyna.booking.SeatService;
import com.prodyna.booking.entity.Aircraft;
import com.prodyna.booking.entity.Seat;
import com.prodyna.booking.monitoring.Monitored;

@Stateless
@Monitored
public class SeatServiceBean implements SeatService {

	@Inject
	private EntityManager em;

	@Override
	public void create(String aid, String sid) {
		Aircraft a = em.find(Aircraft.class, aid);
		Seat s = new Seat(a, sid);
		em.persist(s);
	}

	@Override
	public void delete(String aid, String sid) {
		TypedQuery<Seat> q = em
				.createQuery(
						"select s from Seat s where s.aircraft.registration = :aid and s.name = :sid",
						Seat.class);
		q.setParameter("aid", aid);
		q.setParameter("sid", sid);
		Seat s = q.getSingleResult();
		em.remove(s);
	}

	@Override
	public List<String> list(String aid) {
		return em
				.createQuery(
						"select s.name from Seat s where s.aircraft.registration = :aid",
						String.class).setParameter("aid", aid).getResultList();
	}

}
