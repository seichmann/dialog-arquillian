package com.prodyna.booking.event;

import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import com.prodyna.booking.BookingService;

public class BookingEventDecorator implements BookingService {

//	@Inject
//	@Delegate
//	@Any
	BookingService bs;

	@Inject
	@Ticket
	Event<String> be;

	@Override
	public String book(String fid, String sid, String pax) {
		String id = bs.book(fid, sid, pax);
		be.fire(id);
		return id;
	}

	@Override
	public void cancel(@PathParam("tid") String tid) {
		bs.cancel( tid );
	}

	@Override
	public List<String> list() {
		return bs.list();
	}

	@Override
	public String flightNumber(@PathParam("tid") String tid) {
		return bs.flightNumber( tid );
	}

	@Override
	public String aircraft(@PathParam("tid") String tid) {
		return bs.aircraft( tid );
	}
}
