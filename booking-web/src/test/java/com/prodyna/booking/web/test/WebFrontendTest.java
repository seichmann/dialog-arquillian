package com.prodyna.booking.web.test;

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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@RunWith(Arquillian.class)
public class WebFrontendTest {

	@ArquillianResource URL url;
	
	@Drone HtmlUnitDriver driver;

	@Deployment
	public static Archive<?> createDeployment() {
		final WebArchive archive = ShrinkWrap.create(WebArchive.class,
				"booking.war");
		archive.addPackages(true, "com.prodyna.booking");
		archive.addPackages(true, "org.openqa");
//		archive.addAsWebInfResource("META-INF/beans.xml", "beans.xml");
//		archive.addAsWebInfResource("web.xml");
//		archive.addAsWebInfResource("META-INF/persistence.xml",
//				"classes/META-INF/persistence.xml");
		return archive;
	}

	@Test
	@RunAsClient
	@InSequence(1)
	public void listAircraft() {
		Assert.assertNotNull( url );
		System.out.println("URL " + url );
		Assert.assertNotNull( driver );
		
		driver.get( url.toString() + "/aircraft");
		System.out.println( driver.getCurrentUrl() );
	}

}
