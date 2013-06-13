package com.prodyna.booking.test;

import com.prodyna.booking.model.Aircraft;
import com.prodyna.booking.model.Booking;
import com.prodyna.booking.model.Flight;
import com.prodyna.booking.model.Seat;
import com.prodyna.booking.ticket.UUIDGenerator;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class UUIDGeneratorTest {

    @Test
    public void createUUID() {
        Aircraft a = new Aircraft("D-EEFZ");
        Flight f = new Flight(a, "LH400-12OCT12");
        Seat s = new Seat(a, "A1");
        Booking b = new Booking(f, s, "Darko Krizic");
        String id = new UUIDGenerator().generate(b);
        assertNotNull(id);
        assertEquals(36, id.length());
        System.out.println(id);
        Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        Matcher m = p.matcher(id);
        assertTrue(m.matches());
    }
}
