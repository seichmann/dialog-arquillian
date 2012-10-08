package com.prodyna.booking.service;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.producer.EntityManagerProducer;

@RunWith(Arquillian.class)
public class AircraftServiceTest {

	@Inject
	private AircraftService as;

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "aircraft.jar");
		jar.addAsResource("META-INF/beans.xml");
		jar.addAsResource("META-INF/persistence.xml");
		jar.addClass(AircraftService.class);
		jar.addClass(AircraftServiceBean.class);
		jar.addClass(EntityManagerProducer.class);
		jar.addPackage("com.prodyna.booking.monitoring");
		return jar;
	}

	@Test
	public void createAndCount() {
		assertNotNull(as);
	}
}
