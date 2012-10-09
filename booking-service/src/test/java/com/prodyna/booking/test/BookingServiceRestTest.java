package com.prodyna.booking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.BookingService;
import com.prodyna.booking.FlightService;
import com.prodyna.booking.SeatService;

@RunWith(Arquillian.class)
public class BookingServiceRestTest {

	private static final String DAIRX = "D-AIRX";
	private static final String DABVX = "D-ABVX";

	private AircraftService as;
	private SeatService ss;
	private FlightService fs;
	private BookingService bs;

	@Deployment
	public static Archive<?> createDeployment() {
		final WebArchive archive = ShrinkWrap.create(WebArchive.class, "aircraft.war");
		archive.addPackages(false, "com.prodyna.booking");
		archive.addPackages(true, "com.prodyna.booking.entity");
		archive.addPackages(true, "com.prodyna.booking.monitoring");
		archive.addPackages(true, "com.prodyna.booking.producer");
		archive.addPackages(true, "com.prodyna.booking.service");
		archive.addPackages(true, "com.prodyna.booking.ticket");
		archive.addPackages(true, "com.prodyna.booking.rest");
		archive.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		archive.addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml");
		return archive;
	}

	@Before
	public void before() {
		 // TODO @ArquillianResource
		as = ProxyFactory.create(AircraftService.class, "http://localhost:8080/aircraft/rest");
		ss = ProxyFactory.create(SeatService.class, "http://localhost:8080/aircraft/rest");
		fs = ProxyFactory.create(FlightService.class, "http://localhost:8080/aircraft/rest");
		bs = ProxyFactory.create(BookingService.class, "http://localhost:8080/aircraft/rest");
	}
	
	@Test
	@InSequence(1)
	public void createAircraft() {
		assertEquals(0, as.list().size());
		as.create(DABVX);
		as.create(DAIRX);
		assertEquals(2, as.list().size());
	}

	@Test
	@InSequence(2)
	public void createSeats() {
		ss.create(DABVX, "1A");
		ss.create(DABVX, "1B");
		ss.create(DABVX, "2A");
		ss.create(DABVX, "2B");
		assertEquals(4, ss.list(DABVX).size());

		ss.create(DAIRX, "1A");
		ss.create(DAIRX, "1B");
		assertEquals(2, ss.list(DAIRX).size());
	}

//	@RunAsClient
	@Test
	@InSequence(3)
	public void createFlight() {
		fs.create(DABVX, "LH400-08OCT12");
		fs.create(DABVX, "LH400-09OCT12");
		fs.create(DAIRX, "LH001-08OCT12");
		fs.create(DAIRX, "LH001-09OCT12");
		assertEquals(4, fs.list().size());
	}

	@Test
	@InSequence(4)
	public void bookMarkus() {
		String id = bs.book("LH400-08OCT12", "1A", "Markus Konrad");
		assertNotNull(id);
		assertEquals(1, bs.list().size() );
		assertEquals(DABVX, bs.aircraft( id ) );
		assertEquals("LH400-08OCT12", bs.flightNumber( id) );
	}

	@Test
	@InSequence(5)
	public void bookDarko() {
		String id = bs.book("LH001-09OCT12", "1B", "Darko Krizic");
		assertNotNull(id);
		assertEquals(2, bs.list().size() );
		assertEquals(DAIRX, bs.aircraft( id ) );
		assertEquals("LH001-09OCT12", bs.flightNumber( id) );
	}

	@Test
	@InSequence(5)
	public void cancelOne() {
		String id = bs.list().get(0);
		bs.cancel( id );
		assertEquals(1, bs.list().size() );
	}

}
