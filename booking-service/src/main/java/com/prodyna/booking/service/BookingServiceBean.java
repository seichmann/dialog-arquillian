package com.prodyna.booking.service;

import com.prodyna.booking.*;
import com.prodyna.booking.monitoring.Monitored;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
@Monitored
public class BookingServiceBean implements BookingService {

    @Override
    public String book(String fid, String sid, String pax) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cancel(String tid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
