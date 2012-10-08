package com.prodyna.booking.event;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.prodyna.booking.entity.Booking;

@Singleton
public class BookingEventObserver {

	@Inject
	private Logger log;

	private Booking last = null;
	private int count = 0;

	public void onBooking(@Observes Booking b) {
		log.info("New booking " + b);
		last = b;
		count++;
	}

	public Booking getLast() {
		return last;
	}

	public void setLast(Booking last) {
		this.last = last;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
