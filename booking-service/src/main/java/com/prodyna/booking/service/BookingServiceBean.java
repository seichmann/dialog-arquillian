package com.prodyna.booking.service;

import com.prodyna.booking.BookingService;
import com.prodyna.booking.model.Aircraft;
import com.prodyna.booking.model.Booking;
import com.prodyna.booking.model.Flight;
import com.prodyna.booking.model.Seat;
import com.prodyna.booking.monitoring.Monitored;
import com.prodyna.booking.ticket.IDGenerator;
import org.slf4j.Logger;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Local(BookingService.class)
@Stateless
@Monitored
@LocalBean
public class BookingServiceBean implements BookingService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private IDGenerator ig;

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
    public List<Booking> list() {
        List<Booking> resultList = em.createQuery("select b from Booking b", Booking.class)
                .getResultList();
        log.debug("Revision 1");
        return resultList;
    }

}
