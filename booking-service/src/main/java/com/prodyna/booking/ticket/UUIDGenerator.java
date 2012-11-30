package com.prodyna.booking.ticket;

import java.util.UUID;

import javax.enterprise.inject.Alternative;

import com.prodyna.booking.entity.Booking;

@Alternative
public class UUIDGenerator implements IDGenerator {

	@Override
	public String generate(Booking b) {
		return UUID.randomUUID().toString();
	}

}
