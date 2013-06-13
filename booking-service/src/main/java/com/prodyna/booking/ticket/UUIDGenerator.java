package com.prodyna.booking.ticket;

import com.prodyna.booking.model.Booking;

import javax.enterprise.inject.Alternative;
import java.util.UUID;

@Alternative
public class UUIDGenerator implements IDGenerator {

    @Override
    public String generate(Booking b) {
        return UUID.randomUUID().toString();
    }

}
