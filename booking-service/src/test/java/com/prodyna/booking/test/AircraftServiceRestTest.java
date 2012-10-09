package com.prodyna.booking.test;

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

@RunWith(Arquillian.class)
public class AircraftServiceRestTest {

	private AircraftService as;

	@Deployment
	public static Archive<?> createDeployment() {
		final WebArchive archive = ShrinkWrap.create(WebArchive.class, "aircraft.war");
		archive.addPackages(true, "com.prodyna.booking");
		archive.addAsWebInfResource("META-INF/beans.xml", "beans.xml");
		archive.addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml");
		return archive;
	}

	@Before
	public void before() {
		// TODO @ArquillianResource
		as = ProxyFactory.create(AircraftService.class, "http://localhost:8080/aircraft/rest");
	}
	
	@Test
	@InSequence(1)
	public void createAndCount() {
		assertEquals(0,  as.list().size() );
		as.create("D-ABVX");
		as.create("D-AIRX");
		assertEquals(2,  as.list().size() );
	}

}
