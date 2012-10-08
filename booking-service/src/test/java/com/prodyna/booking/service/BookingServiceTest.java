package com.prodyna.booking.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.BookingService;
import com.prodyna.booking.FlightService;
import com.prodyna.booking.SeatService;

@RunWith(Arquillian.class)
public class BookingServiceTest {

	@Inject
	private	DatabaseCleaner dc;
	
	@Inject
	private AircraftService as;
	
	@Inject
	private SeatService ss;
	
	@Inject
	private FlightService fs;

	@Inject
	private	BookingService bs;
	
	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "aircraft.jar");
		jar.addAsResource("META-INF/beans.xml");
		jar.addAsResource("META-INF/persistence.xml");
		jar.addPackage( "com.prodyna.booking");
		jar.addPackages( true, "com.prodyna.booking.entity");
		jar.addPackages( true, "com.prodyna.booking.monitoring");
		jar.addPackages( true, "com.prodyna.booking.service");
		jar.addPackages( true, "com.prodyna.booking.producer");
		jar.addPackages( true, "com.prodyna.booking.ticket");
		return jar;
	}

	@Test
	@InSequence(1)
	public void createAircraft() {
		dc.clean();
		assertEquals(0,  as.list().size() );
		as.create("D-ABVX");
		as.create("D-AIRX");
		assertEquals(2,  as.list().size() );
	}
	
	@Test
	@InSequence(2)
	public void createSeats() {
		ss.create("D-ABVX",  "1A");
		ss.create("D-ABVX",  "1B");
		ss.create("D-ABVX",  "2A");
		ss.create("D-ABVX",  "2B");
		assertEquals(4, ss.list("D-ABVX").size() );
		
		ss.create("D-AIRX",  "1A");
		ss.create("D-AIRX",  "1B");
		assertEquals(2, ss.list("D-AIRX").size() );
	}
	
	@Test
	@InSequence(3)
	public void createFlight() {
		fs.create("D-ABVX", "LH400/08OCT12");
		fs.create("D-ABVX", "LH400/09OCT12");
		fs.create("D-AIRX", "LH001/08OCT12");
		fs.create("D-AIRX", "LH001/09OCT12");
		assertEquals(4, fs.list().size() );
	}

	@Test
	@InSequence(4)
	public void bookMarkus() {
		bs.book("LH400/08OCT12", "1A", "Markus Konrad");
	}

	@Test
	@InSequence(5)
	public void bookDarko() {
		bs.book("LH001/09OCT12", "1B", "Darko Krizic");
	}

}
