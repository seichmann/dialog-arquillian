package com.prodyna.booking.test;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.booking.AircraftService;

@RunWith(Arquillian.class)
public class EARDeploymentTest {

	@Inject
	private AircraftService as;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.createFromZipFile(
				EnterpriseArchive.class, new File(
						"../booking-app/target/booking-app.ear"));
	}
	
	@Test
	@RunAsClient
	public void createAircraft() {
//		assertEquals(0, as.list().size());
//		as.create("D-EEFZ");
//		assertEquals(1, as.list().size());
	}
}
