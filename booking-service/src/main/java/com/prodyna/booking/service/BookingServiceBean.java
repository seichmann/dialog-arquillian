package com.prodyna.booking.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.booking.BookingService;
import com.prodyna.booking.entity.Booking;
import com.prodyna.booking.entity.Flight;
import com.prodyna.booking.entity.Seat;
import com.prodyna.booking.monitoring.Monitored;
import com.prodyna.booking.ticket.IDGenerator;

@Stateless
@Monitored
public class BookingServiceBean implements BookingService {

	@Inject private EntityManager em;
	
	@Inject IDGenerator ig;
	
    @Override
    public String book(String fid, String sid, String pax) {
    	Flight f = em.find( Flight.class,  fid );
    	Seat s = em.find( Seat.class, sid );
    	Booking b = new Booking( f, s, pax );
    	String id = ig.generate( b );
    	b.setTicket( id );
    	em.persist( b );
    	return id;
    }

    @Override
    public void cancel(String tid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
