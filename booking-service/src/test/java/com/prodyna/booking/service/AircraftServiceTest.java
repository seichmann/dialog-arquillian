package com.prodyna.booking.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.entity.Aircraft;
import com.prodyna.booking.monitoring.Monitored;
import com.prodyna.booking.producer.EntityManagerProducer;

@RunWith(Arquillian.class)
public class AircraftServiceTest {

	@Inject
	private AircraftService as;

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "aircraft.jar");
		jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		jar.addAsResource("META-INF/persistence.xml");
		jar.addClass(Aircraft.class);
		jar.addClass(AircraftService.class);
		jar.addClass(AircraftServiceBean.class);
		jar.addClass(Monitored.class);
		jar.addClass(EntityManagerProducer.class);
		return jar;
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
