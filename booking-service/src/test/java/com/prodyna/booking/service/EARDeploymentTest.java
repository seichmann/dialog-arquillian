package com.prodyna.booking.service;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.booking.AircraftService;

@RunWith(Arquillian.class)
public class EARDeploymentTest {

	private AircraftService as;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.createFromZipFile(
				EnterpriseArchive.class, new File(
						"../booking-app/target/booking-app.ear"));
	}

	@Before
	public void before() {
		 // TODO @ArquillianResource
		as = ProxyFactory.create(AircraftService.class, "http://localhost:8080/booking-web/rest");
	}
	
	@Test
	@RunAsClient
	public void createAircraft() {
		assertEquals(0, as.list().size());
		as.create("D-EEFZ");
		assertEquals(1, as.list().size());
	}
}
