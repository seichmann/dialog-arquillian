package com.prodyna.booking.ticket;

import java.util.UUID;

import com.prodyna.booking.entity.Booking;

public class UUIDGenerator implements IDGenerator {

	@Override
	public String generate(Booking b) {
		return UUID.randomUUID().toString();
	}

}
