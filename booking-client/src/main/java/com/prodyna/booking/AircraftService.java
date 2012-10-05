package com.prodyna.booking;

import java.util.List;

public interface AircraftService {
    void create( String aid );
    void delete( String aid );
    List<String> list();
}
