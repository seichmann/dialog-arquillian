package com.prodyna.booking.ticket;

import com.prodyna.booking.model.Booking;

import javax.enterprise.inject.Alternative;

@Alternative
public interface IDGenerator {
    String generate(Booking b);
}
