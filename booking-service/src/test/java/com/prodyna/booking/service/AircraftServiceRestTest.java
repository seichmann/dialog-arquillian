package com.prodyna.booking.service;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.SeatService;

@RunWith(Arquillian.class)
public class AircraftServiceRestTest {

	private AircraftService as;
	private SeatService ss;

	@Deployment
	public static Archive<?> createDeployment() {
		final WebArchive archive = ShrinkWrap.create(WebArchive.class, "booking.war");
		archive.addPackages(true, "com.prodyna.booking");
		archive.addAsWebInfResource("META-INF/beans.xml", "beans.xml");
		archive.addAsWebInfResource("web.xml");
		archive.addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml");
		
		System.out.println(archive.toString(true));
		
		return archive;
	}

	@Before
	public void before() {
		as = ProxyFactory.create(AircraftService.class, "http://localhost:8080/booking/rest");
		ss = ProxyFactory.create(SeatService.class, "http://localhost:8080/booking/rest");
	}
	
	@Test
	@InSequence(1)
	public void createAircraft() {
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

}
