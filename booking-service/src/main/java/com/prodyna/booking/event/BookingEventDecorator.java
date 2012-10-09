package com.prodyna.booking.event;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.prodyna.booking.BookingService;

@Decorator
public abstract class BookingEventDecorator implements BookingService {

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
}
