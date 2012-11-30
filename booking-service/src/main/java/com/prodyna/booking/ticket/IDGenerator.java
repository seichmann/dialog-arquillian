package com.prodyna.booking.ticket;

import javax.enterprise.inject.Alternative;

import com.prodyna.booking.entity.Booking;

@Alternative
public interface IDGenerator {
	String generate( Booking b );
}
