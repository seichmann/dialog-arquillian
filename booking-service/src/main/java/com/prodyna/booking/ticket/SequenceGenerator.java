package com.prodyna.booking.ticket;

import com.prodyna.booking.model.Booking;

import javax.enterprise.inject.Alternative;

@Alternative
public class SequenceGenerator implements IDGenerator {

    private static long seq = 0;

    @Override
    public String generate(Booking b) {
        return "" + (seq++);
    }

}
