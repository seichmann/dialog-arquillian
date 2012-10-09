package com.prodyna.booking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.prodyna.booking.entity.Aircraft;
import com.prodyna.booking.entity.Booking;
import com.prodyna.booking.entity.Flight;
import com.prodyna.booking.entity.Seat;
import com.prodyna.booking.ticket.UUIDGenerator;

public class UUIDGeneratorTest {

	@Test
	public void createUUID() {
		Aircraft a = new Aircraft("D-EEFZ");
		Flight f = new Flight(a, "LH400-12OCT12");
		Seat s = new Seat( a, "A1");
		Booking b = new Booking( f, s, "Darko Krizic");
		String id = new UUIDGenerator().generate( b );
		assertNotNull( id );
		assertEquals(36, id.length() );
		System.out.println( id );
		Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
		Matcher m = p.matcher( id );
		assertTrue( m.matches() );
	}
}
