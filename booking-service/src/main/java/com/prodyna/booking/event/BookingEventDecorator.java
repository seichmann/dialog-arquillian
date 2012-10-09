package com.prodyna.booking.event;

import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.prodyna.booking.BookingService;

@Decorator
public class BookingEventDecorator implements BookingService {

	@Inject
	@Delegate
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
	public void cancel(String tid) {
		bs.cancel(tid);
	}

	@Override
	public List<String> list() {
		return bs.list();
	}

	@Override
	public String flightNumber(String tid) {
		return bs.flightNumber(tid);
	}

	@Override
	public String aircraft(String tid) {
		return bs.aircraft(tid);
	}
}
