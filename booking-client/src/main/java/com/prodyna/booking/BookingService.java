package com.prodyna.booking;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Local
@Path("bs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BookingService {
    
	@POST
	@Path("booking")
	String book(@QueryParam("fid") String fid, @QueryParam("sid") String sid, @QueryParam("pax") String pax );
    
	@DELETE
	@Path("booking/{tid}")
	void cancel(@PathParam("tid") String tid );
    
	@GET
	@Path("booking")
	List<String> list();
	
	@GET
	@Path("booking/{tid}/flightnumber")
	String flightNumber(@PathParam("tid") String tid);
	
	@GET
	@Path("booking/{tid}/aircraft")
	String aircraft(@PathParam("tid") String tid);
}

