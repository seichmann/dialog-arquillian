package com.prodyna.booking.ticket;

import javax.enterprise.inject.Alternative;

import com.prodyna.booking.entity.Booking;

@Alternative
public class SequenceGenerator implements IDGenerator {

	private static long seq = 0;
	
	@Override
	public String generate(Booking b) {
		return "" + (seq++);
	}

}
