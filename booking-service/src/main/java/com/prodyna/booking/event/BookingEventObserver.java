package com.prodyna.booking.event;

import org.slf4j.Logger;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton
public class BookingEventObserver {

    @Inject
    private Logger log;

    private String last = null;
    private int count = 0;

    public void onBooking(@Observes @Ticket String s) {
        log.info("New booking " + s);
        last = s;
        count++;
    }

    public String getLast() {
        return last;
    }

    public int getCount() {
        return count;
    }

}
