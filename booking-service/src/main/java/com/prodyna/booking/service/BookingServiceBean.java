package com.prodyna.booking.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.booking.BookingService;
import com.prodyna.booking.entity.Aircraft;
import com.prodyna.booking.entity.Booking;
import com.prodyna.booking.entity.Flight;
import com.prodyna.booking.entity.Seat;
import com.prodyna.booking.monitoring.Monitored;
import com.prodyna.booking.ticket.IDGenerator;

@Stateless
@Monitored
public class BookingServiceBean implements BookingService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	IDGenerator ig;

	@Override
	public String book(String fid, String sid, String pax) {
		log.info("Booking " + pax + " to " + fid + " on seat " + sid);
		Flight f = em.find(Flight.class, fid);
		Aircraft a = f.getAircraft();
		log.info(a.toString());
		Seat s = em
				.createQuery(
						"select s from Seat s where s.aircraft = :a and s.name = :sid",
						Seat.class).setParameter("a", a)
				.setParameter("sid", sid).getSingleResult();
		Booking b = new Booking(f, s, pax);
		String id = ig.generate(b);
		b.setTicket(id);
		em.persist(b);
		return id;
	}

	@Override
	public void cancel(String tid) {
		Booking b = em.find(Booking.class, tid);
		em.remove(b);
	}

	@Override
	public String flightNumber(String tid) {
		Booking b = em.find(Booking.class, tid);
		return b.getFlight().getFlightNumber();
	}

	@Override
	public String aircraft(String tid) {
		Booking b = em.find(Booking.class, tid);
		return b.getFlight().getAircraft().getRegistration();
	}

	@Override
	public List<String> list() {
		return em.createQuery("select b.id from Booking b", String.class)
				.getResultList();
	}

}
