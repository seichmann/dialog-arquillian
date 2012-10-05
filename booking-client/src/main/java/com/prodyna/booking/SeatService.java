package com.prodyna.booking;

import java.util.List;

public interface SeatService {
    void create( String aid, String sid );
    void delete( String aid, String sid );
    List<String> list( String aid );
}
