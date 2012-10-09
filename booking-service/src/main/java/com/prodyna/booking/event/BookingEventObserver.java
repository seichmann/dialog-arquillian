package com.prodyna.booking.event;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

@Path("/beo")
@LocalBean
@Singleton
public class BookingEventObserver {

	@Inject
	private Logger log;

	private String last = null;
	private int count = 0;

	public void onBooking(@Observes @Ticket String s) {
		log.info("New booking " + s);
		last = s;
		count++;
	}

	@GET
	@Path("bookingevent/last")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLast() {
		return last;
	}

	@Path("bookingevent/count")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCount() {
		return count;
	}

}
