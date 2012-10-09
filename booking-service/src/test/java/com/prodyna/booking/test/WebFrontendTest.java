package com.prodyna.booking.test;
//package com.prodyna.booking.service;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.junit.InSequence;
//import org.jboss.arquillian.test.api.ArquillianResource;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//
//@RunWith(Arquillian.class)
//public class WebFrontendTest {
//
//	@ArquillianResource HtmlUnitDriver driver;
//
//	@Deployment
//	public static Archive<?> createDeployment() {
//		final WebArchive archive = ShrinkWrap.create(WebArchive.class,
//				"booking.war");
//		archive.addPackages(true, "com.prodyna.booking");
//		archive.addAsWebInfResource("META-INF/beans.xml", "beans.xml");
//		archive.addAsWebInfResource("web.xml");
//		archive.addAsWebInfResource("META-INF/persistence.xml",
//				"classes/META-INF/persistence.xml");
//		return archive;
//	}
//
//	@Test
//	@InSequence(1)
//	public void listAircraft() {
//		System.out.println( driver.getCurrentUrl() );
//	}
//
//}
