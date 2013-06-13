package com.prodyna.booking.test;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.test.util.DatabaseCleaner;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AircraftServiceMixedTest {

    @Deployment
    public static Archive<?> createDeployment() {
        final WebArchive archive = ShrinkWrap.create(WebArchive.class, "aircraft.war");
        archive.addPackages(true, "com.prodyna.booking");
        archive.addAsWebInfResource("META-INF/beans.xml", "beans.xml");
        archive.addAsWebInfResource("src/main/resources/META-INF/persistence.xml", "classes/META-INF/persistence.xml");
        return archive;
    }

    @Inject
    private DatabaseCleaner dbc;

    @Inject
    private AircraftService as;

    @InSequence(1)
    @Test
    public void cleanDB() {
        dbc.clean();
    }

    @RunAsClient
    @InSequence(2)
    @Test
    public void createAndCount() {
        final String restEndpoint = "http://127.0.0.1:8080/aircraft/rest";
        as = ProxyFactory.create(AircraftService.class, restEndpoint);
        assertEquals(0, as.list().size());
        as.create("D-ABVX");
        as.create("D-AIRX");
        assertEquals(2, as.list().size());
    }

}
