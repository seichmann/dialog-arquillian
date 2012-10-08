package com.prodyna.booking.event;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.prodyna.booking.entity.Booking;

public class BookingEventObserver {

	@Inject private Logger log;

	public void onBooking( @Observes Booking b ) {
		log.info("New booking " + b );
	}
}
