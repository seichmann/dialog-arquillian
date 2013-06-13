package com.prodyna.booking.test;

import com.prodyna.booking.AircraftService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AircraftServiceRestTest {

    @Deployment
    public static Archive<?> createDeployment() {
        final WebArchive archive = ShrinkWrap.create(WebArchive.class, "aircraft.war");
        archive.addPackages(true, "com.prodyna.booking");
        archive.addAsWebInfResource("META-INF/beans.xml", "beans.xml");
        archive.addAsWebInfResource("src/main/resources/META-INF/persistence.xml", "classes/META-INF/persistence.xml");
        return archive;
    }

    private AircraftService as;

    @ArquillianResource
    private URL deploymentUrl;

    @Before
    public void before() {
        final String restEndpoint = deploymentUrl + "rest";
        as = ProxyFactory.create(AircraftService.class, restEndpoint);
    }

    @RunAsClient
    @Test
    public void createAndCount() {
        assertEquals(0, as.list().size());
        as.create("D-ABVX");
        as.create("D-AIRX");
        assertEquals(2, as.list().size());
    }

}
