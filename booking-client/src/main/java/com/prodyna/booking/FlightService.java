package com.prodyna.booking;

import java.util.List;

public interface FlightService {
    void create( String aid, String fid );
    void delete( String fid );
    List<String> list();
}
