package com.prodyna.booking.event;

import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;

import com.prodyna.booking.BookingService;
import com.prodyna.booking.monitoring.Monitored;

@Decorator
@Monitored
public class BookingEventDecorator implements BookingService {

	@Inject
	@Delegate
	private BookingService bs;

	@Inject
	@Ticket
	private Event<String> be;

	@Inject
	private Logger log;

	@Override
	public String book(String fid, String sid, String pax) {
		String id = bs.book(fid, sid, pax);
		log.info("Firing event " + id);
		be.fire(id);
		return id;
	}

	@Override
	public void cancel(@PathParam("tid") String tid) {
		bs.cancel(tid);
	}

	@Override
	public List<String> list() {
		return bs.list();
	}

	@Override
	public String flightNumber(@PathParam("tid") String tid) {
		return bs.flightNumber(tid);
	}

	@Override
	public String aircraft(@PathParam("tid") String tid) {
		return bs.aircraft(tid);
	}
}
