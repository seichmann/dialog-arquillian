package com.prodyna.booking;

import java.util.List;

public interface BookingService {
    String book( String fid, String sid, String pax );
    void cancel( String tid );
    List<String> list();
}
