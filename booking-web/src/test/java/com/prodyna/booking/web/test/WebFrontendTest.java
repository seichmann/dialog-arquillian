package com.prodyna.booking.web.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;

@RunWith(Arquillian.class)
public class WebFrontendTest {

	@ArquillianResource
	private URL url;

	@Drone
	private WebDriver driver;

	@Deployment(testable=false)
	public static Archive<?> createDeployment() {
		final WebArchive archive = ShrinkWrap.create(WebArchive.class,
				"booking.war");
		archive.addPackages(false, "com.prodyna.booking");
		archive.addPackages(true, "com.prodyna.booking.entity");
		archive.addPackages(true, "com.prodyna.booking.event");
		archive.addPackages(true, "com.prodyna.booking.monitoring");
		archive.addPackages(true, "com.prodyna.booking.producer");
		archive.addPackages(true, "com.prodyna.booking.service");
		archive.addPackages(true, "com.prodyna.booking.ticket");
		archive.addPackages(false, "com.prodyna.booking.web");
		archive.addAsWebInfResource(new File(
				"../booking-service/src/main/resources/META-INF/beans.xml"),
				"beans.xml");
		archive.addAsWebInfResource(
				new File(
						"../booking-service/src/main/resources/META-INF/persistence.xml"),
				"classes/META-INF/persistence.xml");
		return archive;
	}

	@Test
	@RunAsClient
	@InSequence(1)
	public void listAircraft() {
		driver.get(url.toString() + "aircraft");
		System.out.println(driver.getCurrentUrl());
		String c = driver.getPageSource();
		System.out.println(c);
		assertTrue(c.contains("Aircrafts"));
		assertFalse(c.contains("D-EEFZ"));

		WebElement af = driver.findElement(new ByIdOrName("addform"));
		WebElement reg = af.findElement(new ByIdOrName("registration"));
		reg.sendKeys("D-EEFZ");
		af.submit();

		c = driver.getPageSource();
		System.out.println(c);
		assertTrue(c.contains("Aircrafts"));
		assertTrue(c.contains("D-EEFZ"));
	}

}

